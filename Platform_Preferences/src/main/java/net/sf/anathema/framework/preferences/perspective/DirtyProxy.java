package net.sf.anathema.framework.preferences.perspective;

import net.sf.anathema.framework.preferences.elements.DirtyModel;
import net.sf.anathema.interaction.Command;
import org.jmock.example.announcer.Announcer;

public class DirtyProxy implements DirtyModel {

  private final Announcer<Command> announcer = Announcer.to(Command.class);

  public void whenDirtied(Command command) {
    announcer.addListener(command);
  }

  public void register(DirtyModel model) {
    model.whenDirtied(() -> announcer.announce().execute());
  }
}
