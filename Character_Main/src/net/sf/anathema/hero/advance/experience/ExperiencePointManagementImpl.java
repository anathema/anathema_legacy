package net.sf.anathema.hero.advance.experience;

import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.points.PointModelFetcher;
import net.sf.anathema.hero.points.PointsModel;
import net.sf.anathema.hero.points.overview.IValueModel;

import java.util.ArrayList;
import java.util.List;

public class ExperiencePointManagementImpl implements ExperiencePointManagement {

  private final Hero hero;

  public ExperiencePointManagementImpl(Hero hero) {
    this.hero = hero;
  }

  @Override
  public List<IValueModel<Integer>> getAllModels() {
    final List<IValueModel<Integer>> allModels = new ArrayList<>();
    // todo (sandra): Sorting mechanism for the value models
    PointsModel pointsModel = PointModelFetcher.fetch(hero);
    for (IValueModel<Integer>  model : pointsModel.getExperienceOverviewModels()) {
      allModels.add(model);
    }
    return allModels;
  }

  @Override
  public int getTotalCosts() {
    int experienceCosts = 0;
    for (IValueModel<Integer> model : getAllModels()) {
      experienceCosts += model.getValue();
    }
    return experienceCosts;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (IValueModel<Integer> model : getAllModels()) {
      builder.append(model.getCategoryId());
      builder.append(": ");
      builder.append(model.getValue());
    }
    builder.append("Overall: ");
    builder.append(getTotalCosts());
    return builder.toString();
  }
}