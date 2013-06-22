package net.sf.anathema.character.model.advance;

import net.sf.anathema.character.presenter.overview.IValueModel;

import java.util.List;

public interface IExperiencePointManagement {

  int getMiscGain();

  int getTotalCosts();

  List<IValueModel<Integer>> getAllModels();
}