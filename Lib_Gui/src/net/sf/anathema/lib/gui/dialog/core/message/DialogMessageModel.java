package net.sf.anathema.lib.gui.dialog.core.message;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.model.AbstractChangeableModel;

public class DialogMessageModel extends AbstractChangeableModel {

  private IBasicMessage baseMessage;
  private IBasicMessage overlaidMessage;
  private boolean overlaidMessageActive = false;

  public void setMessage(final IBasicMessage message) {
    Preconditions.checkNotNull(message);
    synchronized (getMutex()) {
      if (message.equals(getMessage())) {
        return;
      }
      if (message.getType() == MessageType.NORMAL) {
        this.baseMessage = message;
        this.overlaidMessageActive = false;
      }
      else {
        this.overlaidMessage = message;
        this.overlaidMessageActive = true;
      }
      fireChangeEvent();
    }
  }

  public boolean isOverlaidMessageActive() {
    synchronized (getMutex()) {
      return overlaidMessageActive;
    }
  }

  public IBasicMessage getBaseMessage() {
    synchronized (getMutex()) {
      return baseMessage;
    }
  }

  public IBasicMessage getOverlaidMessage() {
    synchronized (getMutex()) {
      return overlaidMessage;
    }
  }

  public IBasicMessage getMessage() {
    synchronized (getMutex()) {
      return overlaidMessageActive ? overlaidMessage : baseMessage;
    }
  }
}