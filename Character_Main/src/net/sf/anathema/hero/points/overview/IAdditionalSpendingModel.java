package net.sf.anathema.hero.points.overview;

public interface IAdditionalSpendingModel extends ISpendingModel {

  int getAdditionalValue();

  int getAdditionalRestrictedAlotment();

  boolean isExtensionRequired();

  int getRequiredSize();
}