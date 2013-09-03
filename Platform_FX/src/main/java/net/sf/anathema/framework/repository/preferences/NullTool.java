package net.sf.anathema.framework.repository.preferences;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Hotkey;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;

public class NullTool implements Tool {
  @Override
  public void setIcon(RelativePath relativePath) {
    //nothing to do;
  }

  @Override
  public void setOverlay(RelativePath relativePath) {
    //nothing to do;
  }

  @Override
  public void setTooltip(String text) {
    //nothing to do;
  }

  @Override
  public void setText(String text) {
    //nothing to do;
  }

  @Override
  public void enable() {
    //nothing to do;
  }

  @Override
  public void disable() {
    //nothing to do;
  }

  @Override
  public void setCommand(Command command) {
    //nothing to do;
  }

  @Override
  public void setHotkey(Hotkey s) {
    //nothing to do;
  }
}