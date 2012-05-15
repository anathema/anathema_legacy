package net.sf.anathema.lib.gui.dialog.core;

import javax.swing.Icon;
import javax.swing.JToolBar;

public class DialogHeaderPanelConfiguration implements IDialogHeaderPanelConfiguration {

  public static IDialogHeaderPanelConfiguration createVisibleWithoutIcon() {
    return new DialogHeaderPanelConfiguration(null, true);
  }

  public static IDialogHeaderPanelConfiguration createInvisible() {
    return new DialogHeaderPanelConfiguration(null, false);
  }

  private final Icon icon;
  private final boolean visible;
  private final JToolBar toolBar;

  private DialogHeaderPanelConfiguration(Icon icon, boolean visible) {
    this(icon, visible, null);
  }

  private DialogHeaderPanelConfiguration(Icon icon, boolean visible, JToolBar toolBar) {
    this.icon = icon;
    this.visible = visible;
    this.toolBar = toolBar;
  }

  @Override
  public Icon getLargeDialogIcon() {
    return icon;
  }

  @Override
  public boolean isHeaderPanelVisible() {
    return visible;
  }

  @Override
  public JToolBar getToolBar() {
    return toolBar;
  }
}