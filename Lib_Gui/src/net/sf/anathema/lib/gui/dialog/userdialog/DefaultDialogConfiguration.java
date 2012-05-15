package net.sf.anathema.lib.gui.dialog.userdialog;

import net.sf.anathema.lib.gui.dialog.core.AbstractGenericDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.core.DialogHeaderPanelConfiguration;
import net.sf.anathema.lib.gui.dialog.core.IDialogHeaderPanelConfiguration;
import net.sf.anathema.lib.gui.dialog.core.IDialogResult;
import net.sf.anathema.lib.gui.dialog.core.IVetoDialogCloseHandler;
import net.sf.anathema.lib.gui.dialog.core.preferences.IDialogPreferences;
import net.sf.anathema.lib.gui.dialog.input.RequestFinishListener;
import net.sf.anathema.lib.gui.dialog.input.check.CheckBoxSmartDialogPanel;
import net.sf.anathema.lib.gui.dialog.setting.IDialogVisibilitySetting;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.IDialogButtonConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;
import net.sf.anathema.lib.model.BooleanModel;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class DefaultDialogConfiguration<P extends IDialogPage>
    extends
    AbstractGenericDialogConfiguration implements IDialogConfiguration<P> {

  private final class VisibilityCheckboxModel extends BooleanModel {

    private boolean visible = dialogVisibilitySetting.isDialogVisible();

    @Override
    public void setValue(boolean selected) {
      visible = !selected;
    }

    @Override
    public boolean getValue() {
      boolean value = !visible;
      return value;
    }

    public void updateSetting() {
      if (visible != dialogVisibilitySetting.isDialogVisible()) {
        dialogVisibilitySetting.setDialogVisible(visible);
      }
    }
  }

  private final P dialogPage;
  private final Dimension customizedPreferedSize;
  private final JComponent[] additionalButtons;
  private IVetoDialogCloseHandler vetoDialogCloseHandler;
  private IDialogVisibilitySetting dialogVisibilitySetting;
  private final boolean updateVisibilitySettingOnCancel;
  private VisibilityCheckboxModel visibilityCheckboxModel;

  /**
   * @deprecated as of 21.12.2010 (beck), use {@link DefaultDialogConfigurationBuilder} instead
   */
  @Deprecated
  public DefaultDialogConfiguration(
      P dialogPage,
      IDialogButtonConfiguration buttonConfiguration) {
    this(dialogPage, buttonConfiguration, null, null);
  }

  public DefaultDialogConfiguration(
      P dialogPage,
      IDialogButtonConfiguration buttonConfiguration,
      IDialogVisibilitySetting dialogVisibilitySetting) {
    this(
        dialogPage,
        buttonConfiguration,
        DialogHeaderPanelConfiguration.createVisibleWithoutIcon(),
        null,
        null,
        null,
        dialogVisibilitySetting);
  }

  /**
   * @deprecated as of 21.12.2010 (beck), use {@link DefaultDialogConfigurationBuilder} instead
   */
  @Deprecated
  public DefaultDialogConfiguration(
      P dialogPage,
      IDialogButtonConfiguration buttonConfiguration,
      Dimension customizedPreferedSize,
      IDialogPreferences preferences) {
    this(
        dialogPage,
        buttonConfiguration,
        DialogHeaderPanelConfiguration.createVisibleWithoutIcon(),
        customizedPreferedSize,
        preferences,
        null);
  }

  public DefaultDialogConfiguration(
          P dialogPage,
          IDialogButtonConfiguration buttonConfiguration,
          IDialogHeaderPanelConfiguration headerPanelConfiguration,
          Dimension customizedPreferedSize,
          IDialogPreferences preferences,
          JComponent[] additionalButtons) {
    this(
            dialogPage,
            buttonConfiguration,
            headerPanelConfiguration,
            customizedPreferedSize,
            preferences,
            additionalButtons,
            (IDialogVisibilitySetting) null);
  }

  /**
   * Use {@link DefaultDialogConfigurationBuilder} to create a configuration 
   */
  public DefaultDialogConfiguration(
      P dialogPage,
      IDialogButtonConfiguration buttonConfiguration,
      IDialogHeaderPanelConfiguration headerPanelConfiguration,
      Dimension customizedPreferedSize,
      IDialogPreferences preferences,
      JComponent[] additionalButtons,
      final IVetoDialogCloseHandler vetoDialogCloseHandler,
      IDialogVisibilitySetting dialogVisibilitySetting,
      boolean updateVisibilitySettingOnCancel) {
    super(buttonConfiguration, headerPanelConfiguration, preferences);
    this.dialogPage = dialogPage;
    this.customizedPreferedSize = customizedPreferedSize;
    this.additionalButtons = additionalButtons;
    this.dialogVisibilitySetting = dialogVisibilitySetting;
    this.updateVisibilitySettingOnCancel = updateVisibilitySettingOnCancel;
    this.vetoDialogCloseHandler = new IVetoDialogCloseHandler() {

      @Override
      public boolean handleDialogAboutToClose(IDialogResult result, Component parentComponent) {
        updateDialogVisibilitySetting(result);
        return vetoDialogCloseHandler.handleDialogAboutToClose(result, parentComponent);
      }
    };
  }

  public DefaultDialogConfiguration(
      P dialogPage,
      IDialogButtonConfiguration buttonConfiguration,
      IDialogHeaderPanelConfiguration headerPanelConfiguration,
      Dimension customizedPreferedSize,
      IDialogPreferences preferences,
      JComponent[] additionalButtons,
      IDialogVisibilitySetting dialogVisibilitySetting) {
    super(buttonConfiguration, headerPanelConfiguration, preferences);
    this.dialogPage = dialogPage;
    this.customizedPreferedSize = customizedPreferedSize;
    this.additionalButtons = additionalButtons;
    this.dialogVisibilitySetting = dialogVisibilitySetting;
    this.updateVisibilitySettingOnCancel = false;
    this.vetoDialogCloseHandler = new IVetoDialogCloseHandler() {
      @Override
      public boolean handleDialogAboutToClose(
          IDialogResult result,
          Component parentComponent) {
        updateDialogVisibilitySetting(result);
        if (result.isCanceled()) {
          return performCancel(parentComponent);
        }
        return performOk(parentComponent);
      }
    };

  }

  @Override
  public P getDialogPage() {
    return dialogPage;
  }

  @Override
  public void setUserDialogContainer(final IUserDialogContainer dialogContainer) {
    dialogPage.addRequestFinishListener(new RequestFinishListener() {
      @Override
      public void requestFinish() {
        dialogContainer.requestFinish();
      }
    });
  }

  @Override
  public JComponent[] createAdditionalButtons() {
    if (additionalButtons == null) {
      return new JComponent[0];
    }
    return additionalButtons;
  }

  @Override
  public JComponent createOptionalButtonPanelLeftComponent() {
    if (dialogVisibilitySetting == null) {
      return null;
    }
    JPanel panel = new JPanel(new FlowLayout());
    visibilityCheckboxModel = new VisibilityCheckboxModel();
    CheckBoxSmartDialogPanel checkBox = new CheckBoxSmartDialogPanel(
        visibilityCheckboxModel,
        dialogVisibilitySetting.getMessageText());
    checkBox.fillInto(panel, 2);
    return panel;
  }

  private void updateDialogVisibilitySetting(IDialogResult result) {
    if ((!result.isCanceled() || updateVisibilitySettingOnCancel)
        && this.visibilityCheckboxModel != null) {
      this.visibilityCheckboxModel.updateSetting();
    }
  }

  @Override
  public IVetoDialogCloseHandler getVetoCloseHandler() {
    return vetoDialogCloseHandler;
  }

  @Deprecated
  public boolean performOk(Component parentComponent) {
    return true;
  }

  @Deprecated
  public boolean performCancel(Component parentComponent) {
    return true;
  }

  @Override
  public Dimension getCustomizedPreferedSize() {
    return customizedPreferedSize;
  }

  @Override
  public boolean isVisible() {
    return dialogVisibilitySetting == null || dialogVisibilitySetting.isDialogVisible();
  }
}