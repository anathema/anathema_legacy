package net.sf.anathema.interaction;

import net.sf.anathema.framework.perspective.ToolBar;
import net.sf.anathema.lib.file.RelativePath;

public interface Interaction {

  void setIcon(RelativePath relativePath);

  void setTooltip(String key);

  void setText(String key);

  void enable();

  void disable();

  void setCommand(Command command);

  void addTo(ToolBar toolbar);
}
