package net.sf.anathema.character.generic.impl.magic.persistence;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_ALTERNATIVE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_ALTERNATIVES;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM_REFERENCE;

import java.util.Collection;
import java.util.List;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.CollectionUtilities;
import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.ComboRulesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.IdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.AttributeRequirementBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.TraitPrerequisitesBuilder;
import net.sf.anathema.character.generic.magic.charms.ICharmAlternative;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharmSetBuilder extends AbstractCharmSetBuilder implements ICharmSetBuilder {

  private final ICharmBuilder builder = new CharmBuilder(
      new IdStringBuilder(),
      new TraitPrerequisitesBuilder(),
      new AttributeRequirementBuilder(),
      new ComboRulesBuilder());

  @Override
  protected void buildCharms(Collection<Charm> allCharms, Element charmListElement) throws PersistenceException {
    for (Element charmElementObject : ElementUtilities.elements(charmListElement, TAG_CHARM)) {
      createCharm(allCharms, builder, charmElementObject);
    }
    readAlternatives(charmListElement, allCharms);
  }

  private void readAlternatives(Element charmListElement, Collection<Charm> allCharms) {
    Element alternativesElement = charmListElement.element(TAG_ALTERNATIVES);
    if (alternativesElement == null) {
      return;
    }
    for (Element alternativeElement : ElementUtilities.elements(alternativesElement, TAG_ALTERNATIVE)) {
      readAlternative(alternativeElement, allCharms);
    }
  }

  private void readAlternative(Element alternativeElement, Collection<Charm> allCharms) {
    List<Element> charmReferences = ElementUtilities.elements(alternativeElement, TAG_CHARM_REFERENCE);
    Charm[] charms = new Charm[charmReferences.size()];
    for (int index = 0; index < charms.length; index++) {
      final String charmId = charmReferences.get(index).attributeValue(ATTRIB_ID);
      Charm charm = CollectionUtilities.find(allCharms, new IPredicate<Charm>() {
        @Override
        public boolean evaluate(Charm candidate) {
          return candidate.getId().equals(charmId);
        }
      });
      Ensure.ensureNotNull("Charm not found " + charmId, charm); //$NON-NLS-1$
      charms[index] = charm;
    }
    ICharmAlternative charmAlternative = new CharmAlternative(charms);
    for (Charm charm : charms) {
      charm.addAlternative(charmAlternative);
    }
  }
}