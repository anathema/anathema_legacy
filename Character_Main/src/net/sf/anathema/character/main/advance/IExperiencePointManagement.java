package net.sf.anathema.character.main.advance;

import net.sf.anathema.hero.points.overview.IValueModel;

import java.util.List;

public interface IExperiencePointManagement {

  int getMiscGain();

  int getTotalCosts();

  List<IValueModel<Integer>> getAllModels();
}