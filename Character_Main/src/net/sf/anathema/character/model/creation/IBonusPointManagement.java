package net.sf.anathema.character.model.creation;

import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.hero.points.overview.IAdditionalSpendingModel;
import net.sf.anathema.hero.points.overview.IOverviewModel;
import net.sf.anathema.hero.points.overview.ISpendingModel;

public interface IBonusPointManagement {

  void recalculate();

  ISpendingModel getDefaultAbilityModel();

  ISpendingModel getFavoredAbilityModel();

  ISpendingModel getFavoredAbilityPickModel();

  ISpendingModel getFavoredCharmModel();

  IAdditionalSpendingModel getDefaultCharmModel();

  IAdditionalSpendingModel getTotalModel();

  IOverviewModel[] getAllModels();
}