package net.sf.anathema.character.presenter.overview;

public interface IAdditionalSpendingModel extends ISpendingModel {

  public int getAdditionalValue();

  public int getAdditionalRestrictedAlotment();

  public boolean isExtensionRequired();

  public int getRequiredSize();
}