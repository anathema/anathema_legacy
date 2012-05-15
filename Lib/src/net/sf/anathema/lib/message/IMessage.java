package net.sf.anathema.lib.message;

public interface IMessage extends IBasicMessage {

  @Deprecated
  String getTitle();

  Throwable getThrowable();

  String getDetail();
}