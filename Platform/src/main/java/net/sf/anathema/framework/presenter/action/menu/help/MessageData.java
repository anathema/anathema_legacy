package net.sf.anathema.framework.presenter.action.menu.help;

import net.sf.anathema.lib.message.MessageType;

public class MessageData implements IMessageData {

  private final String key;
  private final MessageType type;

  public MessageData(String key, MessageType type) {
    this.key = key;
    this.type = type;
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public MessageType getType() {
    return type;
  }
}
