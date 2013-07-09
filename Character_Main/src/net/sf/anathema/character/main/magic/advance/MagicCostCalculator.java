package net.sf.anathema.character.main.magic.advance;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.magic.model.magic.IMagicVisitor;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.magic.IMagicTemplate;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.hero.charms.CharmsModelFetcher;
import net.sf.anathema.hero.spells.SpellModel;
import net.sf.anathema.hero.spells.SpellsModelFetcher;
import net.sf.anathema.character.main.advance.CostAnalyzer;
import net.sf.anathema.character.main.magic.model.charm.special.ISubeffectCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charm.special.IUpgradableCharmConfiguration;
import net.sf.anathema.hero.model.Hero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MagicCostCalculator {

  private final CharmsModel charms;
  private final SpellModel spells;
  private final int favoredCreationCharmCount;
  private final int defaultCreationCharmCount;
  private int generalPicksSpent = 0;
  private int favoredPicksSpent = 0;
  private int bonusPointsSpentForCharms = 0;
  private final BonusPointCosts costs;
  private CostAnalyzer analyzer;
  protected int bonusPointsSpentForSpells;
  private final IMagicTemplate magicTemplate;

  public MagicCostCalculator(Hero hero, BonusPointCosts costs) {
    this.magicTemplate = hero.getTemplate().getMagicTemplate();
    this.charms = CharmsModelFetcher.fetch(hero);
    this.spells = SpellsModelFetcher.fetch(hero);
    this.favoredCreationCharmCount = hero.getTemplate().getCreationPoints().getFavoredCreationCharmCount();
    this.defaultCreationCharmCount = hero.getTemplate().getCreationPoints().getDefaultCreationCharmCount();
    this.costs = costs;
    this.analyzer = new CostAnalyzer(hero);
  }

  public void calculateMagicCosts() {
    clear();
    List<Magic> magicToHandle = compileCompleteMagicList();
    if (magicToHandle == null || magicToHandle.size() == 0) {
      return;
    }
    int[] weights = new int[magicToHandle.size()];
    for (int index = 0; index < weights.length; index++) {
      weights[index] = costs.getMagicCosts(magicToHandle.get(index), analyzer);
    }
    List<Magic> sortedMagicList = new WeightedMagicSorter().sortDescending(magicToHandle.toArray(new Magic[magicToHandle.size()]), weights);
    Set<Magic> handledMagic = new HashSet<>();
    for (Magic magic : sortedMagicList) {
      handleMagic(magic, handledMagic);
    }
  }

  private class CountVisitor implements IMagicVisitor {
    int learnCount;
    Set<Magic> handledMagic;

    public CountVisitor(Set<Magic> handledMagic) {
      this.learnCount = 0;
      this.handledMagic = handledMagic;
    }

    @Override
    public void visitCharm(Charm charm) {
      learnCount = determineLearnCount(charm, handledMagic);
    }

    @Override
    public void visitSpell(ISpell spell) {
      learnCount = 1;
    }

    public int getLearnCount() {
      return learnCount;
    }
  }

  private void handleMagic(Magic magic, Set<Magic> handledMagic) {
    int bonusPointFactor = costs.getMagicCosts(magic, analyzer);
    CountVisitor visitor = new CountVisitor(handledMagic);
    magic.accept(visitor);
    boolean favored = analyzer.isMagicFavored(magic);
    for (int timesHandled = 0; timesHandled < visitor.getLearnCount(); timesHandled++) {
      if (favored) {
        handleFavoredMagic(bonusPointFactor, magic);
      } else {
        handleGeneralMagic(bonusPointFactor, magic);
      }
    }
    handleSubeffectCharm(magic);

    handledMagic.add(magic);
  }

  private void handleSubeffectCharm(Magic magic) {
    if (!(magic instanceof Charm)) {
      return;
    }
    ISpecialCharmConfiguration specialCharmConfiguration = charms.getSpecialCharmConfiguration((Charm) magic);
    if (specialCharmConfiguration instanceof IUpgradableCharmConfiguration) {
      bonusPointsSpentForCharms += ((IUpgradableCharmConfiguration) specialCharmConfiguration).getUpgradeBPCost();
    }
    if (!(specialCharmConfiguration instanceof ISubeffectCharmConfiguration)) {
      return;
    }
    ISubeffectCharmConfiguration configuration = (ISubeffectCharmConfiguration) specialCharmConfiguration;
    int count = Math.max(0, (configuration.getCreationLearnedSubeffectCount() - 1));
    int cost = (int) Math.ceil(count * configuration.getPointCostPerEffect());
    bonusPointsSpentForCharms += cost;
  }

  private List<Magic> compileCompleteMagicList() {
    List<Magic> completeList = new ArrayList<>();
    completeList.addAll(Arrays.asList(charms.getCreationLearnedCharms()));
    completeList.addAll(Arrays.asList(spells.getLearnedSpells(false)));
    return completeList;
  }

  private void clear() {
    generalPicksSpent = 0;
    favoredPicksSpent = 0;
    bonusPointsSpentForCharms = 0;
    bonusPointsSpentForSpells = 0;
  }

  private int determineLearnCount(Charm charm, Set<Magic> handledMagic) {
    int learnCount = handleSpecialCharm(charm);
    if (charms.isAlienCharm(charm)) {
      learnCount *= 2;
    }
    for (Charm mergedCharm : charm.getMergedCharms()) {
      if (handledMagic.contains(mergedCharm) && !isSpecialCharm(charm)) {
        return 0;
      }
    }
    return learnCount;
  }

  private boolean isSpecialCharm(Charm charm) {
    return charms.getSpecialCharmConfiguration(charm) != null;
  }

  private int handleSpecialCharm(Charm charm) {
    ISpecialCharmConfiguration specialCharmConfiguration = charms.getSpecialCharmConfiguration(charm);
    if (specialCharmConfiguration != null) {
      if (specialCharmConfiguration instanceof IUpgradableCharmConfiguration) {
        return 1;
      }
      return specialCharmConfiguration.getCreationLearnCount();
    }
    return 1;
  }

  private void handleFavoredMagic(int bonusPointFactor, Magic magic) {
    if (canBuyFromFreePicks(magic) && favoredPicksSpent < favoredCreationCharmCount) {
      favoredPicksSpent++;
    } else {
      handleGeneralMagic(bonusPointFactor, magic);
    }
  }

  private boolean canBuyFromFreePicks(Magic magic) {
    return magicTemplate.canBuyFromFreePicks(magic);
  }

  private void handleGeneralMagic(final int bonusPointFactor, Magic magic) {
    if (canBuyFromFreePicks(magic) && generalPicksSpent < defaultCreationCharmCount) {
      generalPicksSpent++;
    } else {
      magic.accept(new IMagicVisitor() {
        @Override
        public void visitCharm(Charm charm) {
          bonusPointsSpentForCharms += bonusPointFactor;
        }

        @Override
        public void visitSpell(ISpell spell) {
          bonusPointsSpentForSpells += bonusPointFactor;
        }
      });
    }
  }

  public int getFavoredCharmPicksSpent() {
    return favoredPicksSpent;
  }

  public int getGeneralCharmPicksSpent() {
    return generalPicksSpent;
  }

  public int getBonusPointsSpentForCharms() {
    return bonusPointsSpentForCharms;
  }

  public int getBonusPointsSpentForSpells() {
    return bonusPointsSpentForSpells;
  }
}