package net.sf.anathema.campaign.perspective;

import net.sf.anathema.framework.module.NullWizardPageFactory;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.workflow.wizard.selection.IWizardFactory;

public class NullWizardPageRegistry extends Registry<PrintNameFile, IWizardFactory> {
  @Override
  public IWizardFactory get(PrintNameFile id) {
    return new NullWizardPageFactory();
  }
}