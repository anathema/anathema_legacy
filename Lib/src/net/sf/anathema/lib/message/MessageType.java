package net.sf.anathema.lib.message;

/**
 * Constants for specifying the type of a message object.
 */
public enum MessageType {

  ERROR("Fehler") { //$NON-NLS-1$
    @Override
    public void accept(final IMessageTypeVisitor visitor) {
      visitor.visitError(this);
    }
  },
  WARNING("Warnung") { //$NON-NLS-1$
    @Override
    public void accept(final IMessageTypeVisitor visitor) {
      visitor.visitWarning(this);
    }
  },
  INFORMATION("Information") { //$NON-NLS-1$
    @Override
    public void accept(final IMessageTypeVisitor visitor) {
      visitor.visitInformation(this);
    }
  },
  NORMAL("Normal") { //$NON-NLS-1$
    @Override
    public void accept(final IMessageTypeVisitor visitor) {
      visitor.visitNormal(this);
    }
  },
  QUESTION("Question") { //$NON-NLS-1$
    @Override
    public void accept(final IMessageTypeVisitor visitor) {
      visitor.visitQuestion(this);
    }
  };

  private String name;

  private MessageType(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  public abstract void accept(IMessageTypeVisitor visitor);
}