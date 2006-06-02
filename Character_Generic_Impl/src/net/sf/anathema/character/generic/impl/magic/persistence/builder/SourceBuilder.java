package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_EDITION;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_PAGE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_SOURCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_SOURCE;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.impl.magic.MagicSource;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class SourceBuilder {

  public IMagicSource[] buildSourceList(Element magicElement) {
    List<IMagicSource> sources = new ArrayList<IMagicSource>();
    List<Element> sourceElements = ElementUtilities.elements(magicElement, TAG_SOURCE);
    if (sourceElements.isEmpty()) {
      sources.add(MagicSource.CUSTOM_SOURCE);
    }
    else {
      for (Element sourceElement : sourceElements) {
        String source = sourceElement.attributeValue(ATTRIB_SOURCE);
        String page = sourceElement.attributeValue(ATTRIB_PAGE);
        IExaltedEdition edition = null;
        final String editionValue = sourceElement.attributeValue(ATTRIB_EDITION);
        if (editionValue != null) {
          edition = ExaltedEdition.valueOf(editionValue);
        }
        sources.add(new MagicSource(source, page, edition));
      }
    }
    return sources.toArray(new IMagicSource[sources.size()]);
  }
}