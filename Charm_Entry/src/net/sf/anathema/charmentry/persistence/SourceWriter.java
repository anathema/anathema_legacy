package net.sf.anathema.charmentry.persistence;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_SOURCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_SOURCE;
import net.sf.anathema.character.generic.magic.ICharmData;

import org.dom4j.Element;

public class SourceWriter {

  public void write(ICharmData charm, Element charmElement) {
    Element sourceElement = charmElement.addElement(TAG_SOURCE);
    sourceElement.addAttribute(ATTRIB_SOURCE, charm.getSource().getId());
  }
}