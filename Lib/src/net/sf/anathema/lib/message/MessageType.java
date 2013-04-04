package net.sf.anathema.lib.message;

/**
 * Constants for specifying the type of a message object.
 */
public enum MessageType {

  ERROR("Fehler") {
    @Override
    public void accept(IMessageTypeVisitor visitor) {
      visitor.visitError(this);
    }
  },
  WARNING("Warnung") {
    @Override
    public void accept(IMessageTypeVisitor visitor) {
      visitor.visitWarning(this);
    }
  },
  INFORMATION("Information") {
    @Override
    public void accept(IMessageTypeVisitor visitor) {
      visitor.visitInformation(this);
    }
  },
  NORMAL("Normal") {
    @Override
    public void accept(IMessageTypeVisitor visitor) {
      visitor.visitNormal(this);
    }
  },
  QUESTION("Question") {
    @Override
    public void accept(IMessageTypeVisitor visitor) {
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