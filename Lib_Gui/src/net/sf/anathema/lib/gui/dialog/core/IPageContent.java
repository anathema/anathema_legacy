package net.sf.anathema.lib.gui.dialog.core;

import net.sf.anathema.lib.gui.container.IComponentContainer;

public interface IPageContent extends IComponentContainer {

  /** Set the focus to the first input control. Called by the dialog before showing this page the
   * first time. Usually the page calls the <code>requestFocus()</code> on its first input widget.*/
  void requestFocus();
}