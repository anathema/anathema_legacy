package net.sf.anathema.character.generic.framework.xml.presentation;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterTypeVisitor;
import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class CharacterTemplateResourceProvider extends AbstractUI {

  private final Map<CharacterType, String> resourcesByCharacterType = new HashMap<CharacterType, String>();
  private Map<CharacterType, String> tinyResourcesByCharacterType = new HashMap<CharacterType, String>();

  public CharacterTemplateResourceProvider(IResources resources) {
    super(resources);
    for (CharacterType type : CharacterType.values()) {
      type.accept(new ICharacterTypeVisitor() {
        public void visitAbyssal(CharacterType visitedType) {
          resourcesByCharacterType.put(visitedType, IIconConstants.ABYSSAL_BALL);
          tinyResourcesByCharacterType.put(visitedType, IIconConstants.ABYSSAL_BALL_TINY);
        }

        public void visitDB(CharacterType visitedType) {
          resourcesByCharacterType.put(visitedType, IIconConstants.DB_BALL);
          tinyResourcesByCharacterType.put(visitedType, IIconConstants.DB_BALL_TINY);
        }

        public void visitLunar(CharacterType visitedType) {
          resourcesByCharacterType.put(visitedType, IIconConstants.LUNAR_BALL);
          tinyResourcesByCharacterType.put(visitedType, IIconConstants.LUNAR_BALL_TINY);
        }

        public void visitMortal(CharacterType visitedType) {
          resourcesByCharacterType.put(visitedType, IIconConstants.MORTAL_BALL);
          tinyResourcesByCharacterType.put(visitedType, IIconConstants.MORTAL_BALL_TINY);
        }

        public void visitSidereal(CharacterType visitedType) {
          resourcesByCharacterType.put(visitedType, IIconConstants.SIDEREAL_BALL);
          tinyResourcesByCharacterType.put(visitedType, IIconConstants.SIDEREAL_BALL_TINY);
        }

        public void visitSolar(CharacterType visitedType) {
          resourcesByCharacterType.put(visitedType, IIconConstants.SOLAR_BALL);
          tinyResourcesByCharacterType.put(visitedType, IIconConstants.SOLAR_BALL_TINY);
        }

        public void visitDragonKing(CharacterType visitedType) {
          // Nothing to do
        }
      });
    }
  }

  public Icon getMediumBallResource(CharacterType characterType) {
    return getIcon(resourcesByCharacterType.get(characterType));
  }

  public Icon getTinyBallResource(CharacterType characterType) {
    return getIcon(tinyResourcesByCharacterType.get(characterType));
  }

  public Icon getUnselectedBallResource() {
    return getIcon(IIconConstants.UNSELECTED_BALL);
  }
}