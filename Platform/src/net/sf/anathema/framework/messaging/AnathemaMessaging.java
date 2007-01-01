package net.sf.anathema.framework.messaging;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.core.message.MessageType;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaMessaging implements IAnathemaMessaging, IAnathemaMessageContainer {

  private final IResources resources;
  private final List<IBasicMessage> messages = new ArrayList<IBasicMessage>();
  private final ChangeControl changeControl = new ChangeControl();

  public AnathemaMessaging(IResources resources) {
    this.resources = resources;
  }

  public void addMessage(String pattern, MessageType messageType, Object... arguments) {
    String messageText = resources.getString(pattern, arguments);
    addMessage(new BasicMessage(messageText, messageType));
  }

  public synchronized void addMessage(IBasicMessage message) {
    messages.add(0, message);
    changeControl.fireChangedEvent();
    if (messages.size() > getMessageLimit()) {
      messages.remove(messages.size() - 1);
    }
  }

  public void addChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  public synchronized IBasicMessage[] getAllMessages() {
    return messages.toArray(new IBasicMessage[messages.size()]);
  }

  private int getMessageLimit() {
    return 100;
  }

  public synchronized IBasicMessage getLatestMessage() {
    if (messages.isEmpty()) {
      return null;
    }
    return messages.get(0);
  }
}