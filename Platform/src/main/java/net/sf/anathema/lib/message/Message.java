package net.sf.anathema.lib.message;

public class Message {
  private final MessageType type;
  private final String text;

  public Message(String text, MessageType type) {
    this.text = text;
    this.type = type;
  }

  public String getText() {
    return text;
  }

  public MessageType getType() {
    return type;
  }
}