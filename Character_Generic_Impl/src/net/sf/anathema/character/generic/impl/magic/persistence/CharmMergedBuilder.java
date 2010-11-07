package net.sf.anathema.character.generic.impl.magic.persistence;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_MERGED;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_MERGES;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM_REFERENCE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.Element;

public class CharmMergedBuilder {

  public void buildMerges(Document charmDoc, ICharm[] charms) {
    Element charmListElement = charmDoc.getRootElement();
    readMerges(charmListElement, charms);
  }

  private void readMerges(Element charmListElement, ICharm[] charms) {
    Element mergesElement = charmListElement.element(TAG_MERGES);
    if (mergesElement == null) {
      return;
    }
    for (Element mergedElement : ElementUtilities.elements(mergesElement, TAG_MERGED)) {
      readMerged(mergedElement, charms);
    }
  }

  private void readMerged(Element mergedElement, ICharm[] existingCharms) {
    List<Element> charmReferences = ElementUtilities.elements(mergedElement, TAG_CHARM_REFERENCE);
    Set<ICharm> charms = new HashSet<ICharm>(charmReferences.size());
    for (Element charmReference : charmReferences) {
      final String charmId = charmReference.attributeValue(ATTRIB_ID);
      ICharm charm = ArrayUtilities.getFirst(existingCharms, new IPredicate<ICharm>() {
        @Override
        public boolean evaluate(ICharm candidate) {
          return candidate.getId().equals(charmId);
        }
      });
      Ensure.ensureNotNull("Charm not found " + charmId, charm); //$NON-NLS-1$
      charms.add(charm);
    }
    for (ICharm charm : charms) {
      ((Charm) charm).addMerged(charms);
    }
  }
}