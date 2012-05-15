package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.message.IBasicMessage;

public interface IAnathemaMessageContainer {

  public void addChangeListener(IChangeListener listener);

  public IBasicMessage getLatestMessage();

  public IBasicMessage[] getAllMessages();
}