package net.sf.anathema.hero.creation;

import net.sf.anathema.interaction.ToggleTool;

import javax.swing.Action;
import javax.swing.JToggleButton;

public interface ToggleButtonPanel {

  ToggleTool addButton(String label);
}