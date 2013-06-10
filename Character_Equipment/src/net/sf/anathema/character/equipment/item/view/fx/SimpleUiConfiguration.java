package net.sf.anathema.character.equipment.item.view.fx;

import net.sf.anathema.lib.gui.AbstractUIConfiguration;

public class SimpleUiConfiguration extends AbstractUIConfiguration<String> {

  @Override
  public String getLabel(String value) {
    return value;
  }
}