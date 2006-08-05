package net.sf.anathema.demo.platform.item;

public interface INewItemWizardModel {

  public void setSelectedValue(CreationItemType newValue);

  public CreationItemType getSelectedValue();

  public CreationItemType[] getRegisteredItemTypes();
}