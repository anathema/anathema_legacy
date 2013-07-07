package net.sf.anathema.character.main.magic.persistence;

import net.sf.anathema.character.main.magic.persistence.builder.GenericCharmUtilities;
import net.sf.anathema.character.main.magic.persistence.builder.prerequisite.CharmPrerequisiteBuilder;
import net.sf.anathema.character.main.magic.charms.CharmException;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.lib.util.Identifier;
import org.dom4j.Element;

import java.util.Collection;

public class GenericCharmPrerequisiteBuilder extends CharmPrerequisiteBuilder implements IGenericsBuilder {

  private Identifier type;

  @Override
  protected Collection<String> getCharmIds(Element parent) throws CharmException {
    return GenericCharmUtilities.getAllReferencedGenericCharms(parent, type);
  }

  @Override
  public void setType(TraitType type) {
    this.type = type;
  }
}
