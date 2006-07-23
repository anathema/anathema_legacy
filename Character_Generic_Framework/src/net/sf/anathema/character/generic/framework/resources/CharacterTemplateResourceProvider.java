package net.sf.anathema.character.generic.framework.resources;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterTypeVisitor;
import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class CharacterTemplateResourceProvider extends AbstractUI {

  private final Map<CharacterType, String> ballResourcesByCharacterType = new HashMap<CharacterType, String>();
  private Map<CharacterType, String> tinyBallResourcesByCharacterType = new HashMap<CharacterType, String>();
  private Map<CharacterType, String> smalltypeIconsByCharacterType = new HashMap<CharacterType, String>();
  private Map<CharacterType, String> tinyTypeIconsByCharacterType = new HashMap<CharacterType, String>();

  public CharacterTemplateResourceProvider(IResources resources) {
    super(resources);
    for (CharacterType type : CharacterType.values()) {
      type.accept(new ICharacterTypeVisitor() {
        public void visitAbyssal(CharacterType visitedType) {
          ballResourcesByCharacterType.put(visitedType, IIconConstants.ABYSSAL_BALL);
          tinyBallResourcesByCharacterType.put(visitedType, IIconConstants.ABYSSAL_BALL_TINY);
          smalltypeIconsByCharacterType.put(visitedType, IIconConstants.ABYSSAL_ICON_SMALL);
          tinyTypeIconsByCharacterType.put(visitedType, IIconConstants.ABYSSAL_ICON_TINY);
        }

        public void visitDB(CharacterType visitedType) {
          ballResourcesByCharacterType.put(visitedType, IIconConstants.DB_BALL);
          tinyBallResourcesByCharacterType.put(visitedType, IIconConstants.DB_BALL_TINY);
          smalltypeIconsByCharacterType.put(visitedType, IIconConstants.DB_ICON_SMALL);
          smalltypeIconsByCharacterType.put(visitedType, IIconConstants.DB_ICON_TINY);
        }

        public void visitLunar(CharacterType visitedType) {
          ballResourcesByCharacterType.put(visitedType, IIconConstants.LUNAR_BALL);
          tinyBallResourcesByCharacterType.put(visitedType, IIconConstants.LUNAR_BALL_TINY);
          smalltypeIconsByCharacterType.put(visitedType, IIconConstants.LUNAR_ICON_SMALL);
          smalltypeIconsByCharacterType.put(visitedType, IIconConstants.LUNAR_ICON_TINY);
        }

        public void visitMortal(CharacterType visitedType) {
          ballResourcesByCharacterType.put(visitedType, IIconConstants.MORTAL_BALL);
          tinyBallResourcesByCharacterType.put(visitedType, IIconConstants.MORTAL_BALL_TINY);
          smalltypeIconsByCharacterType.put(visitedType, IIconConstants.MORTAL_ICON_SMALL);
          smalltypeIconsByCharacterType.put(visitedType, IIconConstants.MORTAL_ICON_TINY);
        }

        public void visitSidereal(CharacterType visitedType) {
          ballResourcesByCharacterType.put(visitedType, IIconConstants.SIDEREAL_BALL);
          tinyBallResourcesByCharacterType.put(visitedType, IIconConstants.SIDEREAL_BALL_TINY);
          smalltypeIconsByCharacterType.put(visitedType, IIconConstants.SIDEREAL_ICON_SMALL);
          smalltypeIconsByCharacterType.put(visitedType, IIconConstants.SIDEREAL_ICON_TINY);
        }

        public void visitSolar(CharacterType visitedType) {
          ballResourcesByCharacterType.put(visitedType, IIconConstants.SOLAR_BALL);
          tinyBallResourcesByCharacterType.put(visitedType, IIconConstants.SOLAR_BALL_TINY);
          smalltypeIconsByCharacterType.put(visitedType, IIconConstants.SOLAR_ICON_SMALL);
          smalltypeIconsByCharacterType.put(visitedType, IIconConstants.SOLAR_ICON_TINY);
        }

        public void visitDragonKing(CharacterType visitedType) {
          // Nothing to do
        }
      });
    }
  }

  public Icon getSmallTypeIcon(CharacterType characterType) {
    return getIcon(smalltypeIconsByCharacterType.get(characterType));
  }

  public Icon getMediumBallResource(CharacterType characterType) {
    return getIcon(ballResourcesByCharacterType.get(characterType));
  }

  public Icon getTinyBallResource(CharacterType characterType) {
    return getIcon(tinyBallResourcesByCharacterType.get(characterType));
  }

  public Icon getTinyTypeIcon(CharacterType characterType) {
    return getIcon(tinyTypeIconsByCharacterType.get(characterType));
  }

  public Icon getUnselectedBallResource() {
    return getIcon(IIconConstants.UNSELECTED_BALL);
  }
}