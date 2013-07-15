package net.sf.anathema.hero.points.overview;

public interface IAdditionalSpendingModel extends SpendingModel {

  int getAdditionalValue();

  int getAdditionalRestrictedAlotment();

  boolean isExtensionRequired();

  int getRequiredSize();
}