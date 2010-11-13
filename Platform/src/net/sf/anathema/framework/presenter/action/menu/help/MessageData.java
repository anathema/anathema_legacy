package net.sf.anathema.framework.presenter.action.menu.help;

import net.disy.commons.core.message.MessageType;

public class MessageData implements IMessageData {

  private final String key;
  private final MessageType type;

  public MessageData(String key, MessageType type) {
    this.key = key;
    this.type = type;
  }

  public String getKey() {
    return key;
  }

  public MessageType getType() {
    return type;
  }
}
