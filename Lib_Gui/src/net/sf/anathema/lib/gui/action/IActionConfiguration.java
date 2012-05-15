package net.sf.anathema.lib.gui.action;

import javax.swing.Icon;

public interface IActionConfiguration {

  Icon getIcon();

  String getName();

  /** @return the tooltip text for the combo box or <code>null</code> if none. */
  String getToolTipText();

}