package net.sf.anathema.lib.message;

public class MessageImpl implements Message {
  private final MessageType type;
  private final String text;

  public MessageImpl(String text) {
    this(text, MessageType.NORMAL);
  }

  public MessageImpl(String text, MessageType type) {
    this.text = text;
    this.type = type;
  }

  @Override
  public String getText() {
    return text;
  }

  @Override
  public MessageType getType() {
    return type;
  }
}