package net.sf.anathema.character.generic.impl.magic.persistence;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_ALTERNATIVE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_ALTERNATIVES;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM_REFERENCE;
import static net.sf.anathema.lib.lang.ArrayUtilities.getFirst;

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
    Set<ICharm> charms = new HashSet<ICharm>(charmReferences.size());
    for (Element charmReference : charmReferences) {
      final String charmId = charmReference.attributeValue(ATTRIB_ID);
      ICharm charm = getFirst(existingCharms, new Predicate<ICharm>() {
        @Override
        public boolean apply(ICharm candidate) {
          return candidate.getId().equals(charmId);
        }
      });
      Preconditions.checkNotNull(charm, "Charm not found " + charmId); //$NON-NLS-1$
      charms.add(charm);
    }
    for (ICharm charm : charms) {
      ((Charm) charm).addAlternative(charms);
    }
  }
}