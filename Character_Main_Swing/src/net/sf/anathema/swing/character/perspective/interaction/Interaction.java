package net.sf.anathema.swing.character.perspective.interaction;

import net.sf.anathema.framework.perspective.ToolBar;
import net.sf.anathema.interaction.Command;

public interface Interaction {

  void setIcon(String relativePath);

  void setTooltip(String key);

  void setText(String key);

  void enable();

  void disable();

  void setCommand(Command command);

  void addTo(ToolBar toolbar);
}
