package net.sf.anathema.character.generic.impl.magic.persistence;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_ALTERNATIVE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_ALTERNATIVES;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM_REFERENCE;

import java.util.List;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAlternative;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.Element;

public class CharmAlternativeBuilder {

  public void buildAlternatives(Document charmDoc, ICharm[] charms) {
    Element charmListElement = charmDoc.getRootElement();
    readAlternatives(charmListElement, charms);
  }

  private void readAlternatives(Element charmListElement, ICharm[] charms) {
    Element alternativesElement = charmListElement.element(TAG_ALTERNATIVES);
    if (alternativesElement == null) {
      return;
    }
    for (Element alternativeElement : ElementUtilities.elements(alternativesElement, TAG_ALTERNATIVE)) {
      readAlternative(alternativeElement, charms);
    }
  }

  private void readAlternative(Element alternativeElement, ICharm[] existingCharms) {
    List<Element> charmReferences = ElementUtilities.elements(alternativeElement, TAG_CHARM_REFERENCE);
    ICharm[] charms = new ICharm[charmReferences.size()];
    for (int index = 0; index < charms.length; index++) {
      final String charmId = charmReferences.get(index).attributeValue(ATTRIB_ID);
      ICharm charm = ArrayUtilities.getFirst(existingCharms, new IPredicate<ICharm>() {
        @Override
        public boolean evaluate(ICharm candidate) {
          return candidate.getId().equals(charmId);
        }
      });
      Ensure.ensureNotNull("Charm not found " + charmId, charm); //$NON-NLS-1$
      charms[index] = charm;
    }
    ICharmAlternative charmAlternative = new CharmAlternative(charms);
    for (ICharm charm : charms) {
      ((Charm) charm).addAlternative(charmAlternative);
    }
  }
}