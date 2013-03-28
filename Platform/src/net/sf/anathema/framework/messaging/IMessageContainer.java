package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.message.IBasicMessage;

public interface IMessageContainer {

  void addChangeListener(IChangeListener listener);

  IBasicMessage getLatestMessage();
}