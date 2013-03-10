package net.sf.anathema.scribe.perspective.presenter;

import net.sf.anathema.interaction.Command;

public interface Tool {

  void setIcon(String relativePath);

  void setTooltip(String text);

  void setText(String text);

  void enable();

  void disable();

  void setCommand(Command command);
}
