package net.sf.anathema.framework.preferences.elements;

import net.sf.anathema.interaction.Command;

public interface DirtyModel {
  void whenDirtied(Command command);
}
