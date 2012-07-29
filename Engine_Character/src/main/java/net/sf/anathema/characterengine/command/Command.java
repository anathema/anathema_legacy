package net.sf.anathema.characterengine.command;

import net.sf.anathema.characterengine.persona.Qualities;

public interface Command {
  void execute(Qualities qualities);
}
