package net.sf.anathema.character.model.advance;

import net.sf.anathema.character.presenter.overview.IValueModel;

public interface IExperiencePointManagement {

  int getMiscGain();

  int getTotalCosts();

  IValueModel<Integer>[] getAllModels();
}