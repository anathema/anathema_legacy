package net.sf.anathema.lib.gui.wizard;

import net.disy.commons.swing.dialog.wizard.IBasicWizardPage;
import net.sf.anathema.lib.gui.IPresenter;

public interface IWizardPresenter extends IPresenter {

  public IBasicWizardPage getStartPage();
}