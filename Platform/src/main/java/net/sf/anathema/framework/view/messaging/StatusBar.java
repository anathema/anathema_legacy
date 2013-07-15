package net.sf.anathema.framework.view.messaging;

import net.sf.anathema.lib.message.IBasicMessage;

public interface StatusBar {

  void setLatestMessage(IBasicMessage message);
}