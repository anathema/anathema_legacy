package net.sf.anathema.character.main.testing.dummy.trait;

import com.google.common.base.Predicate;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedAttributeTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.AbstractTraitMap;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesConfiguration;
import net.sf.anathema.character.main.abilities.AbilityModel;
import net.sf.anathema.character.main.attributes.model.temporary.AttributeModel;
import net.sf.anathema.character.main.testing.dummy.DummyCharacterModelContext;
import net.sf.anathema.character.main.traits.model.MappedTraitGroup;
import net.sf.anathema.character.main.traits.model.TraitMap;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.generic.traits.types.AttributeGroupType.Mental;
import static net.sf.anathema.character.generic.traits.types.AttributeGroupType.Physical;
import static net.sf.anathema.character.generic.traits.types.AttributeGroupType.Social;
import static net.sf.anathema.lib.lang.ArrayUtilities.getFirst;

public class DummyCoreTraitConfiguration extends AbstractTraitMap implements TraitMap {

  private final MultiEntryMap<String, TraitType> abilityGroupsByType = new MultiEntryMap<>();
  private ISpecialtiesConfiguration specialtyConfiguration;

  private IIdentifiedTraitTypeGroup getAttributeTypeGroup(final AttributeGroupType type) {
    IIdentifiedTraitTypeGroup[] allAttributeTypeGroups = getAttributeTypeGroups();
    return getFirst(allAttributeTypeGroups, new Predicate<IIdentifiedTraitTypeGroup>() {
      @Override
      public boolean apply(IIdentifiedTraitTypeGroup group) {
        return group.getGroupId() == type;
      }
    });
  }

  public Trait[] getAllTraits(AttributeGroupType groupType) {
    IIdentifiedTraitTypeGroup attributeTypeGroup = getAttributeTypeGroup(groupType);
    TraitGroup traitGroup = new MappedTraitGroup(this, attributeTypeGroup);
    return traitGroup.getGroupTraits();
  }

  public boolean containsAllTraits(AttributeGroupType attributeGroupType, Trait[] traits) {
    for (Trait trait : traits) {
      if (!ArrayUtils.contains(getAllTraits(attributeGroupType), trait)) {
        return false;
      }
    }
    return true;
  }

  public void addTestTrait(Trait trait) {
    addTraits(trait);
  }

  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups() {
    List<IIdentifiedTraitTypeGroup> groups = new ArrayList<>();
    for (String groupId : abilityGroupsByType.keySet()) {
      List<TraitType> traitTypes = abilityGroupsByType.get(groupId);
      groups.add(new IdentifiedTraitTypeGroup(traitTypes.toArray(new TraitType[traitTypes.size()]), new SimpleIdentifier(groupId)));
    }
    return groups.toArray(new IIdentifiedTraitTypeGroup[groups.size()]);
  }

  public ISpecialtiesConfiguration getSpecialtyConfiguration() {
    if (specialtyConfiguration == null) {
      specialtyConfiguration = new SpecialtiesConfiguration(this, getAbilityTypeGroups(), new DummyCharacterModelContext());
    }
    return specialtyConfiguration;
  }

  public final IIdentifiedTraitTypeGroup[] getAttributeTypeGroups() {
    return new IIdentifiedTraitTypeGroup[]{new IdentifiedAttributeTypeGroup(Physical), new IdentifiedAttributeTypeGroup(Social),
            new IdentifiedAttributeTypeGroup(Mental)};
  }

  public void addAbilityTypeToGroup(AbilityType traitType, String id) {
    abilityGroupsByType.add(id, traitType);
  }

  public AttributeModel getAttributeModel() {
    return new DummyAttributeModel(this);
  }

  public AbilityModel getAbilityModel() {
    return new DummyAbilityModel(this);
  }

  public TraitGroup createGroup(final AttributeGroupType groupType, final AttributeType... types) {
    return new TraitGroup() {
      @Override
      public Trait[] getGroupTraits() {
        return getTraits(types);
      }

      @Override
      public Identifier getGroupId() {
        return groupType;
      }
    };
  }
}