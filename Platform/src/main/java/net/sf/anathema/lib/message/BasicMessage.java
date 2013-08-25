package net.sf.anathema.lib.message;

import com.google.common.base.Preconditions;

public class BasicMessage implements IBasicMessage {
  private final MessageType type;
  private final String text;

  public BasicMessage(String text) {
    this(text, MessageType.NORMAL);
  }

  public BasicMessage(String text, MessageType type) {
    Preconditions.checkNotNull(text, "Text for message may not be null.");
    Preconditions.checkNotNull(type, "Type for message may not be null.");
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

  @Override
  public boolean isErrorMessage() {
    return MessageType.ERROR.equals(type);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof BasicMessage)) {
      return false;
    }
    BasicMessage other = (BasicMessage) obj;
    return other.type == type && text.equals(other.text);
  }

  @Override
  public int hashCode() {
    return text.hashCode() + 5 * type.hashCode();
  }

  @Override
  public String toString() {
    return getType() + ": " + getText();
  }
}