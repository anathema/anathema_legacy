package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.message.MessageType;

public interface IMessaging {

  void addMessage(String messagePattern, MessageType messageType, Object... arguments);

  void addMessage(IBasicMessage message);
}