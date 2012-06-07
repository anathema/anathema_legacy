package net.sf.anathema.lib.gui.dialog.message;

import net.sf.anathema.lib.gui.dialog.core.DialogHeaderPanelConfiguration;
import net.sf.anathema.lib.gui.dialog.core.IDialogHeaderPanelConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.DefaultDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.DialogButtonConfigurationFactory;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.IDialogButtonConfiguration;
import net.sf.anathema.lib.message.IMessage;

public class MessageUserDialogConfiguration extends DefaultDialogConfiguration<MessageDialogPage> {

  public MessageUserDialogConfiguration(IMessage message) {
    this(message, DialogButtonConfigurationFactory.createOkOnly());
  }

  public MessageUserDialogConfiguration(IMessage message, IDialogButtonConfiguration buttonConfiguration) {
    super(new MessageDialogPage(message), buttonConfiguration, null);
  }

  @Override
  public IDialogHeaderPanelConfiguration getHeaderPanelConfiguration() {
    return DialogHeaderPanelConfiguration.createInvisible();
  }
}