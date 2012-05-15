package net.sf.anathema.lib.gui.dialog.core;

import net.sf.anathema.lib.gui.dialog.input.RequestFinishListener;
import net.sf.anathema.lib.gui.dialog.wizard.IWizardConfiguration;

public interface IPage {

  void addRequestFinishListener(RequestFinishListener requestFinishListener);

  void removeRequestFinishListener(RequestFinishListener requestFinishListener);

  /** Returns this dialog page's description text.
   * @return the description text for this dialog page, or <code>null</code> if none */
  String getDescription();

  /** Returns whether this page is complete or not.
   * 
   * This information is typically used by the wizard to decide when it is okay to finish.
   * @return <code>true</code> if this page is complete, and <code>false</code> otherwise */
  boolean canFinish();

  /** Returns this dialog page's title.
   @return the title of this dialog page, or <code>null</code> if none */
  String getTitle();

  /** Returns a help handler than will be used for handling help requests (e.g. when the user clicks
   * the help button on this page). If there is no help available, return <code>null</code>. In this case 
   * the page container will typicalle disable or remove the help button.
   * Note that for wizards the help button will only by visible if the coresponding wizard
   * configuration returns <code>true</code> in {@link IWizardConfiguration#isHelpAvailable()}.
   *  
   * @return A handler if help is available or <code>null</code> if no help is supported for this page. */
  IDialogHelpHandler getHelpHandler();

  /** Called from the dialog container when the page is entered. */
  void enter();

  /** Called from the dialog container when the page is left. */
  void leave();
}