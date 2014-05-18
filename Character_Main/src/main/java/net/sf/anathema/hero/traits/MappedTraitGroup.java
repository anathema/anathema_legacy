package net.sf.anathema.hero.traits;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.TraitGroup;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.groups.IdentifiedTraitTypeList;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class MappedTraitGroup implements TraitGroup {
  private final TraitMap traitMap;
  private final IdentifiedTraitTypeList typeGroup;

  public MappedTraitGroup(TraitMap traitMap, IdentifiedTraitTypeList typeGroup) {
    this.traitMap = traitMap;
    this.typeGroup = typeGroup;
  }

  @Override
  public Trait[] getGroupTraits() {
    List<Trait> traits = new ArrayList<>();
    for (TraitType type : typeGroup.getAll()) {
      traits.add(traitMap.getTrait(type));
    }
    return traits.toArray(new Trait[traits.size()]);
  }

  @Override
  public Identifier getGroupId() {
    return typeGroup.getListId();
  }
}
