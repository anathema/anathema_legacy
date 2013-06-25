package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.control.ChangeListener;

public class SelectInteraction implements ChangeListener {
  private ToggleTool interaction;

  public SelectInteraction(ToggleTool interaction) {
    this.interaction = interaction;
  }

  @Override
  public void changeOccurred() {
    interaction.select();
  }
}