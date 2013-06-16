package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

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
  public RelativePath createStandardIcon() {
    CasteUI casteUI = new CasteUI(properties);
    if (context.isExperienced() && !trait.getFavorization().isCasteOrFavored()) {
      return AgnosticUIConfiguration.NO_ICON;
    }
    if (trait.getFavorization().isCaste()) {
      return casteUI.getSmallCasteIconPath(context.getCasteType());
    }
    return new CharacterUI().getMediumBallPath(context.getCharacterType());
  }

  @Override
  public RelativePath createUnselectedIcon() {
    return AgnosticUIConfiguration.NO_ICON;
  }

  @Override
  public String getToolTipText() {
    return AgnosticUIConfiguration.NO_TOOLTIP;
  }
}