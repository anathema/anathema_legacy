package net.sf.anathema.character.generic.impl.magic.persistence;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.util.IPredicate;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM_REFERENCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_MERGED;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_MERGES;

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
      ICharm charm = net.sf.anathema.lib.lang.ArrayUtilities.getFirst(existingCharms, new IPredicate<ICharm>() {
        @Override
        public boolean evaluate(ICharm candidate) {
          return candidate.getId().equals(charmId);
        }
      });
      Preconditions.checkNotNull(charm, "Charm not found " + charmId); //$NON-NLS-1$
      charms.add(charm);
    }
    for (ICharm charm : charms) {
      ((Charm) charm).addMerged(charms);
    }
  }
}