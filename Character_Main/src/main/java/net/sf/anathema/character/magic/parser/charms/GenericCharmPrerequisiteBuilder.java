package net.sf.anathema.character.magic.parser.charms;

import net.sf.anathema.character.magic.charm.CharmException;
import net.sf.anathema.character.magic.parser.charms.prerequisite.CharmPrerequisiteBuilder;
import net.sf.anathema.hero.traits.model.TraitType;
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
