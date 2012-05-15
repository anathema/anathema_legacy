package net.sf.anathema.lib.gui.dialog.message;

import com.google.common.base.Preconditions;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
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

  public MessageDialogPage(final IMessage message) {
    super(""); //$NON-NLS-1$
    Preconditions.checkNotNull(message);
    this.message = message;
  }

  @Override
  public JComponent createContent() {
    final Icon icon = new LargeIconMessageTypeUi().getIcon(message.getType());
    final AutoWrappingLabel label = new AutoWrappingLabel(message.getText(), 294);
    final JPanel panel = new JPanel(new GridDialogLayout(2, false, 13, 0));
    label.setBackground(panel.getBackground());
    panel.add(new JLabel(icon), GridDialogLayoutData.FILL_VERTICAL);
    panel.add(label.getContent(), GridDialogLayoutData.FILL_HORIZONTAL);
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
    return message.getTitle() == null
        ? MessageTypeUi.getInstance().getLabel(message.getType())
        : message.getTitle();
  }
}