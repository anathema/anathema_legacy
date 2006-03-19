package net.sf.anathema.character.generic.impl.magic.persistence.writer;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_PAGE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_SOURCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_SOURCE;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.magic.ICharmData;

import org.dom4j.Element;

public class SourceWriter {

  private final static String CUSTOM = "Custom"; //$NON-NLS-1$
  
  public void write(ICharmData charm, Element charmElement) {
    String source = charm.getSource().getSource();
    if (StringUtilities.isNullOrEmpty(source) || source.equalsIgnoreCase(CUSTOM)) {
      return;// Note: No Source == Custom Charm
    }
    Element sourceElement = charmElement.addElement(TAG_SOURCE);
    sourceElement.addAttribute(ATTRIB_SOURCE, source);
    sourceElement.addAttribute(ATTRIB_PAGE, charm.getSource().getPage());
  }
}