package net.sf.anathema.hero.perspective;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;

public class EnableInteraction implements ChangeListener {
  private Tool interaction;

  public EnableInteraction(Tool interaction) {
    this.interaction = interaction;
  }

  @Override
  public void changeOccurred() {
    interaction.enable();
  }
}