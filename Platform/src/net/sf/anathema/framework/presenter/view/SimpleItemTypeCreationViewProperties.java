package net.sf.anathema.framework.presenter.view;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.module.NullWizardPageFactory;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileScanner;
import net.sf.anathema.framework.repository.access.printname.NullPrintNameFileScanner;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class SimpleItemTypeCreationViewProperties extends AbstractItemTypeCreationViewProperties {

  private final IObjectUi ui;

  public SimpleItemTypeCreationViewProperties(IItemType type, Icon icon) {
    super(type, icon);
    this.ui = new SimpleItemTypeUi(icon);
  }

  public IObjectUi getItemTypeUI() {
    return ui;
  }

  public IWizardFactory getNewItemWizardFactory() {
    return new NullWizardPageFactory();
  }

  public IPrintNameFileScanner getScanner() {
    return new NullPrintNameFileScanner();
  }
}