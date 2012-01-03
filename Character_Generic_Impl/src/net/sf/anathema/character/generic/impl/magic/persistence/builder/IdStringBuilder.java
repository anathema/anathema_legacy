package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants;
import net.sf.anathema.character.generic.magic.charms.CharmException;

import org.dom4j.Element;

public class IdStringBuilder implements IIdStringBuilder {

  public String build(Element element) throws CharmException {
    final String value = element.attributeValue(ICharmXMLConstants.ATTRIB_ID);
    if (StringUtilities.isNullOrTrimmedEmpty(value)) {
      throw new CharmException("Id must not be empty."); //$NON-NLS-1$
    }
    return value;
  }
}