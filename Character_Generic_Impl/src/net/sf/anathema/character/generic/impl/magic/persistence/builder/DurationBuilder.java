package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class DurationBuilder {

  public Duration buildDuration(Element durationElement) throws PersistenceException {

    if (durationElement == null) {
      throw new CharmException("Duration not specified for Charm"); //$NON-NLS-1$
    }
    String durationString = ElementUtilities.getRequiredAttrib(durationElement, ICharmXMLConstants.ATTRIB_DURATION);
    return Duration.getDuration(durationString);
  }
}