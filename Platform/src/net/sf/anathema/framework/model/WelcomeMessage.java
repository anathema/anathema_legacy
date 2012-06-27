package net.sf.anathema.framework.model;

import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.lib.message.BasicMessage;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.random.RandomUtilities;

import java.util.List;

public class WelcomeMessage {
  private final IMessaging messaging;
  private final InformativeMessages messages;


  public WelcomeMessage(IMessaging messaging, InformativeMessages messages) {
    this.messaging = messaging;
    this.messages = messages;
  }

  public void show() {
    String welcomeMessage = chooseMessage();
    messaging.addMessage(new BasicMessage(welcomeMessage, MessageType.INFORMATION));
  }

  private String chooseMessage() {
    List<String> allMessages = messages.getAll();
    if (allMessages.isEmpty()) {
      return "Welcome to Anathema!";
    }
    return RandomUtilities.choose(allMessages);
  }
}