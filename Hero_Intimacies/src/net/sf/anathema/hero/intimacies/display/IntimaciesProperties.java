package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.lib.gui.AgnosticUIConfiguration.NO_ICON;

public class IntimaciesProperties implements IIconToggleButtonProperties {
  private final Resources resources;

  public IntimaciesProperties(Resources resources) {
    this.resources = resources;
  }

  @Override
  public RelativePath createStandardIcon() {
    return new CharacterUI().getLinkIconPath();
  }

  @Override
  public RelativePath createUnselectedIcon() {
    return NO_ICON;
  }

}
