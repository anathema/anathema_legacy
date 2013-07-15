package net.sf.anathema.hero.advance.experience;

import net.sf.anathema.hero.points.overview.IValueModel;

import java.util.List;

public interface ExperiencePointManagement {

  int getTotalCosts();

  List<IValueModel<Integer>> getAllModels();
}