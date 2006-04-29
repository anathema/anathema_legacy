package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.magic.charms.CharmException;

import org.dom4j.Element;

public class HeaderStringBuilder {

  private final String attributeId;

  public HeaderStringBuilder(String attributeId) {
    this.attributeId = attributeId;
  }

  public String build(Element element) throws CharmException {
    final String value = element.attributeValue(attributeId);
    if (StringUtilities.isNullOrTrimEmpty(value)) {
      throw new CharmException("Attribute must not be empty:" + attributeId); //$NON-NLS-1$
    }
    return value;
  }
}