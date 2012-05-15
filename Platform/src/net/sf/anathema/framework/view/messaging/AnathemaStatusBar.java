package net.sf.anathema.framework.view.messaging;

import net.sf.anathema.lib.gui.message.MessageTypeUi;
import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Dimension;

public class AnathemaStatusBar implements IAnathemaStatusBar {

  private final JLabel label = new JLabel();

  public AnathemaStatusBar() {
    label.setPreferredSize(new Dimension(350, 25));
  }

  @Override
  public JComponent getComponent() {
    return label;
  }

  @Override
  public void setLatestMessage(IBasicMessage message) {
    label.setIcon(message == null ? null : MessageTypeUi.getInstance().getIcon(message.getType()));
    label.setText(message == null ? "  " : message.getText()); //$NON-NLS-1$
    label.revalidate();
  }
}