package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.message.IBasicMessage;

public interface IMessageContainer {

  void addChangeListener(ChangeListener listener);

  IBasicMessage getLatestMessage();
}