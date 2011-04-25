package net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_VALUE;

import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class BackgroundPrerequisiteBuilder implements ITraitPrerequisiteBuilder {

  public IGenericTrait build(Element element) throws PersistenceException {
    ITraitType propertyType = new CustomizedBackgroundTemplate(element.attributeValue(ATTRIB_ID));
    int minValue = ElementUtilities.getRequiredIntAttrib(element, ATTRIB_VALUE);
    return new ValuedTraitType(propertyType, minValue);
  }
}