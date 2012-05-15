package net.sf.anathema.lib.gui.dialog.wizard;

import net.sf.anathema.lib.gui.dialog.core.IDialogContainer;

/**
 * Interface for containers that can host a wizard. It displays wizard pages, at most one of which
 * is considered the current page. <code>getCurrentPage</code> returns the current page; 
 * <code>showPage</code> programmatically changes the the current page. 
 *
 *The class {@link WizardDialog} provides a fully functional implementation of this interface which
 *will meet the needs of most clients. However, clients are also free to implement this interface if
 *{@link WizardDialog} does not suit their needs.
 *
 */
public interface IWizardContainer extends IDialogContainer {

  public IWizardPage getCurrentPage();
}