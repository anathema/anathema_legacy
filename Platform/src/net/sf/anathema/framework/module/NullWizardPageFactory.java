package net.sf.anathema.framework.module;

import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class NullWizardPageFactory implements IWizardFactory {

  public IAnathemaWizardPage createPage(IAnathemaWizardModelTemplate template) {
    return null;
  }

  public IAnathemaWizardModelTemplate createTemplate() {
    return null;
  }

  public boolean needsFurtherDetails() {
    return false;
  }
}