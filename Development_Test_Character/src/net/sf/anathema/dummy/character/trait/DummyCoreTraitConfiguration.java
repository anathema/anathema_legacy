package net.sf.anathema.dummy.character.trait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedAttributeTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.library.trait.AbstractTraitCollection;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesConfiguration;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.dummy.character.DummyCharacterModelContext;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class DummyCoreTraitConfiguration extends AbstractTraitCollection implements ICoreTraitConfiguration {

  private final MultiEntryMap<String, ITraitType> abilityGroupsByType = new MultiEntryMap<String, ITraitType>();
  private ISpecialtiesConfiguration specialtyConfiguration;

  public IBackgroundConfiguration getBackgrounds() {
    throw new NotYetImplementedException();
  }

  private IIdentifiedTraitTypeGroup getAttributeTypeGroup(final AttributeGroupType type) {
    IIdentifiedCasteTraitTypeGroup[] allAttributeTypeGroups = getAttributeTypeGroups();
    return ArrayUtilities.getFirst(allAttributeTypeGroups, new IPredicate<IIdentifiedCasteTraitTypeGroup>() {
      public boolean evaluate(IIdentifiedCasteTraitTypeGroup group) {
        return group.getGroupId() == type;
      }
    });
  }

  public ITrait[] getAllTraits(AttributeGroupType groupType) {
    IIdentifiedTraitTypeGroup attributeTypeGroup = getAttributeTypeGroup(groupType);
    TraitGroup traitGroup = new TraitGroup(this, attributeTypeGroup);
    return traitGroup.getGroupTraits();
  }

  public boolean containsAllTraits(AttributeGroupType attributeGroupType, ITrait[] traits) {
    for (ITrait trait : traits) {
      if (!ArrayUtilities.contains(getAllTraits(attributeGroupType), trait)) {
        return false;
      }
    }
    return true;
  }

  public void addTestTrait(ITrait trait) {
    addTrait(trait);
  }

  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups() {
    List<IIdentifiedTraitTypeGroup> groups = new ArrayList<IIdentifiedTraitTypeGroup>();
    for (String groupId : abilityGroupsByType.keySet()) {
      groups.add(new IdentifiedTraitTypeGroup(
          abilityGroupsByType.get(groupId).toArray(new ITraitType[0]),
          new Identificate(groupId)));
    }
    return groups.toArray(new IIdentifiedTraitTypeGroup[groups.size()]);
  }
  
  public ISpecialtiesConfiguration getSpecialtyConfiguration() {
    if (specialtyConfiguration == null) {
      specialtyConfiguration = new SpecialtiesConfiguration(new DummyCharacterModelContext());
    }
    return specialtyConfiguration ;
  }

  public final IIdentifiedCasteTraitTypeGroup[] getAttributeTypeGroups() {
    return new IIdentifiedCasteTraitTypeGroup[] {
        new IdentifiedAttributeTypeGroup(AttributeGroupType.Physical),
        new IdentifiedAttributeTypeGroup(AttributeGroupType.Social),
        new IdentifiedAttributeTypeGroup(AttributeGroupType.Mental) };
  }

  public IIdentificate getAbilityGroupId(AbilityType abilityType) {
    for (IIdentifiedTraitTypeGroup group : getAbilityTypeGroups()) {
      if (group.contains(abilityType)) {
        return group.getGroupId();
      }
    }
    throw new IllegalStateException("Ability type in no group: " + abilityType); //$NON-NLS-1$
  }

  public void addAbilityTypeToGroup(AbilityType traitType, String id) {
    abilityGroupsByType.add(id, traitType);
  }

  public IFavorableTrait[] getAllAbilities() {
    List<ITraitType> abilityTypes = new ArrayList<ITraitType>();
    for (IIdentifiedTraitTypeGroup group : getAbilityTypeGroups()) {
      Collections.addAll(abilityTypes, group.getAllGroupTypes());
    }
    return getFavorableTraits(abilityTypes.toArray(new ITraitType[abilityTypes.size()]));
  }
}