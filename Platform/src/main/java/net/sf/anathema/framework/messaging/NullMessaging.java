package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;

public class NullMessaging implements IMessaging {
  @Override
  public void addMessage(String messagePattern, MessageType messageType, Object... arguments) {
    // nothing to do
  }

  @Override
  public void addMessage(Message message) {
    // nothing to do
  }
}
