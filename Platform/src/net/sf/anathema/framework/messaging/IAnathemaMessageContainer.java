package net.sf.anathema.framework.messaging;

import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IAnathemaMessageContainer {

  public void addChangeListener(IChangeListener listener);
  
  public IBasicMessage getLatestMessage();
  
  public IBasicMessage[] getAllMessages();
}