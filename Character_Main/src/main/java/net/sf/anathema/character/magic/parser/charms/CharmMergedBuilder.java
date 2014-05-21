package net.sf.anathema.character.magic.parser.charms;

import com.google.common.base.Predicate;
import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.character.magic.charm.CharmImpl;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.TAG_CHARM_REFERENCE;
import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.TAG_MERGED;
import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.TAG_MERGES;
import static net.sf.anathema.lib.lang.ArrayUtilities.getFirst;

public class CharmMergedBuilder {

  private final Logger logger = Logger.getLogger(CharmMergedBuilder.class);

  public void buildMerges(Document charmDoc, Charm[] charms) {
    Element charmListElement = charmDoc.getRootElement();
    readMerges(charmListElement, charms);
  }

  private void readMerges(Element charmListElement, Charm[] charms) {
    Element mergesElement = charmListElement.element(TAG_MERGES);
    if (mergesElement == null) {
      return;
    }
    for (Element mergedElement : ElementUtilities.elements(mergesElement, TAG_MERGED)) {
      readMerged(mergedElement, charms);
    }
  }

  private void readMerged(Element mergedElement, Charm[] existingCharms) {
    List<Element> charmReferences = ElementUtilities.elements(mergedElement, TAG_CHARM_REFERENCE);
    Set<Charm> charms = new HashSet<>(charmReferences.size());
    for (Element charmReference : charmReferences) {
      final String charmId = charmReference.attributeValue(ATTRIB_ID);
      Charm charm = getFirst(existingCharms, new Predicate<Charm>() {
        @Override
        public boolean apply(Charm candidate) {
          return candidate.getId().equals(charmId);
        }
      });
      if (charm == null) {
        logger.warn("Merge charm not found " + charmId);
        continue;
      }
      charms.add(charm);
    }
    for (Charm charm : charms) {
      ((CharmImpl) charm).addMerged(charms);
    }
  }
}