package net.sf.anathema.character.magic.parser.charms;

import net.sf.anathema.character.magic.charm.CharmException;
import net.sf.anathema.character.magic.charm.ICharmXMLConstants;
import net.sf.anathema.hero.traits.model.ValuedTraitType;
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