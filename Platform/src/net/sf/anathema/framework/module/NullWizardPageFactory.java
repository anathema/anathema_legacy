package net.sf.anathema.framework.module;

import net.sf.anathema.framework.item.repository.creation.IItemCreationTemplate;
import net.sf.anathema.framework.presenter.IWizardFactory;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;

public class NullWizardPageFactory implements IWizardFactory {

  public IAnathemaWizardPage createPage(IItemCreationTemplate template) {
    return null;
  }

  public IItemCreationTemplate createTemplate() {
    return null;
  }
}