package net.sf.anathema.lib.message;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Message extends BasicMessage implements IMessage {
  private static String getStackTrace(Throwable throwable) {
    if (throwable == null) {
      return null;
    }
    StringWriter stacktrace = new StringWriter();
    throwable.printStackTrace(new PrintWriter(stacktrace));
    return stacktrace.toString();
  }

  private final Throwable throwable;
  private final String detailText;

  public Message(String text, Throwable throwable) {
    this(text, MessageType.ERROR, getStackTrace(throwable), throwable);
  }

  public Message(String text, MessageType type) {
    this(text, type, null, null);
  }

  private Message(String text, MessageType type, String detailText, Throwable throwable) {
    super(text, type);
    this.throwable = throwable;
    this.detailText = detailText;
  }

  @Override
  public Throwable getThrowable() {
    return throwable;
  }

  @Override
  public String getDetail() {
    return detailText;
  }
}