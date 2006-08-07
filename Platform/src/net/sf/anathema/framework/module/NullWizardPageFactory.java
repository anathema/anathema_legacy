package net.sf.anathema.framework.module;

import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.workflow.wizard.selection.IItemCreationTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class NullWizardPageFactory implements IWizardFactory {

  public IAnathemaWizardPage createPage(IItemCreationTemplate template) {
    return null;
  }

  public IItemCreationTemplate createTemplate() {
    return null;
  }

  public boolean needsFurtherDetails() {
    return false;
  }
}