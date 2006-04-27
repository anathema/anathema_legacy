package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARMTYPE;

import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;

import org.dom4j.Element;

public class CharmTypeBuilder {

  public CharmType build(Element rulesElement) throws CharmException {
    CharmType charmType;
    Element typeElement = rulesElement.element(TAG_CHARMTYPE);
    if (typeElement == null) {
      throw new CharmException("Bad type in charm. (Element required)"); //$NON-NLS-1$
    }
    try {
      charmType = CharmType.valueOf(typeElement.attributeValue(ATTRIB_TYPE));
    }
    catch (IllegalArgumentException e) {
      throw new CharmException("Bad type in charm. (Type unreadable)"); //$NON-NLS-1$
    }
    catch (NullPointerException e) {
      throw new CharmException("Bad type in charm. (Attribute required)"); //$NON-NLS-1$
    }
    return charmType;
  }
}