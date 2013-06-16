package net.sf.anathema.character.main.testing.dummy.trait;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.main.abilities.AbilityModel;

public class DummyAbilityModel implements AbilityModel {

  private DummyCoreTraitConfiguration configuration;

  public DummyAbilityModel(DummyCoreTraitConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public Trait[] getAll() {
    return configuration.getTraits(AbilityType.values());
  }

  @Override
  public TraitGroup[] getTraitGroups() {
    TraitGroup physical = configuration.createGroup(AttributeGroupType.Physical, AttributeType.Strength, AttributeType.Dexterity,
            AttributeType.Stamina);
    TraitGroup social = configuration.createGroup(AttributeGroupType.Social, AttributeType.Charisma, AttributeType.Manipulation,
            AttributeType.Appearance);
    TraitGroup mental = configuration.createGroup(AttributeGroupType.Mental, AttributeType.Perception, AttributeType.Intelligence,
            AttributeType.Wits);
    return new TraitGroup[]{physical, social, mental};
  }

  @Override
  public Trait getTrait(TraitType type) {
    return getTrait(type);
  }

  @Override
  public Trait[] getTraits(TraitType... traitTypes) {
    return configuration.getTraits(traitTypes);
  }

  @Override
  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups() {
    return configuration.getAbilityTypeGroups();
  }

  @Override
  public ISpecialtiesConfiguration getSpecialtyConfiguration() {
    return configuration.getSpecialtyConfiguration();
  }
}
