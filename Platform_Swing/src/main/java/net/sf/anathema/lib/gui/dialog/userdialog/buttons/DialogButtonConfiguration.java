package net.sf.anathema.lib.gui.dialog.userdialog.buttons;

import net.sf.anathema.lib.gui.action.ActionConfiguration;
import net.sf.anathema.lib.gui.action.IActionConfiguration;

public class DialogButtonConfiguration implements IDialogButtonConfiguration {

  static final ActionConfiguration DEFAULT_CANCEL_CONFIG = new ActionConfiguration(DialogMessages.CANCEL);
  static final ActionConfiguration DEFAULT_OK_CONFIG = new ActionConfiguration(DialogMessages.OK);
  private final IActionConfiguration okConfiguration;
  private final IActionConfiguration cancelConfiguration;

  public DialogButtonConfiguration() {
    this(DEFAULT_OK_CONFIG);
  }

  public DialogButtonConfiguration(IActionConfiguration okConfiguration) {
    this(okConfiguration, DEFAULT_CANCEL_CONFIG);
  }

  public DialogButtonConfiguration(IActionConfiguration okConfiguration, IActionConfiguration cancelConfiguration) {
    this.okConfiguration = okConfiguration;
    this.cancelConfiguration = cancelConfiguration;
  }

  @Override
  public IActionConfiguration getOkActionConfiguration() {
    return okConfiguration;
  }

  @Override
  public IActionConfiguration getCancelActionConfiguration() {
    return cancelConfiguration;
  }
}