package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericIdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericTraitPrerequisitesBuilder;
import net.sf.anathema.character.generic.traits.ITraitType;

public class GenericCharmBuilder extends CharmBuilder implements ICharmBuilder {

  private final GenericIdStringBuilder idBuilder;
  private final GenericTraitPrerequisitesBuilder traitBuilder;

  public GenericCharmBuilder(GenericIdStringBuilder idBuilder, GenericTraitPrerequisitesBuilder traitBuilder) {
    super(idBuilder, traitBuilder);
    this.idBuilder = idBuilder;
    this.traitBuilder = traitBuilder;

  }

  public void setType(ITraitType type) {
    idBuilder.setType(type);
    traitBuilder.setType(type);
  }
}