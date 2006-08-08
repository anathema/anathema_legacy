package net.sf.anathema.character.presenter;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.resources.CharacterTemplateResourceProvider;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.trait.IFavorableModifiableTrait;
import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.lib.resources.IResources;

public class FavorableTraitViewProperties implements IIconToggleButtonProperties {

  private final IResources resources;
  private final IFavorableModifiableTrait ability;
  private final IPresentationProperties properties;
  private final IBasicCharacterData context;

  public FavorableTraitViewProperties(
      IPresentationProperties properties,
      IBasicCharacterData context,
      IFavorableModifiableTrait ability,
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
      return new CasteUI(resources, context.getRuleSet().getEdition(), properties).getSmallCasteIcon(ability.getFavorization()
          .getCaste());
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