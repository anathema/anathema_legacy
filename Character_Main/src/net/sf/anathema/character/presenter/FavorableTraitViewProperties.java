package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.trait.Trait;

import javax.swing.Icon;

public class FavorableTraitViewProperties implements IIconToggleButtonProperties {

  private final Trait trait;
  private final IPresentationProperties properties;
  private final IBasicCharacterData context;

  public FavorableTraitViewProperties(IPresentationProperties properties, IBasicCharacterData context, Trait trait) {
    this.properties = properties;
    this.context = context;
    this.trait = trait;
  }

  @Override
  public Icon createStandardIcon() {
    CasteUI casteUI = new CasteUI(properties);
    if (context.isExperienced() && !trait.getFavorization().isCasteOrFavored()) {
      return casteUI.getEmptyIcon();
    }
    if (trait.getFavorization().isCaste()) {
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