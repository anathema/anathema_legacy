package net.sf.anathema.framework.messaging;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class Messaging implements IMessaging, IMessageContainer {

  private final Resources resources;
  private final List<Message> messages = new ArrayList<>();
  private final Announcer<ChangeListener> changeControl = Announcer.to(ChangeListener.class);

  public Messaging(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void addMessage(String pattern, MessageType messageType, Object... arguments) {
    String messageText = resources.getString(pattern, arguments);
    addMessage(new Message(messageText, messageType));
  }

  @Override
  public synchronized void addMessage(Message message) {
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
  public synchronized Message getLatestMessage() {
    if (messages.isEmpty()) {
      return new Message("", MessageType.NORMAL);
    }
    return messages.get(0);
  }
}