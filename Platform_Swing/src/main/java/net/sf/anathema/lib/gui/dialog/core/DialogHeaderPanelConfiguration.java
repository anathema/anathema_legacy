package net.sf.anathema.lib.gui.dialog.core;

public class DialogHeaderPanelConfiguration implements IDialogHeaderPanelConfiguration {

  public static IDialogHeaderPanelConfiguration createVisible() {
    return new DialogHeaderPanelConfiguration(true);
  }

  public static IDialogHeaderPanelConfiguration createInvisible() {
    return new DialogHeaderPanelConfiguration(false);
  }

  private final boolean visible;

  private DialogHeaderPanelConfiguration(boolean visible) {
    this.visible = visible;
  }

  @Override
  public boolean isHeaderPanelVisible() {
    return visible;
  }
}