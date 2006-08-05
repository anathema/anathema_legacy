package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.item.repository.creation.IItemCreationTemplate;
import net.sf.anathema.lib.gui.wizard.IAnathemaWizardPage;

public interface IWizardFactory {

  public IAnathemaWizardPage createPage(IItemCreationTemplate template);

  public IItemCreationTemplate createTemplate();
}