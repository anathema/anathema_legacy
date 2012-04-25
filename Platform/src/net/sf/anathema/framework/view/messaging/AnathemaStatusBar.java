package net.sf.anathema.framework.view.messaging;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JLabel;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.message.MessageTypeUi;

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