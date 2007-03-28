package net.sf.anathema.character.generic.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;

public enum CharacterType implements ICharacterType {

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

    @Override
    public FavoringTraitType getFavoringTraitType() {
      return FavoringTraitType.AttributeType;
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

  public static ICharacterType getById(String id) {
    for (ICharacterType type : values()) {
      if (type.getId().equals(id)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No type defined for id:" + id); //$NON-NLS-1$
  }

  public static ICharacterType[] getAllExaltTypes() {
    List<ICharacterType> types = new ArrayList<ICharacterType>();
    Collections.addAll(types, values());
    types.remove(MORTAL);
    types.remove(DRAGON_KING);
    return types.toArray(new ICharacterType[types.size()]);
  }

  public static ICharacterType[] getCelestialExaltTypes() {
    List<ICharacterType> types = new ArrayList<ICharacterType>();
    Collections.addAll(types, getAllExaltTypes());
    types.remove(DB);
    return types.toArray(new ICharacterType[types.size()]);
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return getId();
  }

  public boolean isExaltType() {
    return ArrayUtilities.contains(getAllExaltTypes(), this);
  }

  @Override
  public FavoringTraitType getFavoringTraitType() {
    return FavoringTraitType.AbilityType;
  }
}