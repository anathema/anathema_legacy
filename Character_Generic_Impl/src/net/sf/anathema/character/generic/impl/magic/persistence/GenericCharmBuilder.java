package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericIdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericAttributeRequirementBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericTraitPrerequisitesBuilder;
import net.sf.anathema.character.generic.traits.ITraitType;

public class GenericCharmBuilder extends CharmBuilder implements ICharmBuilder {

  private final GenericIdStringBuilder idBuilder;
  private final GenericTraitPrerequisitesBuilder traitBuilder;
  private final GenericAttributeRequirementBuilder builder;

  public GenericCharmBuilder(
      GenericIdStringBuilder idBuilder,
      GenericTraitPrerequisitesBuilder traitBuilder,
      GenericAttributeRequirementBuilder builder) {
    super(idBuilder, traitBuilder, builder);
    this.idBuilder = idBuilder;
    this.traitBuilder = traitBuilder;
    this.builder = builder;
  }

  public void setType(ITraitType type) {
    idBuilder.setType(type);
    traitBuilder.setType(type);
    builder.setType(type);
  }
}