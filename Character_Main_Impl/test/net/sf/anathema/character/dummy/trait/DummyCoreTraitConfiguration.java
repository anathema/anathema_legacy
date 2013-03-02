package net.sf.anathema.character.dummy.trait;

import com.google.common.base.Predicate;
import net.sf.anathema.character.generic.dummy.DummyCharacterModelContext;
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
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.sf.anathema.lib.lang.ArrayUtilities.getFirst;

public class DummyCoreTraitConfiguration extends AbstractTraitCollection implements ICoreTraitConfiguration {

  private final MultiEntryMap<String, ITraitType> abilityGroupsByType = new MultiEntryMap<>();
  private ISpecialtiesConfiguration specialtyConfiguration;

  @Override
  public IBackgroundConfiguration getBackgrounds() {
    throw new NotYetImplementedException();
  }

  private IIdentifiedTraitTypeGroup getAttributeTypeGroup(final AttributeGroupType type) {
    IIdentifiedCasteTraitTypeGroup[] allAttributeTypeGroups = getAttributeTypeGroups();
    return getFirst(allAttributeTypeGroups, new Predicate<IIdentifiedCasteTraitTypeGroup>() {
      @Override
      public boolean apply(IIdentifiedCasteTraitTypeGroup group) {
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
      if (!net.sf.anathema.lib.lang.ArrayUtilities.containsValue(getAllTraits(attributeGroupType), trait)) {
        return false;
      }
    }
    return true;
  }

  public void addTestTrait(ITrait trait) {
    addTrait(trait);
  }

  @Override
  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups() {
    List<IIdentifiedTraitTypeGroup> groups = new ArrayList<>();
    for (String groupId : abilityGroupsByType.keySet()) {
      List<ITraitType> traitTypes = abilityGroupsByType.get(groupId);
      groups.add(new IdentifiedTraitTypeGroup(
              traitTypes.toArray(new ITraitType[traitTypes.size()]),
          new Identifier(groupId)));
    }
    return groups.toArray(new IIdentifiedTraitTypeGroup[groups.size()]);
  }
  
  @Override
  public ISpecialtiesConfiguration getSpecialtyConfiguration() {
    if (specialtyConfiguration == null) {
      specialtyConfiguration = new SpecialtiesConfiguration(
          this,
          getAbilityTypeGroups(),
          new DummyCharacterModelContext());
    }
    return specialtyConfiguration;
  }

  @Override
  public final IIdentifiedCasteTraitTypeGroup[] getAttributeTypeGroups() {
    return new IIdentifiedCasteTraitTypeGroup[] {
        new IdentifiedAttributeTypeGroup(AttributeGroupType.Physical),
        new IdentifiedAttributeTypeGroup(AttributeGroupType.Social),
        new IdentifiedAttributeTypeGroup(AttributeGroupType.Mental) };
  }

  public void addAbilityTypeToGroup(AbilityType traitType, String id) {
    abilityGroupsByType.add(id, traitType);
  }

  @Override
  public IFavorableTrait[] getAllAbilities() {
    List<ITraitType> abilityTypes = new ArrayList<>();
    for (IIdentifiedTraitTypeGroup group : getAbilityTypeGroups()) {
      Collections.addAll(abilityTypes, group.getAllGroupTypes());
    }
    return getFavorableTraits(abilityTypes.toArray(new ITraitType[abilityTypes.size()]));
  }
}