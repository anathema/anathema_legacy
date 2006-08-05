package net.sf.anathema.demo.platform.item.model;

import net.sf.anathema.demo.platform.item.CreationItemType;
import net.sf.anathema.demo.platform.item.INewItemWizardModel;

public class NewItemWizardModel implements INewItemWizardModel {

  private CreationItemType value;

  public CreationItemType getSelectedValue() {
    return value;
  }

  public void setSelectedValue(CreationItemType value) {
    this.value = value;
  }

  public CreationItemType[] getRegisteredItemTypes() {
    return new CreationItemType[] { new CreationItemType(), new CreationItemType() };
  }
}