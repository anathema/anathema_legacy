package net.sf.anathema.character.generic.impl.magic.persistence;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_RENAMES;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_RENAME;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.Element;

public class CharmRenameBuilder
{
  private static final String ATTRIB_FROM = "from";
  private static final String ATTRIB_TO = "to";
  
  public Map<String, String> buildRenames(Document charmDoc) {
    Element charmListElement = charmDoc.getRootElement();
    return readRenames(charmListElement);
  }

  private Map<String, String> readRenames(Element charmListElement) {
    Element renameElement = charmListElement.element(TAG_RENAMES);
    if (renameElement == null) {
      return null;
    }
    Map<String, String> renames = new HashMap<String, String>();
    for (Element renamedElement : ElementUtilities.elements(renameElement, TAG_RENAME)) {
      String from = renamedElement.attributeValue(ATTRIB_FROM);
      String to = renamedElement.attributeValue(ATTRIB_TO);
      renames.put(from, to);
    }
    return renames;    
  }
}