package net.sf.anathema.character.ghost.trait;

import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;

public class VirtueFavoringType implements FavoringTraitType {
  @Override
  public VirtueType[] getTraitTypesForGenericCharms() {
    return net.sf.anathema.character.generic.traits.types.VirtueType.values();
  }

  @Override
  public ITraitType getSpellFavoringType() {
    throw new UnsupportedOperationException("Ghosts cannot learn Spells.");
  }

  @Override
  public String getId() {
    return "VirtueType";
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof VirtueFavoringType;
  }

  @Override
  public int hashCode() {
    return 3;
  }
}