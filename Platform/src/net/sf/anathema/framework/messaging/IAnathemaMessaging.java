package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.message.MessageType;

public interface IAnathemaMessaging {

  public void addMessage(String messagePattern, MessageType messageType, Object... arguments);

  public void addMessage(IBasicMessage message);
}