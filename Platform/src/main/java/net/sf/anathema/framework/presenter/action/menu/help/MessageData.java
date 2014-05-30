package net.sf.anathema.framework.presenter.action.menu.help;

import net.sf.anathema.lib.message.MessageType;

public class MessageData implements IMessageData {

  private final String message;
  private final MessageType type;

  public MessageData(String message, MessageType type) {
    this.message = message;
    this.type = type;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public MessageType getType() {
    return type;
  }
}
