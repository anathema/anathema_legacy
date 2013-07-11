package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.message.MessageType;

public class NullMessaging implements IMessaging {
  @Override
  public void addMessage(String messagePattern, MessageType messageType, Object... arguments) {
    // nothing to do
  }

  @Override
  public void addMessage(IBasicMessage message) {
    // nothing to do
  }
}
