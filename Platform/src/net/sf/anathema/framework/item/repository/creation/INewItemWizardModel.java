package net.sf.anathema.framework.item.repository.creation;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface INewItemWizardModel {

  public void setSelectedValue(IItemType newValue);

  public IItemType getSelectedItemType();

  public IItemType[] getRegisteredItemTypes();

  public void addListener(IChangeListener inputListener);

  public IItemCreationTemplate getTemplate(IItemType type);
}