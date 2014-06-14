package net.sf.anathema.lib.message;

import static net.sf.anathema.lib.message.MessageDuration.Temporary;

public class Message {
  private final MessageType type;
  private final MessageDuration duration;
  private final String text;

  public Message(String text, MessageType type) {
    this(text, type, Temporary);
  }

  public Message(String text, MessageType type, MessageDuration duration) {
    this.text = text;
    this.type = type;
    this.duration = duration;
  }

  public String getText() {
    return text;
  }

  public MessageType getType() {
    return type;
  }

  public MessageDuration getDuration() {
    return duration;
  }
}