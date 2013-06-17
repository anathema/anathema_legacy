package net.sf.anathema.character.main.testing.dummy.trait;

import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.main.attributes.AttributeModel;

public class DummyAttributeModel implements AttributeModel {

  private DummyCoreTraitConfiguration configuration;

  public DummyAttributeModel(DummyCoreTraitConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public Trait[] getAll() {
    return configuration.getTraits(AttributeType.values());
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
  public IIdentifiedTraitTypeGroup[] getAttributeTypeGroups() {
    return configuration.getAttributeTypeGroups();
  }

  @Override
  public Trait getTrait(TraitType traitType) {
    return configuration.getTrait(traitType);
  }

  @Override
  public Trait[] getTraits(TraitType... traitTypes) {
    return configuration.getTraits(traitTypes);
  }
}
