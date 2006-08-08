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
import net.sf.anathema.character.library.trait.IFavorableModifiableTrait;
import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.library.trait.specialties.ISpecialtyConfiguration;
import net.sf.anathema.character.library.trait.specialties.SpecialtyConfiguration;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class DummyCoreTraitConfiguration extends AbstractTraitCollection implements ICoreTraitConfiguration {

  private final MultiEntryMap<String, ITraitType> abilityGroupsByType = new MultiEntryMap<String, ITraitType>();
  private ISpecialtyConfiguration specialtyConfiguration;

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

  public IModifiableTrait[] getAllTraits(AttributeGroupType groupType) {
    IIdentifiedTraitTypeGroup attributeTypeGroup = getAttributeTypeGroup(groupType);
    TraitGroup traitGroup = new TraitGroup(this, attributeTypeGroup);
    return traitGroup.getGroupTraits();
  }

  public boolean containsAllTraits(AttributeGroupType attributeGroupType, IModifiableTrait[] traits) {
    for (IModifiableTrait trait : traits) {
      if (!ArrayUtilities.contains(getAllTraits(attributeGroupType), trait)) {
        return false;
      }
    }
    return true;
  }

  public void addTestTrait(IModifiableTrait trait) {
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
  
  public ISpecialtyConfiguration getSpecialtyConfiguration() {
    if (specialtyConfiguration == null) {
      specialtyConfiguration = new SpecialtyConfiguration(this, getAbilityTypeGroups());
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

  public IFavorableModifiableTrait[] getAllAbilities() {
    List<ITraitType> abilityTypes = new ArrayList<ITraitType>();
    for (IIdentifiedTraitTypeGroup group : getAbilityTypeGroups()) {
      Collections.addAll(abilityTypes, group.getAllGroupTypes());
    }
    return getFavorableTraits(abilityTypes.toArray(new ITraitType[abilityTypes.size()]));
  }
}