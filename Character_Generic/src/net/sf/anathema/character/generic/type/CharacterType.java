package net.sf.anathema.character.generic.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.lib.util.IIdentificate;

public enum CharacterType implements IIdentificate {

  SOLAR("Solar") { //$NON-NLS-1$
    @Override
    public void accept(ICharacterTypeVisitor visitor) {
      visitor.visitSolar(this);
    }
  },
  DB("Dragon-Blooded") { //$NON-NLS-1$
    @Override
    public void accept(ICharacterTypeVisitor visitor) {
      visitor.visitDB(this);
    }
  },
  LUNAR("Lunar") { //$NON-NLS-1$
    @Override
    public void accept(ICharacterTypeVisitor visitor) {
      visitor.visitLunar(this);
    }
  },
  ABYSSAL("Abyssal") { //$NON-NLS-1$
    @Override
    public void accept(ICharacterTypeVisitor visitor) {
      visitor.visitAbyssal(this);
    }
  },

  SIDEREAL("Sidereal") {//$NON-NLS-1$
    @Override
    public void accept(ICharacterTypeVisitor visitor) {
      visitor.visitSidereal(this);
    }
  },
  MORTAL("Mortal") {//$NON-NLS-1$
    @Override
    public void accept(ICharacterTypeVisitor visitor) {
      visitor.visitMortal(this);
    }
  },
  DRAGON_KING("DragonKing") { //$NON-NLS-1$
    @Override
    public void accept(ICharacterTypeVisitor visitor) {
      visitor.visitDragonKing(this);
    }
  };

  private final String id;

  private CharacterType(String id) {
    this.id = id;
  }

  public abstract void accept(ICharacterTypeVisitor visitor);

  public static CharacterType getById(String id) {
    for (CharacterType type : values()) {
      if (type.getId().equals(id)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No type defined for id:" + id); //$NON-NLS-1$
  }

  public static CharacterType[] getAllExaltTypes() {
    List<CharacterType> types = new ArrayList<CharacterType>();
    Collections.addAll(types, values());
    types.remove(MORTAL);
    types.remove(DRAGON_KING);
    return types.toArray(new CharacterType[types.size()]);
  }

  public static CharacterType[] getCelestialExaltTypes() {
    List<CharacterType> types = new ArrayList<CharacterType>();
    Collections.addAll(types, getAllExaltTypes());
    types.remove(DB);
    return types.toArray(new CharacterType[types.size()]);
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return getId();
  }

  public static boolean isExaltType(CharacterType characterType) {
    return ArrayUtilities.contains(getAllExaltTypes(), characterType);
  }
}