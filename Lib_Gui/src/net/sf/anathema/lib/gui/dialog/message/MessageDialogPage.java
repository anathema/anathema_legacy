package net.sf.anathema.lib.gui.dialog.message;

import com.google.common.base.Preconditions;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.dialog.userdialog.page.AbstractDialogPage;
import net.sf.anathema.lib.gui.dialog.widgets.AutoWrappingLabel;
import net.sf.anathema.lib.gui.message.LargeIconMessageTypeUi;
import net.sf.anathema.lib.gui.message.MessageTypeUi;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.message.IMessage;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessageDialogPage extends AbstractDialogPage {

  private final IMessage message;

  public MessageDialogPage(IMessage message) {
    super("");
    Preconditions.checkNotNull(message);
    this.message = message;
  }

  @Override
  public JComponent createContent() {
    Icon icon = new LargeIconMessageTypeUi().getIcon(message.getType());
    AutoWrappingLabel label = new AutoWrappingLabel(message.getText(), 294);
    JPanel panel = new JPanel(new MigLayout(new LC().insets("0").fill().gridGapX("13")));
    label.setBackground(panel.getBackground());
    panel.add(new JLabel(icon), new CC().growY().pushY());
    panel.add(label.getContent(), new CC().growX().pushX());
    return panel;
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    return getDefaultMessage();
  }

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public String getTitle() {
    return MessageTypeUi.getInstance().getLabel(message.getType());
  }
}