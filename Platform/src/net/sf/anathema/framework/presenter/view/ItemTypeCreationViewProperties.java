package net.sf.anathema.framework.presenter.view;

import javax.swing.Icon;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class ItemTypeCreationViewProperties implements IItemTypeCreationViewProperties {

  private final Icon icon;
  private final IWizardFactory factory;
  private final IItemType type;

  public ItemTypeCreationViewProperties(IItemType type, Icon icon, IWizardFactory factory) {
    this.type = type;
    this.icon = icon;
    this.factory = factory;
  }

  public Icon getIcon() {
    return icon;
  }

  public IWizardFactory getNewItemWizardFactory() {
    return factory;
  }

  public String getLabelKey() {
    return "ItemType." + type.getId() + ".PrintName"; //$NON-NLS-1$ //$NON-NLS-2$
  }
}