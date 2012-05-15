package net.sf.anathema.lib.workflow.wizard.selection;

import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;

public interface IWizardFactory {

  boolean needsFurtherDetails();

  IAnathemaWizardPage createPage(IAnathemaWizardModelTemplate template);

  IAnathemaWizardModelTemplate createTemplate();
}