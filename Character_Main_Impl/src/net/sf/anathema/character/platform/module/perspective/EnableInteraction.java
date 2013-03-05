package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.interaction.Interaction;
import net.sf.anathema.lib.control.IChangeListener;

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
