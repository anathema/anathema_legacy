package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.interaction.ToggleInteraction;
import net.sf.anathema.lib.control.IChangeListener;

public class DeselectInteraction implements IChangeListener {
  private ToggleInteraction interaction;

  public DeselectInteraction(ToggleInteraction interaction) {
    this.interaction = interaction;
  }

  @Override
  public void changeOccurred() {
    interaction.deselect();
  }
}
