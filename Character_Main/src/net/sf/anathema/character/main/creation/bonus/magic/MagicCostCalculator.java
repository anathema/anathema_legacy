package net.sf.anathema.character.main.creation.bonus.magic;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.IMagic;
import net.sf.anathema.character.main.magic.IMagicVisitor;
import net.sf.anathema.character.main.magic.ISpell;
import net.sf.anathema.character.main.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.magic.IMagicTemplate;
import net.sf.anathema.character.main.model.charms.CharmsModel;
import net.sf.anathema.character.main.model.charms.CharmsModelFetcher;
import net.sf.anathema.character.main.model.spells.SpellModel;
import net.sf.anathema.character.main.model.spells.SpellsModelFetcher;
import net.sf.anathema.character.main.advance.CostAnalyzer;
import net.sf.anathema.character.main.charm.special.ISubeffectCharmConfiguration;
import net.sf.anathema.character.main.charm.special.IUpgradableCharmConfiguration;
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
    List<IMagic> magicToHandle = compileCompleteMagicList();
    if (magicToHandle == null || magicToHandle.size() == 0) {
      return;
    }
    int[] weights = new int[magicToHandle.size()];
    for (int index = 0; index < weights.length; index++) {
      weights[index] = costs.getMagicCosts(magicToHandle.get(index), analyzer);
    }
    List<IMagic> sortedMagicList = new WeightedMagicSorter().sortDescending(magicToHandle.toArray(new IMagic[magicToHandle.size()]), weights);
    Set<IMagic> handledMagic = new HashSet<>();
    for (IMagic magic : sortedMagicList) {
      handleMagic(magic, handledMagic);
    }
  }

  private class CountVisitor implements IMagicVisitor {
    int learnCount;
    Set<IMagic> handledMagic;

    public CountVisitor(Set<IMagic> handledMagic) {
      this.learnCount = 0;
      this.handledMagic = handledMagic;
    }

    @Override
    public void visitCharm(ICharm charm) {
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

  private void handleMagic(IMagic magic, Set<IMagic> handledMagic) {
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

  private void handleSubeffectCharm(IMagic magic) {
    if (!(magic instanceof ICharm)) {
      return;
    }
    ISpecialCharmConfiguration specialCharmConfiguration = charms.getSpecialCharmConfiguration((ICharm) magic);
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

  private List<IMagic> compileCompleteMagicList() {
    List<IMagic> completeList = new ArrayList<>();
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

  private int determineLearnCount(ICharm charm, Set<IMagic> handledMagic) {
    int learnCount = handleSpecialCharm(charm);
    if (charms.isAlienCharm(charm)) {
      learnCount *= 2;
    }
    for (ICharm mergedCharm : charm.getMergedCharms()) {
      if (handledMagic.contains(mergedCharm) && !isSpecialCharm(charm)) {
        return 0;
      }
    }
    return learnCount;
  }

  private boolean isSpecialCharm(ICharm charm) {
    return charms.getSpecialCharmConfiguration(charm) != null;
  }

  private int handleSpecialCharm(ICharm charm) {
    ISpecialCharmConfiguration specialCharmConfiguration = charms.getSpecialCharmConfiguration(charm);
    if (specialCharmConfiguration != null) {
      if (specialCharmConfiguration instanceof IUpgradableCharmConfiguration) {
        return 1;
      }
      return specialCharmConfiguration.getCreationLearnCount();
    }
    return 1;
  }

  private void handleFavoredMagic(int bonusPointFactor, IMagic magic) {
    if (canBuyFromFreePicks(magic) && favoredPicksSpent < favoredCreationCharmCount) {
      favoredPicksSpent++;
    } else {
      handleGeneralMagic(bonusPointFactor, magic);
    }
  }

  private boolean canBuyFromFreePicks(IMagic magic) {
    return magicTemplate.canBuyFromFreePicks(magic);
  }

  private void handleGeneralMagic(final int bonusPointFactor, IMagic magic) {
    if (canBuyFromFreePicks(magic) && generalPicksSpent < defaultCreationCharmCount) {
      generalPicksSpent++;
    } else {
      magic.accept(new IMagicVisitor() {
        @Override
        public void visitCharm(ICharm charm) {
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