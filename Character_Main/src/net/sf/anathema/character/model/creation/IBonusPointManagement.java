package net.sf.anathema.character.model.creation;

import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.presenter.overview.IAdditionalSpendingModel;
import net.sf.anathema.character.presenter.overview.IOverviewModel;
import net.sf.anathema.character.presenter.overview.ISpendingModel;

public interface IBonusPointManagement {

  void recalculate();

  ISpendingModel getBackgroundModel();

  ISpendingModel getDefaultAbilityModel();

  ISpendingModel getFavoredAbilityModel();

  ISpendingModel getFavoredAbilityPickModel();

  ISpendingModel getAttributeModel(AttributeGroupPriority priority);

  ISpendingModel getFavoredCharmModel();

  IAdditionalSpendingModel getDefaultCharmModel();

  IAdditionalSpendingModel getTotalModel();

  IOverviewModel[] getAllModels();
}