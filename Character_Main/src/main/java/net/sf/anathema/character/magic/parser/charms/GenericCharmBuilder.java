package net.sf.anathema.character.magic.parser.charms;

import net.sf.anathema.character.magic.parser.charms.prerequisite.GenericAttributePrerequisiteBuilder;
import net.sf.anathema.character.magic.parser.charms.prerequisite.GenericTraitPrerequisitesBuilder;
import net.sf.anathema.character.magic.parser.charms.special.ReflectionSpecialCharmParser;
import net.sf.anathema.character.magic.parser.combos.GenericComboRulesBuilder;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.character.framework.type.CharacterTypes;

public class GenericCharmBuilder extends CharmBuilder implements ICharmBuilder, IGenericsBuilder {

  private final GenericCharmPrerequisiteBuilder charmPrerequisiteBuilder;
  private final GenericIdStringBuilder idBuilder;
  private final GenericTraitPrerequisitesBuilder traitBuilder;
  private final GenericAttributePrerequisiteBuilder attributeRequirementBuilder;
  private final GenericComboRulesBuilder comboBuilder;

  public GenericCharmBuilder(GenericIdStringBuilder idBuilder, GenericTraitPrerequisitesBuilder traitBuilder,
                             GenericAttributePrerequisiteBuilder attributeRequirementBuilder, GenericComboRulesBuilder comboBuilder,
                             GenericCharmPrerequisiteBuilder charmPrerequisiteBuilder, CharacterTypes characterTypes,
                             ReflectionSpecialCharmParser specialCharmParser) {
    super(idBuilder, traitBuilder, attributeRequirementBuilder, comboBuilder, charmPrerequisiteBuilder, characterTypes, specialCharmParser);
    this.idBuilder = idBuilder;
    this.traitBuilder = traitBuilder;
    this.attributeRequirementBuilder = attributeRequirementBuilder;
    this.comboBuilder = comboBuilder;
    this.charmPrerequisiteBuilder = charmPrerequisiteBuilder;
  }

  @Override
  public void setType(TraitType type) {
    idBuilder.setType(type);
    traitBuilder.setType(type);
    attributeRequirementBuilder.setType(type);
    comboBuilder.setType(type);
    charmPrerequisiteBuilder.setType(type);
  }

  @Override
  protected boolean isBuildingGenericCharms() {
    return true;
  }
}