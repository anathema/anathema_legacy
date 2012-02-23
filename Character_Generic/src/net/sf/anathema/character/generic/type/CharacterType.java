package net.sf.anathema.character.generic.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;

import static java.util.Arrays.asList;
import static net.disy.commons.core.util.ArrayUtilities.containsValue;

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
  INFERNAL("Infernal") {//$NON-NLS-1$

    @Override
    public void accept(ICharacterTypeVisitor visitor) {
      visitor.visitInfernal(this);
    }

    @Override
    public FavoringTraitType getFavoringTraitType() {
      return FavoringTraitType.YoziType;
    }
  },
  SPIRIT("Spirit") {//$NON-NLS-1$

    @Override
    public void accept(ICharacterTypeVisitor visitor) {
      visitor.visitSpirit(this);
    }
  },
  GHOST("Ghost") {//$NON-NLS-1$

    @Override
    public void accept(ICharacterTypeVisitor visitor) {
      visitor.visitGhost(this);
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
    types.remove(GHOST);
    types.remove(SPIRIT);
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
    return containsValue(getAllExaltTypes(), this);
  }

  @Override
  public FavoringTraitType getFavoringTraitType() {
    return FavoringTraitType.AbilityType;
  }

  public static Iterable<ICharacterType> getAllEssenceUsers() {
    return Lists.asList(SPIRIT, GHOST, getAllExaltTypes());
  }

  public boolean isEssenceUser() {
    return Iterables.contains(getAllEssenceUsers(), this);
  }
}