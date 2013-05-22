package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.control.IChangeListener;

public class DeselectInteraction implements IChangeListener {
  private ToggleTool interaction;

  public DeselectInteraction(ToggleTool interaction) {
    this.interaction = interaction;
  }

  @Override
  public void changeOccurred() {
    interaction.deselect();
  }
}