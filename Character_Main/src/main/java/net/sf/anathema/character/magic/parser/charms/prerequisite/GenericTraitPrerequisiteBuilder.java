package net.sf.anathema.character.magic.parser.charms.prerequisite;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.ATTRIB_VALUE;

public class GenericTraitPrerequisiteBuilder implements ITraitPrerequisiteBuilder {

  private TraitType type;

  @Override
  public ValuedTraitType build(Element element) throws PersistenceException {
    int minValue = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_VALUE);
    return new net.sf.anathema.hero.traits.model.types.ValuedTraitType(type, minValue);
  }

  public void setType(TraitType type) {
    this.type = type;
  }
}