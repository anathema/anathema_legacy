package net.sf.anathema.framework.module;

import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;
import net.sf.anathema.lib.workflow.wizard.selection.IAnathemaWizardModelTemplate;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class NullWizardPageFactory implements IWizardFactory {

  @Override
  public IAnathemaWizardPage createPage(IAnathemaWizardModelTemplate template) {
    return null;
  }

  @Override
  public IAnathemaWizardModelTemplate createTemplate() {
    return null;
  }

  @Override
  public boolean needsFurtherDetails() {
    return false;
  }
}