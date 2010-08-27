package net.sf.anathema.character.generic.impl.magic.persistence;

import java.util.Collection;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericCharmUtilities;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.CharmPrerequisiteBuilder;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.traits.ITraitType;

import org.dom4j.Element;

public class GenericCharmPrerequisiteBuilder extends CharmPrerequisiteBuilder implements IGenericsBuilder {

  private ITraitType type;

  @Override
  protected Collection<String> getCharmIds(Element parent) throws CharmException {
    return GenericCharmUtilities.getAllReferencedGenericCharms(parent, type);
  }

  public void setType(ITraitType type) {
    this.type = type;
  }
}
