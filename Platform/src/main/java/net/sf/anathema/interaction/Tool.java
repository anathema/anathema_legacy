package net.sf.anathema.interaction;

import net.sf.anathema.lib.file.RelativePath;

public interface Tool {

  void setIcon(RelativePath relativePath);

  void setOverlay(RelativePath relativePath);

  void setTooltip(String text);

  void setText(String text);

  void enable();

  void disable();

  void setCommand(Command command);

  void setHotkey(Hotkey s);
}