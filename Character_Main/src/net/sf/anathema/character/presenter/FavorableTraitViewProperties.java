package net.sf.anathema.character.presenter;

import javax.swing.Icon;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.resources.CharacterTemplateResourceProvider;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.lib.resources.IResources;

public class FavorableTraitViewProperties implements IIconToggleButtonProperties {

  private final IResources resources;
  private final IFavorableTrait ability;
  private final IPresentationProperties properties;
  private final IBasicCharacterData context;

  public FavorableTraitViewProperties(
      IPresentationProperties properties,
      IBasicCharacterData context,
      IFavorableTrait ability,
      IResources resources) {
    this.properties = properties;
    this.context = context;
    this.ability = ability;
    this.resources = resources;
  }

  public Icon createStandardIcon() {
    CasteUI casteUI = new CasteUI(resources, context.getRuleSet().getEdition(), properties);
    if (context.isExperienced() && ability.getFavorization().getFavorableState() == FavorableState.Default) {
      return casteUI.getEmptyIcon();
    }
    if (ability.getFavorization().isCaste()) {
      return casteUI.getSmallCasteIcon(ability.getFavorization().getCaste());
    }
    return new CharacterTemplateResourceProvider(resources).getMediumBallResource(context.getCharacterType());
  }

  public Icon createUnselectedIcon() {
    return null;
  }

  public String getToolTipText() {
    return null;
  }
}