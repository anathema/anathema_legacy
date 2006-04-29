package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.magic.charms.CharmException;

import org.dom4j.Element;

public class IdBuilder {

  public String buildId(Element element) throws CharmException {
    final String id = element.attributeValue(ATTRIB_ID);
    if (StringUtilities.isNullOrTrimEmpty(id)) {
      throw new CharmException("Cannot process Charms without id."); //$NON-NLS-1$
    }
    return id;
  }

}
