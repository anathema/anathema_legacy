package net.sf.anathema.character.presenter.overview;

public interface IAdditionalSpendingModel extends ISpendingModel {

  int getAdditionalValue();

  int getAdditionalRestrictedAlotment();

  boolean isExtensionRequired();

  int getRequiredSize();
}