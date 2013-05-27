package net.sf.anathema.platform.fx;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.platform.Hotkey;

public interface AcceleratorMap {
  void register(Hotkey hotkey, Command command);
}
