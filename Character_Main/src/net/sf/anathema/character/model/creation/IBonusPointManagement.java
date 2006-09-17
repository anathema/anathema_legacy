package net.sf.anathema.character.model.creation;

import net.sf.anathema.character.presenter.overview.IAdditionalSpendingModel;
import net.sf.anathema.character.presenter.overview.IOverviewModel;

public interface IBonusPointManagement {

  public void recalculate();

  public IAdditionalSpendingModel getTotalModel();

  public IOverviewModel[] getAllModels();
}