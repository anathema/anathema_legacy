package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.interaction.Interaction;
import net.sf.anathema.lib.control.IChangeListener;

public class DisableInteraction implements IChangeListener {
  private Interaction interaction;

  public DisableInteraction(Interaction interaction) {
    this.interaction = interaction;
  }

  @Override

  public void changeOccurred() {
    interaction.disable();
  }
}
