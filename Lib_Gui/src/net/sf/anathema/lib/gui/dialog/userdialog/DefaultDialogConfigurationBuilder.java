package net.sf.anathema.lib.gui.dialog.userdialog;

import net.sf.anathema.lib.gui.dialog.core.DialogHeaderPanelConfiguration;
import net.sf.anathema.lib.gui.dialog.core.IDialogHeaderPanelConfiguration;
import net.sf.anathema.lib.gui.dialog.core.IDialogResult;
import net.sf.anathema.lib.gui.dialog.core.IVetoDialogCloseHandler;
import net.sf.anathema.lib.gui.dialog.core.preferences.IDialogPreferences;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.DialogButtonConfigurationFactory;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.IDialogButtonConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;

import javax.swing.JComponent;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class DefaultDialogConfigurationBuilder<P extends IDialogPage> {

  private final P dialogPage;
  private IDialogButtonConfiguration buttonConfiguration = DialogButtonConfigurationFactory.createOkCancel();
  private IDialogHeaderPanelConfiguration headerPanelConfiguration = DialogHeaderPanelConfiguration.createVisibleWithoutIcon();
  private Dimension customizedPreferedSize = null;
  private IDialogPreferences preferences = null;
  private final List<JComponent> additionalButtons = new ArrayList<JComponent>();
  private IVetoDialogCloseHandler vetoDialogCloseHander = new IVetoDialogCloseHandler() {

    @Override
    public boolean handleDialogAboutToClose(IDialogResult result, Component parentComponent) {
      return true;
    }
  };

  public DefaultDialogConfigurationBuilder(P dialogPage) {
    this.dialogPage = dialogPage;
  }

  public DefaultDialogConfiguration<P> build() {
    return new DefaultDialogConfiguration<P>(dialogPage, buttonConfiguration, headerPanelConfiguration,
            customizedPreferedSize, preferences, additionalButtons.toArray(new JComponent[additionalButtons.size()]),
            vetoDialogCloseHander);
  }

  public void setButtonConfiguration(IDialogButtonConfiguration buttonConfiguration) {
    this.buttonConfiguration = buttonConfiguration;
  }
}
