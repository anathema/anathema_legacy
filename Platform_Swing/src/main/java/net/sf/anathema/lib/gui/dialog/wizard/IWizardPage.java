package net.sf.anathema.lib.gui.dialog.wizard;

import net.sf.anathema.lib.gui.dialog.core.IPage;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.message.IBasicMessage;

public interface IWizardPage extends IPage {

  IBasicMessage getMessage();

  IPageContent getPageContent();

  boolean canFlipToNextPage();

  IWizardPage getNextPage();

  IWizardPage getPreviousPage();
}