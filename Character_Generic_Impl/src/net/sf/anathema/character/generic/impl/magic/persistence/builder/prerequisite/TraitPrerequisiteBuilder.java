package net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_VALUE;

import net.sf.anathema.character.generic.impl.traits.TraitTypeUtils;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class TraitPrerequisiteBuilder implements ITraitPrerequisiteBuilder {
  private final TraitTypeUtils traitUtils = new TraitTypeUtils();

  public IGenericTrait build(Element element) throws PersistenceException {
    ITraitType propertyType = traitUtils.getTraitTypeById(element.attributeValue(ATTRIB_ID));
    int minValue = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_VALUE);
    return new ValuedTraitType(propertyType, minValue);
  }
}