package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.swing.character.perspective.interaction.Interaction;

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
