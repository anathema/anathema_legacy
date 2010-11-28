package net.sf.anathema.character.model.advance;

import net.sf.anathema.character.presenter.overview.IValueModel;

public interface IExperiencePointManagement {

  public int getMiscGain();

  public int getTotalCosts();

  public IValueModel<Integer>[] getAllModels();
}