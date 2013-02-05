package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.swing.character.perspective.interaction.ToggleInteraction;

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
