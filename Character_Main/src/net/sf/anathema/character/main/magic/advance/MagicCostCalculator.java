package net.sf.anathema.character.main.magic.advance;

import net.sf.anathema.character.main.advance.CostAnalyzer;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.hero.model.Hero;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MagicCostCalculator {

  private final int favoredCreationMagicCount;
  private final int defaultCreationMagicCount;
  private final MagicCostEvaluator magicCostEvaluator;
  private final BonusPointCosts costs;
  private final CostAnalyzer analyzer;
  private int generalPicksSpent = 0;
  private int favoredPicksSpent = 0;
  protected int bonusPointsSpent = 0;

  public MagicCostCalculator(Hero hero, BonusPointCosts costs) {
    this.magicCostEvaluator = new MagicCostEvaluator(hero);
    this.favoredCreationMagicCount = hero.getTemplate().getCreationPoints().getFavoredCreationMagicCount();
    this.defaultCreationMagicCount = hero.getTemplate().getCreationPoints().getDefaultCreationMagicCount();
    this.costs = costs;
    this.analyzer = new CostAnalyzer(hero);
  }

  public void calculateMagicCosts() {
    clear();
    List<Magic> magicToHandle = magicCostEvaluator.compileCompleteMagicList();
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

  private void clear() {
    generalPicksSpent = 0;
    favoredPicksSpent = 0;
    bonusPointsSpent = 0;
  }

  private void handleMagic(Magic magic, Set<Magic> handledMagic) {
    int bonusPointFactor = costs.getMagicCosts(magic, analyzer);
    boolean favored = analyzer.isMagicFavored(magic);
    int learnCount = magicCostEvaluator.getLearnCount(magic, handledMagic);
    for (int timesHandled = 0; timesHandled < learnCount; timesHandled++) {
      if (favored) {
        handleFavoredMagic(bonusPointFactor);
      } else {
        handleGeneralMagic(bonusPointFactor);
      }
    }
    bonusPointsSpent += magicCostEvaluator.getAdditionalBonusPoints(magic);
    handledMagic.add(magic);
  }

  private void handleFavoredMagic(int bonusPointFactor) {
    if (favoredPicksSpent < favoredCreationMagicCount) {
      favoredPicksSpent++;
    } else {
      handleGeneralMagic(bonusPointFactor);
    }
  }

  private void handleGeneralMagic(final int bonusPointFactor) {
    if (generalPicksSpent < defaultCreationMagicCount) {
      generalPicksSpent++;
    } else {
      bonusPointsSpent += bonusPointFactor;
    }
  }

  public int getBonusPointsSpent() {
    return bonusPointsSpent;
  }

  public int getFavoredCharmPicksSpent() {
    return favoredPicksSpent;
  }

  public int getGeneralCharmPicksSpent() {
    return generalPicksSpent;
  }
}