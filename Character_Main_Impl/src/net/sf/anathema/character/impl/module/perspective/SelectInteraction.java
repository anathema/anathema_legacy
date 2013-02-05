package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.swing.character.perspective.interaction.ToggleInteraction;

public class SelectInteraction implements IChangeListener {
  private ToggleInteraction interaction;

  public SelectInteraction(ToggleInteraction interaction) {
    this.interaction = interaction;
  }

  @Override
  public void changeOccurred() {
    interaction.select();
  }
}
