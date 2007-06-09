package net.sf.anathema.charmentry.persistence;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.magic.ICharmData;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class CharmWriter {

  public void writeCharm(ICharmData charm, Element charmListElement) throws PersistenceException {
    Ensure.ensureArgumentNotNull(charm);
    Ensure.ensureArgumentNotNull(charmListElement);
    Element charmElement = charmListElement.addElement(TAG_CHARM);
    new HeadDataWriter().write(charm, charmElement);
    new PrerequisiteWriter().write(charm, charmElement);
    new CostWriter().write(charm, charmElement);
    new DurationWriter().write(charm, charmElement);
    new CharmTypeWriter().write(charm, charmElement);
    new KeywordWriter().write(charm, charmElement);
    new SourceWriter().write(charm, charmElement);
  }
}