package net.sf.anathema.lib.gui.dialog.core;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.IDialogButtonConfiguration;

public abstract class AbstractGenericDialogConfiguration implements IGenericDialogConfiguration {

  private final IDialogButtonConfiguration buttonConfiguration;
  private final IDialogHeaderPanelConfiguration headerPanelConfiguration;

  public AbstractGenericDialogConfiguration(IDialogButtonConfiguration buttonConfiguration,
                                            IDialogHeaderPanelConfiguration headerPanelConfiguration) {
    Preconditions.checkNotNull(buttonConfiguration);
    Preconditions.checkNotNull(headerPanelConfiguration);
    this.headerPanelConfiguration = headerPanelConfiguration;
    this.buttonConfiguration = buttonConfiguration;
  }

  @Override
  public final IDialogButtonConfiguration getButtonConfiguration() {
    return buttonConfiguration;
  }

  @Override
  public IDialogHeaderPanelConfiguration getHeaderPanelConfiguration() {
    return headerPanelConfiguration;
  }
}