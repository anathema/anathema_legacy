package net.sf.anathema.lib.gui.action;

import javax.swing.Icon;

public interface IActionConfiguration {

  public Icon getIcon();

  public String getName();

  /** @return the tooltip text for the combo box or <code>null</code> if none. */
  public String getToolTipText();

}