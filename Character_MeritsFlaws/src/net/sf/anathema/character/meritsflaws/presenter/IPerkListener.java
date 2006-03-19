package net.sf.anathema.character.meritsflaws.presenter;

public interface IPerkListener {

  public void perkAdded(Object selectedPerk, Object detailsView);

  public void perkSelected(Object perk);

  public void filterChanged(Object type, Object category);

  public void perkRemoved(Object perkSelection);

  public void selectionSelected(Object perkSelection);
}