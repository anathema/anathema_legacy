package net.sf.anathema.character.main.magic.parser.charms.prerequisite;

import net.sf.anathema.character.main.traits.TraitTypeUtils;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import static net.sf.anathema.character.main.magic.model.charm.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.main.magic.model.charm.ICharmXMLConstants.ATTRIB_VALUE;

public class TraitPrerequisiteBuilder implements ITraitPrerequisiteBuilder {
  private final TraitTypeUtils traitUtils = new TraitTypeUtils();

  @Override
  public ValuedTraitType build(Element element) throws PersistenceException {
    TraitType propertyType = traitUtils.getTraitTypeById(element.attributeValue(ATTRIB_ID));
    int minValue = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_VALUE);
    return new net.sf.anathema.character.main.traits.types.ValuedTraitType(propertyType, minValue);
  }
}