package net.sf.anathema.lib.gui.dialog.core;

import javax.swing.Icon;
import javax.swing.JToolBar;

public interface IDialogHeaderPanelConfiguration {

  /** @return whether the header panel in the dialog shall be visible or not. */
  public boolean isHeaderPanelVisible();

  /** An optional icon of 75x66 pixels for the top right corner of the dialog header panel.
   * The Icon should be transparent, because the framework will add a decent background. 
   * @return An icon or <code>null</code> if none. */
  public Icon getLargeDialogIcon();

  public JToolBar getToolBar();

}