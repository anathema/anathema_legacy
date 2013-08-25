package net.sf.anathema.lib.message;

public class Message extends BasicMessage implements IMessage {

  private final Throwable throwable;

  public Message(String text, Throwable throwable) {
    this(text, MessageType.ERROR, throwable);
  }

  public Message(String text, MessageType type) {
    this(text, type, null);
  }

  private Message(String text, MessageType type, Throwable throwable) {
    super(text, type);
    this.throwable = throwable;
  }

  @Override
  public Throwable getThrowable() {
    return throwable;
  }
}