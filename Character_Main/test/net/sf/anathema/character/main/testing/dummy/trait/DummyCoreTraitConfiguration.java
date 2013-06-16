package net.sf.anathema.character.main.testing.dummy.trait;

import com.google.common.base.Predicate;
import net.sf.anathema.character.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.main.attributes.model.temporary.AttributeModel;
import net.sf.anathema.character.main.testing.dummy.DummyCharacterModelContext;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedAttributeTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.library.trait.AbstractTraitCollection;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesConfiguration;
import net.sf.anathema.character.main.traits.model.MappedTraitGroup;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.generic.traits.types.AttributeGroupType.Mental;
import static net.sf.anathema.character.generic.traits.types.AttributeGroupType.Physical;
import static net.sf.anathema.character.generic.traits.types.AttributeGroupType.Social;
import static net.sf.anathema.lib.lang.ArrayUtilities.getFirst;

public class DummyCoreTraitConfiguration extends AbstractTraitCollection implements ICoreTraitConfiguration {

  private final MultiEntryMap<String, ITraitType> abilityGroupsByType = new MultiEntryMap<>();
  private ISpecialtiesConfiguration specialtyConfiguration;

  private IIdentifiedTraitTypeGroup getAttributeTypeGroup(final AttributeGroupType type) {
    IIdentifiedCasteTraitTypeGroup[] allAttributeTypeGroups = getAttributeTypeGroups();
    return getFirst(allAttributeTypeGroups, new Predicate<IIdentifiedCasteTraitTypeGroup>() {
      @Override
      public boolean apply(IIdentifiedCasteTraitTypeGroup group) {
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

  @Override
  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups() {
    List<IIdentifiedTraitTypeGroup> groups = new ArrayList<>();
    for (String groupId : abilityGroupsByType.keySet()) {
      List<ITraitType> traitTypes = abilityGroupsByType.get(groupId);
      groups.add(new IdentifiedTraitTypeGroup(traitTypes.toArray(new ITraitType[traitTypes.size()]), new SimpleIdentifier(groupId)));
    }
    return groups.toArray(new IIdentifiedTraitTypeGroup[groups.size()]);
  }

  @Override
  public ISpecialtiesConfiguration getSpecialtyConfiguration() {
    if (specialtyConfiguration == null) {
      specialtyConfiguration = new SpecialtiesConfiguration(this, getAbilityTypeGroups(), new DummyCharacterModelContext());
    }
    return specialtyConfiguration;
  }

  private final IIdentifiedCasteTraitTypeGroup[] getAttributeTypeGroups() {
    return new IIdentifiedCasteTraitTypeGroup[]{new IdentifiedAttributeTypeGroup(Physical), new IdentifiedAttributeTypeGroup(Social),
            new IdentifiedAttributeTypeGroup(Mental)};
  }

  public void addAbilityTypeToGroup(AbilityType traitType, String id) {
    abilityGroupsByType.add(id, traitType);
  }

  @Override
  public void addFavorableTraits(IIdentifiedCasteTraitTypeGroup[] traitGroups, IncrementChecker incrementChecker,
                                 TypedTraitTemplateFactory factory) {
    throw new NotYetImplementedException();
  }

  public AttributeModel getAttributeConfiguration() {
    return new AttributeModel() {
      @Override
      public Trait[] getAllAttributes() {
        return getTraits(AttributeType.values());
      }

      @Override
      public TraitGroup[] getTraitGroups() {
        TraitGroup physical = createGroup(AttributeGroupType.Physical, AttributeType.Strength, AttributeType.Dexterity, AttributeType.Stamina);
        TraitGroup social = createGroup(AttributeGroupType.Social, AttributeType.Charisma, AttributeType.Manipulation, AttributeType.Appearance);
        TraitGroup mental = createGroup(AttributeGroupType.Mental, AttributeType.Perception, AttributeType.Intelligence, AttributeType.Wits);
        return new TraitGroup[]{physical, social, mental};
      }

      @Override
      public Trait getTrait(AttributeType type) {
        return getTrait(type);
      }

      @Override
      public IIdentifiedTraitTypeGroup[] getAttributeTypeGroups() {
        return DummyCoreTraitConfiguration.this.getAttributeTypeGroups();
      }
    };
  }

  private TraitGroup createGroup(final AttributeGroupType groupType, final AttributeType... types) {
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