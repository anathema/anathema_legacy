package net.sf.anathema.charmentry.persistence;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_EXALT;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_GROUP;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;

import net.sf.anathema.character.generic.magic.ICharmData;

import org.dom4j.Element;

public class HeadDataWriter {

  public void write(ICharmData charm, Element charmElement) {
    charmElement.addAttribute(ATTRIB_ID, charm.getId());
    charmElement.addAttribute(ATTRIB_EXALT, charm.getCharacterType().getId());
    charmElement.addAttribute(ATTRIB_GROUP, charm.getGroupId());
  }
}