package net.sf.anathema.character.impl.model.charm;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;

public class PrerequisiteSetBuilder {

  private final Set<ITraitType> prerequisiteSet = new HashSet<ITraitType>();

  public PrerequisiteSetBuilder() {
    prerequisiteSet.add(OtherTraitType.Essence);
  }

  public void addCharms(ICharm... charms) {
    for (ICharm charm : charms) {
      addCharm(charm);
    }
  }

  private void addCharm(ICharm charm) {
    for (IGenericTrait prerequisite : charm.getPrerequisites()) {
      prerequisiteSet.add(prerequisite.getType());
    }
  }

  public Collection<ITraitType> getAllPrerequisites() {
    return Collections.unmodifiableCollection(prerequisiteSet);
  }
}