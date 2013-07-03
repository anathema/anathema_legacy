package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.traits.ValuedTraitType;
import org.dom4j.Element;

public class GroupStringBuilder {

  public String build(Element rootElement, ValuedTraitType trait) throws CharmException {
    String value = rootElement.attributeValue(ICharmXMLConstants.ATTRIB_GROUP);
    if (value != null) {
      return value;
    }
    if (trait != null) {
      return trait.getType().getId();
    }
    throw new CharmException("Cannot determine group: No group identification found and no primary trait set to default to.");
  }
}