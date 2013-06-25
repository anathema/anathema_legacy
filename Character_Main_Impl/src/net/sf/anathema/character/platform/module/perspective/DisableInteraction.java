package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;

public class DisableInteraction implements ChangeListener {
  private Tool interaction;

  public DisableInteraction(Tool interaction) {
    this.interaction = interaction;
  }

  @Override
  public void changeOccurred() {
    interaction.disable();
  }
}