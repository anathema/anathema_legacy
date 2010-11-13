package net.sf.anathema.framework.repository.tree;

import net.disy.commons.core.message.MessageType;
import net.sf.anathema.framework.messaging.IAnathemaMessaging;
import net.sf.anathema.lib.resources.IResources;

public class AmountMessaging {

  private final IAnathemaMessaging messaging;
  private final String singleItem;
  private final String multiItem;

  public AmountMessaging(IAnathemaMessaging messaging, IResources resources) {
    this.messaging = messaging;
    this.singleItem = resources.getString("AnathemaCore.Tools.RepositoryView.Item"); //$NON-NLS-1$
    this.multiItem = resources.getString("AnathemaCore.Tools.RepositoryView.Items"); //$NON-NLS-1$
  }

  public void addMessage(String messagePattern, int amount) {
    String itemString;
    if (amount == 1) {
      itemString = singleItem;
    }
    else {
      itemString = multiItem;
    }
    messaging.addMessage(messagePattern, MessageType.INFORMATION, amount, itemString);
  }
}