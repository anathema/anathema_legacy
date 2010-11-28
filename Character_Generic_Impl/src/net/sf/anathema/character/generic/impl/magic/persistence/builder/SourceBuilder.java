package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_SOURCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_SOURCE;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class SourceBuilder {

  public IExaltedSourceBook[] buildSourceList(Element magicElement) {
    List<IExaltedSourceBook> sources = new ArrayList<IExaltedSourceBook>();
    List<Element> sourceElements = ElementUtilities.elements(magicElement, TAG_SOURCE);
    for (Element sourceElement : sourceElements) {
      String source = sourceElement.attributeValue(ATTRIB_SOURCE);
      sources.add(ExaltedSourceBook.valueOf(source));
    }
    return sources.toArray(new IExaltedSourceBook[sources.size()]);
  }
}