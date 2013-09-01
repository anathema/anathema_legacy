package net.sf.anathema.framework.messaging;

import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.framework.environment.Resources;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class Messaging implements IMessaging, IMessageContainer {

  private final Resources resources;
  private final List<IBasicMessage> messages = new ArrayList<>();
  private final Announcer<ChangeListener> changeControl = Announcer.to(ChangeListener.class);

  public Messaging(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void addMessage(String pattern, MessageType messageType, Object... arguments) {
    String messageText = resources.getString(pattern, arguments);
    addMessage(new BasicMessage(messageText, messageType));
  }

  @Override
  public synchronized void addMessage(IBasicMessage message) {
    messages.add(0, message);
    changeControl.announce().changeOccurred();
    if (messages.size() > getMessageLimit()) {
      messages.remove(messages.size() - 1);
    }
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    changeControl.addListener(listener);
  }

  private int getMessageLimit() {
    return 100;
  }

  @Override
  public synchronized IBasicMessage getLatestMessage() {
    if (messages.isEmpty()) {
      return new BasicMessage("", MessageType.NORMAL);
    }
    return messages.get(0);
  }
}