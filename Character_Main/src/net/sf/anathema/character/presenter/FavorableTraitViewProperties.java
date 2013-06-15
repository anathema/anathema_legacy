package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.trait.ITrait;

import javax.swing.Icon;

public class FavorableTraitViewProperties implements IIconToggleButtonProperties {

  private final ITrait ability;
  private final IPresentationProperties properties;
  private final IBasicCharacterData context;

  public FavorableTraitViewProperties(IPresentationProperties properties, IBasicCharacterData context, ITrait ability) {
    this.properties = properties;
    this.context = context;
    this.ability = ability;
  }

  @Override
  public Icon createStandardIcon() {
    CasteUI casteUI = new CasteUI(properties);
    if (context.isExperienced() && !ability.getFavorization().isCasteOrFavored()) {
      return casteUI.getEmptyIcon();
    }
    if (ability.getFavorization().isCaste()) {
      return casteUI.getSmallCasteIcon(context.getCasteType());
    }
    return new CharacterUI().getMediumBallResource(context.getCharacterType());
  }

  @Override
  public Icon createUnselectedIcon() {
    return null;
  }

  @Override
  public String getToolTipText() {
    return null;
  }
}