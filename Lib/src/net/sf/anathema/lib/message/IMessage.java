package net.sf.anathema.lib.message;

public interface IMessage extends IBasicMessage {

  @Deprecated
  public String getTitle();

  public Throwable getThrowable();

  public String getDetail();
}