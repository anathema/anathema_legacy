package net.sf.anathema.hero.points;

import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.points.overview.IOverviewModel;
import net.sf.anathema.hero.points.overview.IValueModel;
import net.sf.anathema.hero.points.overview.WeightedCategory;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PointModelImpl implements PointsModel {

  private final List<IValueModel<Integer>> experienceOverviewModels = new ArrayList<>();
  private final List<HeroBonusPointCalculator> bonusPointCalculators = new ArrayList<>();
  private final List<IOverviewModel> bonusOverviewModels = new ArrayList<>();
  private final List<WeightedCategory> bonusCategories = new ArrayList<>();

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    // nothing to do
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    // nothing to do
  }

  @Override
  public void addBonusPointCalculator(HeroBonusPointCalculator calculator) {
    bonusPointCalculators.add(calculator);
  }

  @Override
  public void addBonusCategory(WeightedCategory category) {
    bonusCategories.add(category);
    Collections.sort(bonusCategories);
  }

  @Override
  public void addToBonusOverview(IOverviewModel bonusPointModel) {
    bonusOverviewModels.add(bonusPointModel);
  }

  @Override
  public void addToExperienceOverview(IValueModel<Integer> model) {
    experienceOverviewModels.add(model);
  }

  @Override
  public Iterable<HeroBonusPointCalculator> getBonusPointCalculators() {
    return bonusPointCalculators;
  }

  @Override
  public Iterable<IValueModel<Integer>> getExperienceOverviewModels() {
    return experienceOverviewModels;
  }

  @Override
  public Iterable<IOverviewModel> getBonusOverviewModels() {
    return bonusOverviewModels;
  }

  @Override
  public Iterable<WeightedCategory> getBonusCategories() {
    return bonusCategories;
  }

  @Override
  public Identifier getId() {
    return ID;
  }
}
