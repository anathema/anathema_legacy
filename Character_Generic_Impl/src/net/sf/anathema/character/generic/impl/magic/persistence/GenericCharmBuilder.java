package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericComboRulesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericIdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericAttributeRequirementBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericTraitPrerequisitesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.ICharmPrerequisiteBuilder;
import net.sf.anathema.character.generic.traits.ITraitType;

public class GenericCharmBuilder extends CharmBuilder implements ICharmBuilder, IGenericsBuilder {

  private final GenericCharmPrerequisiteBuilder charmPrerequisiteBuilder;
  private final GenericIdStringBuilder idBuilder;
  private final GenericTraitPrerequisitesBuilder traitBuilder;
  private final GenericAttributeRequirementBuilder attributeRequirementBuilder;
  private final GenericComboRulesBuilder comboBuilder;

  public GenericCharmBuilder(
      GenericIdStringBuilder idBuilder,
      GenericTraitPrerequisitesBuilder traitBuilder,
      GenericAttributeRequirementBuilder attributeRequirementBuilder,
      GenericComboRulesBuilder comboBuilder,
      GenericCharmPrerequisiteBuilder charmPrerequisiteBuilder) {
    super(idBuilder, traitBuilder, attributeRequirementBuilder, comboBuilder, charmPrerequisiteBuilder);
    this.idBuilder = idBuilder;
    this.traitBuilder = traitBuilder;
    this.attributeRequirementBuilder = attributeRequirementBuilder;
    this.comboBuilder = comboBuilder;
    this.charmPrerequisiteBuilder = charmPrerequisiteBuilder;
  }

  public void setType(ITraitType type) {
    idBuilder.setType(type);
    traitBuilder.setType(type);
    attributeRequirementBuilder.setType(type);
    comboBuilder.setType(type);
    charmPrerequisiteBuilder.setType(type);
  }
}