package net.sf.anathema.character.generic.framework.xml.presentation;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterTypeVisitor;

public class CharacterTemplateResourceProvider implements ICharacterTemplateResourceProvider {

  private final Map<CharacterType, String> resourcesByCharacterType = new HashMap<CharacterType, String>();

  public CharacterTemplateResourceProvider() {
    for (CharacterType type : CharacterType.values()) {
      type.accept(new ICharacterTypeVisitor() {
        public void visitAbyssal(CharacterType visitedType) {
          resourcesByCharacterType.put(visitedType, IIconConstants.ABYSSAL_BALL);
        }

        public void visitDB(CharacterType visitedType) {
          resourcesByCharacterType.put(visitedType, IIconConstants.DB_BALL);
        }

        public void visitLunar(CharacterType visitedType) {
          resourcesByCharacterType.put(visitedType, IIconConstants.LUNAR_BALL);
        }

        public void visitMortal(CharacterType visitedType) {
          resourcesByCharacterType.put(visitedType, IIconConstants.MORTAL_BALL);
        }

        public void visitSidereal(CharacterType visitedType) {
          resourcesByCharacterType.put(visitedType, IIconConstants.SIDEREAL_BALL);
        }

        public void visitSolar(CharacterType visitedType) {
          resourcesByCharacterType.put(visitedType, IIconConstants.SOLAR_BALL);
        }

        public void visitDragonKing(CharacterType type) {
          // Nothing to do
        }
      });
    }
  }

  public String getBallResource(CharacterType characterType) {
    return resourcesByCharacterType.get(characterType);
  }

  public String getUnselectedBallResource() {
    return IIconConstants.UNSELECTED_BALL;
  }
}