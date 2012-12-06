package net.sf.anathema;

import net.sf.anathema.framework.view.messaging.IStatusBar;
import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class NullStatusBar implements IStatusBar {
  @Override
  public void setLatestMessage(IBasicMessage message) {
    //nothing to do
  }

  @Override
  public JComponent getComponent() {
    return new JPanel();
  }
}