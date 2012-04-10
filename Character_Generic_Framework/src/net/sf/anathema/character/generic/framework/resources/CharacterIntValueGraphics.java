package net.sf.anathema.character.generic.framework.resources;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.value.IntValueDisplayGraphics;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;

public class CharacterIntValueGraphics implements IntValueDisplayGraphics {

  private final CharacterUI ui;
  private final ICharacterType type;

  public CharacterIntValueGraphics(IResources resources, ICharacterType type) {
    this.type = type;
    this.ui = new CharacterUI(resources);
  }

  @Override
  public Icon getActiveIcon() {
    return ui.getMediumBallResource(type);
  }

  @Override
  public Icon getPassiveIcon() {
    return ui.getUnselectedBallResource();
  }

  @Override
  public Icon getBlockedIcon() {
    return ui.getUnselectableBallResource();
  }
}
