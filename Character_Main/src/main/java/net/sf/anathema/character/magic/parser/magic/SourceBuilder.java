package net.sf.anathema.character.magic.parser.magic;

import net.sf.anathema.character.magic.basic.source.SourceBook;
import net.sf.anathema.character.magic.basic.source.SourceBookImpl;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.ATTRIB_SOURCE;
import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.TAG_SOURCE;

public class SourceBuilder {

  public SourceBook[] buildSourceList(Element magicElement) {
    List<SourceBook> sources = new ArrayList<>();
    List<Element> sourceElements = ElementUtilities.elements(magicElement, TAG_SOURCE);
    for (Element sourceElement : sourceElements) {
      String source = sourceElement.attributeValue(ATTRIB_SOURCE);
      sources.add(new SourceBookImpl(source));
    }
    return sources.toArray(new SourceBook[sources.size()]);
  }
}