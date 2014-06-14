package net.sf.anathema.view;

import net.sf.anathema.framework.view.messaging.StatusBar;
import net.sf.anathema.lib.message.Message;

public class NullStatusBar implements StatusBar {
  @Override
  public void setLatestMessage(Message message) {
    //nothing to do
  }
}