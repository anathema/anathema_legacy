package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ATTRIBUTE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_VISUALIZE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_ATTRIBUTE;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharmAttributeBuilder {

  public ICharmAttribute[] buildCharmAttributes(Element rulesElement, IGenericTrait primaryPrerequisite) {
    List<ICharmAttribute> attributes = new ArrayList<ICharmAttribute>();
    for (Element attributeElement : ElementUtilities.elements(rulesElement, TAG_ATTRIBUTE)) {
      String attributeId = attributeElement.attributeValue(ATTRIB_ATTRIBUTE);
      boolean visualizeAttribute = ElementUtilities.getBooleanAttribute(attributeElement, ATTRIB_VISUALIZE, false);
      attributes.add(new CharmAttribute(attributeId, visualizeAttribute));
    }
    if (primaryPrerequisite != null) {
      attributes.add(new CharmAttribute(primaryPrerequisite.getType().getId(), false));
    }
    return attributes.toArray(new ICharmAttribute[attributes.size()]);
  }
}