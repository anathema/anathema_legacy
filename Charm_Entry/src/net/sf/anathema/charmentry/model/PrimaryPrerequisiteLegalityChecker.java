package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterTypeVisitor;

public class PrimaryPrerequisiteLegalityChecker {

  public boolean isLegalPrimaryPrerequisite(CharacterType characterType, IGenericTrait prerequisite) {
    final ITraitType traitType = prerequisite.getType();
    if (traitType == null || traitType instanceof OtherTraitType) {
      return false;
    }
    if (characterType == null) {
      return true;
    }
    final boolean[] accept = new boolean[1];
    characterType.accept(new ICharacterTypeVisitor() {
      public void visitSolar(CharacterType visitedType) {
        accept[0] = traitType instanceof AbilityType;
      }

      public void visitMortal(CharacterType visitedType) {
        throw new UnsupportedOperationException();
      }

      public void visitSidereal(CharacterType visitedType) {
        accept[0] = traitType instanceof AbilityType;
      }

      public void visitDB(CharacterType visitedType) {
        accept[0] = traitType instanceof AbilityType;
      }

      public void visitAbyssal(CharacterType visitedType) {
        accept[0] = traitType instanceof AbilityType;
      }

      public void visitDragonKing(CharacterType visitedType) {
        throw new UnsupportedOperationException();
      }

      public void visitLunar(CharacterType visitedType) {
        accept[0] = traitType instanceof AttributeType;
      }
    });
    return accept[0];
  }

}
