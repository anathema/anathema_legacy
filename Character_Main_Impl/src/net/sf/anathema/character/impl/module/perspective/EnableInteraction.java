package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.swing.character.perspective.interaction.Interaction;

public class EnableInteraction implements IChangeListener {
  private Interaction interaction;

  public EnableInteraction(Interaction interaction) {
    this.interaction = interaction;
  }

  @Override
  public void changeOccurred() {
    interaction.enable();
  }
}
