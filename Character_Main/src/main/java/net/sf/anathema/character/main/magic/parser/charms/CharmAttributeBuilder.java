package net.sf.anathema.character.main.magic.parser.charms;

import net.sf.anathema.character.main.magic.model.attribute.MagicAttribute;
import net.sf.anathema.character.main.magic.model.attribute.MagicAttributeImpl;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.main.magic.charm.ICharmXMLConstants.ATTRIB_ATTRIBUTE;
import static net.sf.anathema.character.main.magic.charm.ICharmXMLConstants.ATTRIB_VALUE;
import static net.sf.anathema.character.main.magic.charm.ICharmXMLConstants.ATTRIB_VISUALIZE;
import static net.sf.anathema.character.main.magic.charm.ICharmXMLConstants.TAG_ATTRIBUTE;
import static net.sf.anathema.character.main.magic.charm.ICharmXMLConstants.TAG_GENERIC_ATTRIBUTE;

public class CharmAttributeBuilder {

  public MagicAttribute[] buildCharmAttributes(Element rulesElement, ValuedTraitType primaryPrerequisite) {
    List<MagicAttribute> attributes = new ArrayList<>();
    for (Element attributeElement : ElementUtilities.elements(rulesElement, TAG_ATTRIBUTE)) {
      String attributeId = attributeElement.attributeValue(ATTRIB_ATTRIBUTE);
      boolean visualizeAttribute = ElementUtilities.getBooleanAttribute(attributeElement, ATTRIB_VISUALIZE, false);
      String value = attributeElement.attributeValue(ATTRIB_VALUE);
      if (value == null || value.isEmpty()) {
        attributes.add(new MagicAttributeImpl(attributeId, visualizeAttribute));
      } else {
        attributes.add(new MagicAttributeImpl(attributeId, visualizeAttribute, value));
      }
    }
    if (primaryPrerequisite != null) {
      String id = primaryPrerequisite.getType().getId();
      attributes.add(new MagicAttributeImpl(id, false));
      for (Element genericAttributeElement : ElementUtilities.elements(rulesElement, TAG_GENERIC_ATTRIBUTE)) {
        String attributeId = genericAttributeElement.attributeValue(ATTRIB_ATTRIBUTE) + id;
        attributes.add(new MagicAttributeImpl(attributeId, false));
      }
    }
    return attributes.toArray(new MagicAttribute[attributes.size()]);
  }
}