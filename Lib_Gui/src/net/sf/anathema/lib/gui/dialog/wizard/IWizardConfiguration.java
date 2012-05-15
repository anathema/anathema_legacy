package net.sf.anathema.lib.gui.dialog.wizard;

import net.sf.anathema.lib.gui.dialog.core.IGenericDialogConfiguration;

import java.awt.Component;

/**
 * Interface for a wizard. A wizard maintains a list of wizard pages, stacked on top of each other
 * in card layout fashion.
 * 
 * The class Wizard provides an abstract implementation of this interface. However, clients are
 * also free to implement this interface if Wizard does not suit their needs. 
 * 
 * @see AbstractWizardConfiguration */
public interface IWizardConfiguration extends IGenericDialogConfiguration {

  /**
   * Returns the first page to be shown in this wizard.
   * 
   * @return the first wizard page
   */
  public IWizardPage getStartingPage();

  /**
   * Returns the container of this wizard.
   * 
   * @return the wizard container, or <code>null</code> if this wizard has yet to be added to a
   *         container
   */
  public IWizardContainer getContainer();

  /**
   * Sets or clears the container of this wizard.
   * 
   * @param wizardContainer the wizard container, or <code>null</code>
   */
  public void setContainer(IWizardContainer wizardContainer);

  /**
   * Returns whether this wizard could be finished without further user interaction. The result of
   * this method is typically used by the wizard container to enable or disable the Finish button.
   * 
   * @return <code>true</code> if the wizard could be finished, and <code>false</code> otherwise
   */
  public boolean canFinish();

  /**
   * Returns whether this wizard could be canceled without further user interaction. The result of
   * this method is typically used by the wizard container to enable or disable the Cancel button.
   * 
   * @return <code>true</code> if the wizard could be canceled, and <code>false</code> otherwise
   */
  public boolean canCancel();

  /**
   * Returns whether help is available for this wizard. The result of this method is typically used
   * by the container to show or hide the Help button.
   * 
   * @return <code>true</code> if help is available, and <code>false</code> if this wizard is
   *         helpless
   */
  public boolean isHelpAvailable();

  /** Adds any last-minute pages to this wizard.
   * This method is called just before the wizard becomes visible, to give the wizard the
   * opportunity to add any lazily created pages.*/
  public void addPages();

  /** Returns the successor of the given page.
   * This method is typically called by a wizard page
   * @param page the page
   * @return the next page, or null if none */
  public IWizardPage getNextPage(IWizardPage page);

  /** Returns the predecessor of the given page.
   * This method is typically called by a wizard page
   * @param page the page
   * @return the previous page, or null if none */

  public IWizardPage getPreviousPage(IWizardPage page);

  /** Performs any actions appropriate in response to the user having pressed the Finish button, or
   * refuse if finishing now is not permitted. Normally this method is only called on the
   * container's current wizard. However if the current wizard is a nested wizard this method will
   * also be called on all wizards in its parent chain. Such parents may use this notification to
   * save state etc.
   * 
   * However, the value the parents return from this method is ignored.
   * @return true to indicate the finish request was accepted, and false to indicate that
   * the finish request was refused
   * @deprecated as of 29.03.2006 (sieroux), replaced by model/view separation
   */
  @Deprecated
  public boolean performFinish(Component parentComponent);

  /**
   * Returns whether the wizard container shall initialize the wizard pages after creating them from
   * the data. Usually this method will return true if the wizard is started with an already
   * preconfigured model and false otherwise.
   * @see IWizardPage#initializeFromData() 
   */
  public boolean shallInitializePagesFromData();
}