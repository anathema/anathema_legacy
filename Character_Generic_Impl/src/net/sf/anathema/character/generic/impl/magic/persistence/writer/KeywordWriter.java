package net.sf.anathema.character.generic.impl.magic.persistence.writer;

import java.util.List;

import net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class KeywordWriter {

  public void write(List<ICharmAttribute> keywords, Element charmElement) {
    for (ICharmAttribute keyword : keywords) {
      Element element = charmElement.addElement(ICharmXMLConstants.TAG_ATTRIBUTE);
      element.addAttribute(ICharmXMLConstants.ATTRIB_ATTRIBUTE, keyword.getId());
      ElementUtilities.addAttribute(element, ICharmXMLConstants.ATTRIB_VISUALIZE, keyword.isVisualized());
    }
  }
}