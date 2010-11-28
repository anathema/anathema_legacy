package net.sf.anathema.framework.presenter.view;

import javax.swing.Icon;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.module.NullWizardPageFactory;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class SimpleItemTypeViewProperties extends AbstractItemTypeViewProperties {

  public SimpleItemTypeViewProperties(IItemType type, Icon icon) {
    super(type, icon, new SimpleItemTypeUi(icon));
  }

  public IWizardFactory getNewItemWizardFactory() {
    return new NullWizardPageFactory();
  }
}