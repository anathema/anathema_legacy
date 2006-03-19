package net.sf.anathema.character.generic.impl.magic.persistence.writer;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARMTYPE;

import net.sf.anathema.character.generic.magic.ICharmData;

import org.dom4j.Element;

public class CharmTypeWriter {

  public void write(ICharmData charm, Element charmElement) {
    Element typeElement = charmElement.addElement(TAG_CHARMTYPE);
    typeElement.addAttribute(ATTRIB_TYPE, charm.getCharmType().getId());
  }
}