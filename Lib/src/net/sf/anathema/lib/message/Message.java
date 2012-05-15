package net.sf.anathema.lib.message;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Message extends BasicMessage implements IMessage {
  private final Throwable throwable;
  private final String title;
  private final String detailText;

  private Message(
      final String title,
      final String text,
      final MessageType type,
      final String detailText,
      final Throwable throwable) {
    super(text, type);
    this.throwable = throwable;
    this.title = title;
    this.detailText = detailText;
  }

  /**
   * Creates a new Message object using the specified parameters.
   */
  public Message(
      final String title,
      final String text,
      final MessageType type,
      final Throwable throwable) {
    this(title, text, type, getStackTrace(throwable), throwable);
  }

  private static String getStackTrace(final Throwable throwable) {
    if (throwable == null) {
      return null;
    }
    final StringWriter stacktrace = new StringWriter();
    throwable.printStackTrace(new PrintWriter(stacktrace));
    return stacktrace.toString();
  }

  /**
   * Creates a new error message.
   */
  public Message(final String title, final String text, final Throwable throwable) {
    this(title, text, MessageType.ERROR, throwable);
  }

  /**
   * Creates a new error Message object using the specified parameters.
   */
  public Message(final String text, final Throwable throwable) {
    this(null, text, throwable);
  }

  /**
   * Creates a new Message object using the specified parameters.
   */
  public Message(final String text, final MessageType type) {
    this(text, type, (String) null);
  }

  public Message(final String text, final MessageType messageType, final String detailText) {
    this(null, text, messageType, detailText, null);
  }

  @Override
  @Deprecated
  public String getTitle() {
    return title;
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