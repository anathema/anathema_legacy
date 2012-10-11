package net.sf.anathema.lib.gui.dialog.core;

public interface IPage {
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

  /** Called from the dialog container when the page is entered. */
  void enter();

  /** Called from the dialog container when the page is left. */
  void leave();
}