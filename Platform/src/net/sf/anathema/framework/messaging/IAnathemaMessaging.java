package net.sf.anathema.framework.messaging;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.core.message.MessageType;

public interface IAnathemaMessaging {

  public void addMessage(String messagePattern, MessageType messageType, Object... arguments);

  public void addMessage(IBasicMessage message);
}