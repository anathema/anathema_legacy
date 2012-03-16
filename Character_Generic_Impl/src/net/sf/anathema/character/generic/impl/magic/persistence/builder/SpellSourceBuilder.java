package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.SourceBook;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_EDITION;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_SOURCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_SOURCE;

public class SpellSourceBuilder {

  public SourceBookWithEdition[] buildSourceList(Element magicElement) {
    List<SourceBookWithEdition> sources = new ArrayList<SourceBookWithEdition>();
    List<Element> sourceElements = ElementUtilities.elements(magicElement, TAG_SOURCE);
    for (Element sourceElement : sourceElements) {
      IExaltedEdition edition = ExaltedEdition.valueOf(sourceElement.attributeValue(ATTRIB_EDITION));
      IExaltedSourceBook sourceBook = new SourceBook(sourceElement.attributeValue(ATTRIB_SOURCE));
      sources.add(new SourceBookWithEdition(sourceBook, edition));
    }
    return sources.toArray(new SourceBookWithEdition[sources.size()]);
  }
}