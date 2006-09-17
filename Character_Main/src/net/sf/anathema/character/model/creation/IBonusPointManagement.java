package net.sf.anathema.character.model.creation;

import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.presenter.overview.IAdditionalSpendingModel;
import net.sf.anathema.character.presenter.overview.IOverviewModel;
import net.sf.anathema.character.presenter.overview.ISpendingModel;

public interface IBonusPointManagement {

  public void recalculate();

  public ISpendingModel getBackgroundModel();

  public ISpendingModel getDefaultAbilityModel();

  public ISpendingModel getFavoredAbilityModel();

  public ISpendingModel getFavoredAbilityPickModel();

  public ISpendingModel getAttributeModel(AttributeGroupPriority priority);

  public ISpendingModel getFavoredCharmModel();

  public IAdditionalSpendingModel getDefaultCharmModel();

  public IAdditionalSpendingModel getTotalModel();

  public IOverviewModel[] getAllModels();
}