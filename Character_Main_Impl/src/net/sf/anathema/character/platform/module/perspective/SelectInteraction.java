package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.interaction.ToggleInteraction;
import net.sf.anathema.lib.control.IChangeListener;

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
