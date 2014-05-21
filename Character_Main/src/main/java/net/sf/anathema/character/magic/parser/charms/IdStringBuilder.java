package net.sf.anathema.character.magic.parser.charms;

import net.sf.anathema.character.magic.charm.CharmException;
import net.sf.anathema.character.magic.charm.ICharmXMLConstants;
import net.sf.anathema.lib.lang.StringUtilities;
import org.dom4j.Element;

public class IdStringBuilder implements IIdStringBuilder {

  @Override
  public String build(Element element) throws CharmException {
    String value = element.attributeValue(ICharmXMLConstants.ATTRIB_ID);
    if (StringUtilities.isNullOrTrimmedEmpty(value)) {
      throw new CharmException("Id must not be empty.");
    }
    return value;
  }
}