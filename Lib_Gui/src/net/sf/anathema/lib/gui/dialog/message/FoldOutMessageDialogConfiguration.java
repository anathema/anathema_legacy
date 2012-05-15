package net.sf.anathema.lib.gui.dialog.message;

import net.sf.anathema.lib.gui.dialog.core.DialogHeaderPanelConfiguration;
import net.sf.anathema.lib.gui.dialog.core.IDialogHeaderPanelConfiguration;
import net.sf.anathema.lib.gui.dialog.foldout.AbstractFoldOutDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.foldout.IFoldOutPage;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.DialogButtonConfigurationFactory;
import net.sf.anathema.lib.message.IMessage;

public class FoldOutMessageDialogConfiguration
    extends
    AbstractFoldOutDialogConfiguration<MessageDialogPage> {

  public FoldOutMessageDialogConfiguration(final IMessage message, final IFoldOutPage foldOutPage) {
    super(new MessageDialogPage(message), foldOutPage, DialogButtonConfigurationFactory
        .createOkOnly());
  }

  @Override
  public IDialogHeaderPanelConfiguration getHeaderPanelConfiguration() {
    return DialogHeaderPanelConfiguration.createInvisible();
  }
}