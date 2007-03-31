package net.sf.anathema.character.impl.model.creation.bonus.magic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.impl.model.advance.CostAnalyzer;
import net.sf.anathema.character.impl.model.creation.bonus.IAdditionalMagicLearnPointManagement;
import net.sf.anathema.character.impl.model.creation.bonus.additional.IAdditionalBonusPointManagment;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.special.ISubeffectCharmConfiguration;

public class MagicCostCalculator {

  private final ICharmConfiguration charms;
  private final ISpellConfiguration spells;
  private final int favoredCreationCharmCount;
  private final int defaultCreationCharmCount;
  private int generalPicksSpent = 0;
  private int favoredPicksSpent = 0;
  private int bonusPointsSpentForCharms = 0;
  private final IBonusPointCosts costs;
  private CostAnalyzer analyzer;
  private final IAdditionalBonusPointManagment bonusPools;
  protected int bonusPointsSpentForSpells;
  private final IAdditionalMagicLearnPointManagement magicPools;
  private final IMagicTemplate magicTemplate;

  public MagicCostCalculator(
      IMagicTemplate magicTemplate,
      ICharmConfiguration charms,
      ISpellConfiguration spells,
      int favoredCreationCharmCount,
      int defaultCreationCharmCount,
      IBonusPointCosts costs,
      IAdditionalBonusPointManagment bonusPools,
      IAdditionalMagicLearnPointManagement magicPools,
      IBasicCharacterData basicCharacter,
      IGenericTraitCollection traitCollection) {
    this.magicTemplate = magicTemplate;
    this.charms = charms;
    this.spells = spells;
    this.favoredCreationCharmCount = favoredCreationCharmCount;
    this.defaultCreationCharmCount = defaultCreationCharmCount;
    this.costs = costs;
    this.bonusPools = bonusPools;
    this.magicPools = magicPools;
    this.analyzer = new CostAnalyzer(basicCharacter, traitCollection);
  }

  public void calculateMagicCosts() {
    clear();
    List<IMagic> magicToHandle = handleAdditionalMagicPools();
    if (magicToHandle == null || magicToHandle.size() == 0) {
      return;
    }
    int[] weights = new int[magicToHandle.size()];
    for (int index = 0; index < weights.length; index++) {
      weights[index] = costs.getMagicCosts(magicToHandle.get(index), analyzer);
    }
    List<IMagic> sortedMagicList = new WeightedMagicSorter().sortDescending(
        magicToHandle.toArray(new IMagic[magicToHandle.size()]),
        weights);
    for (IMagic magic : sortedMagicList) {
      handleMagic(magic);
    }
  }

  private void handleMagic(IMagic magic) {
    final int bonusPointFactor = costs.getMagicCosts(magic, analyzer);
    final int[] learnCount = new int[1];
    magic.accept(new IMagicVisitor() {
      public void visitCharm(ICharm charm) {
        learnCount[0] = determineLearnCount(charm);
      }

      public void visitSpell(ISpell spell) {
        learnCount[0] = 1;
      }
    });
    boolean favored = analyzer.isMagicFavored(magic);
    for (int timesHandled = 0; timesHandled < learnCount[0]; timesHandled++) {
      if (favored) {
        handleFavoredMagic(bonusPointFactor, magic);
      }
      else {
        handleGeneralMagic(bonusPointFactor, magic);
      }
    }
    handleSubeffectCharm(magic);
  }

  private void handleSubeffectCharm(IMagic magic) {
    if (!(magic instanceof ICharm)) {
      return;
    }
    ISpecialCharmConfiguration specialCharmConfiguration = charms.getSpecialCharmConfiguration((ICharm) magic);
    if (!(specialCharmConfiguration instanceof ISubeffectCharmConfiguration)) {
      return;
    }
    ISubeffectCharmConfiguration configuration = (ISubeffectCharmConfiguration) specialCharmConfiguration;
    final int count = Math.max(0, (configuration.getCreationLearnedSubeffectCount() - 1));
    int cost = (int) Math.ceil(count * configuration.getPointCostPerEffect());
    bonusPointsSpentForCharms += cost;
  }

  private List<IMagic> compileCompleteMagicList() {
    List<IMagic> completeList = new ArrayList<IMagic>();
    completeList.addAll(Arrays.asList(charms.getCreationLearnedCharms()));
    completeList.addAll(Arrays.asList(spells.getLearnedSpells(false)));
    return completeList;
  }

  private List<IMagic> handleAdditionalMagicPools() {
    List<IMagic> magicToHandle = compileCompleteMagicList();
    List<IMagic> leftOverMagic = magicPools.spendOn(magicToHandle);
    return leftOverMagic;
  }

  private void clear() {
    magicPools.clear();
    generalPicksSpent = 0;
    favoredPicksSpent = 0;
    bonusPointsSpentForCharms = 0;
    bonusPointsSpentForSpells = 0;
  }

  private int determineLearnCount(ICharm charm) {
    int learnCount = handleSpecialCharm(charm);
    if (charms.isAlienCharm(charm)) {
      learnCount *= 2;
    }
    return learnCount;
  }

  private int handleSpecialCharm(ICharm charm) {
    ISpecialCharmConfiguration specialCharmConfiguration = charms.getSpecialCharmConfiguration(charm);
    if (specialCharmConfiguration != null) {
      return specialCharmConfiguration.getCreationLearnCount();
    }
    return 1;
  }

  private void handleFavoredMagic(int bonusPointFactor, IMagic magic) {
    if (canBuyFromFreePicks(magic) && favoredPicksSpent < favoredCreationCharmCount) {
      favoredPicksSpent++;
    }
    else {
      handleGeneralMagic(bonusPointFactor, magic);
    }
  }

  private boolean canBuyFromFreePicks(IMagic magic) {
    return magicTemplate.canBuyFromFreePicks(magic);
  }

  private void handleGeneralMagic(final int bonusPointFactor, IMagic magic) {
    if (canBuyFromFreePicks(magic) && generalPicksSpent < defaultCreationCharmCount) {
      generalPicksSpent++;
    }
    else {
      bonusPools.spendOn(magic, bonusPointFactor);
      magic.accept(new IMagicVisitor() {
        public void visitCharm(ICharm charm) {
          bonusPointsSpentForCharms += bonusPointFactor;
        }

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

  public int getAdditionalPointsSpent() {
    return magicPools.getPointsSpent();
  }
}