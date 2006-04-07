package net.sf.anathema.character.presenter;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.trait.IFavorableTrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
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
    if (context.isExperienced() && ability.getFavorization().getFavorableState() == FavorableState.Default) {
      return new Icon() {

        public void paintIcon(Component c, Graphics g, int x, int y) {
          // Nothing to do
        }

        public int getIconWidth() {
          return 20;
        }

        public int getIconHeight() {
          return 20;
        }
      };
    }
    if (ability.getFavorization().isCaste()) {
      String casteId = ability.getFavorization().getCaste().getId();
      return resources.getImageIcon(properties.getCasteIconResource(casteId));
    }
    return resources.getImageIcon(properties.getBallResource());
  }

  public Icon createUnselectedIcon() {
    return null;
  }
}