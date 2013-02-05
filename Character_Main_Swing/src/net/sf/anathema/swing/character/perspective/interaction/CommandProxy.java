package net.sf.anathema.swing.character.perspective.interaction;

public class CommandProxy implements Command {

  private Command delegate = new NullCommand();

  public void setDelegate(Command command) {
    this.delegate = command;
  }

  @Override
  public void execute() {
    delegate.execute();
  }
}
