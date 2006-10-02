package net.sf.anathema.character.equipment;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterTypeVisitor;
import net.sf.anathema.lib.util.IIdentificate;

public enum MagicalMaterial implements IIdentificate {
  Orichalcum, Jade, Moonsilver, Starmetal, Soulsteel;

  public String getId() {
    return name();
  }

  public static MagicalMaterial getDefault(CharacterType characterType) {
    final MagicalMaterial[] material = new MagicalMaterial[1];

    characterType.accept(new ICharacterTypeVisitor() {

      public void visitSolar(CharacterType visitedType) {
        material[0] = Orichalcum;
      }

      public void visitSidereal(CharacterType visitedType) {
        material[0] = Starmetal;
      }

      public void visitMortal(CharacterType visitedType) {
        // nothing to do
      }

      public void visitLunar(CharacterType type) {
        material[0] = Moonsilver;
      }

      public void visitDragonKing(CharacterType type) {
        material[0] = Orichalcum;
      }

      public void visitDB(CharacterType visitedType) {
        material[0] = Jade;
      }

      public void visitAbyssal(CharacterType visitedType) {
        material[0] = Soulsteel;
      }
    });
    return material[0];
  }
}