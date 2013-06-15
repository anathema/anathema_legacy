package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.impl.rules.SourceBook;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_SOURCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_SOURCE;

public class SourceBuilder {

  public IExaltedSourceBook[] buildSourceList(Element magicElement) {
    List<IExaltedSourceBook> sources = new ArrayList<>();
    List<Element> sourceElements = ElementUtilities.elements(magicElement, TAG_SOURCE);
    for (Element sourceElement : sourceElements) {
      String source = sourceElement.attributeValue(ATTRIB_SOURCE);
      sources.add(new SourceBook(source));
    }
    return sources.toArray(new IExaltedSourceBook[sources.size()]);
  }
}