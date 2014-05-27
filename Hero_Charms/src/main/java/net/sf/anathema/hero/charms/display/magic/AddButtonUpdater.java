package net.sf.anathema.hero.charms.display.magic;

import net.sf.anathema.interaction.Tool;

public class AddButtonUpdater implements ButtonUpdater{
  private final MagicLearnProperties properties;
  private final Tool tool;
  private final MagicLearnView view;

  public AddButtonUpdater(MagicLearnProperties properties, Tool tool, MagicLearnView view) {
    this.properties = properties;
    this.tool = tool;
    this.view = view;
  }

  public void updateButton() {
    boolean available = properties.isMagicSelectionAvailable(view.getSelectedAvailableValues());
    if (available) {
      tool.enable();
    } else {
      tool.disable();
    }
  }
}