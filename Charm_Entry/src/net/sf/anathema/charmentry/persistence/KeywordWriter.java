package net.sf.anathema.charmentry.persistence;

import net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class KeywordWriter {

  public void write(ICharmData charm, Element charmElement) {
    for (ICharmAttribute keyword : charm.getAttributes()) {
      Element element = charmElement.addElement(ICharmXMLConstants.TAG_ATTRIBUTE);
      element.addAttribute(ICharmXMLConstants.ATTRIB_ATTRIBUTE, keyword.getId());
      ElementUtilities.addAttribute(element, ICharmXMLConstants.ATTRIB_VISUALIZE, keyword.isVisualized());
    }
  }
}