package net.sf.anathema.character.generic.impl.magic.persistence;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_ALTERNATIVE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_ALTERNATIVES;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM_REFERENCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_GENERIC_CHARM;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.CollectionUtilities;
import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.ComboRulesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericComboRulesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GenericIdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.IdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.AttributeRequirementBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericAttributeRequirementBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.GenericTraitPrerequisitesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.TraitPrerequisitesBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAlternative;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.Element;

public class CharmSetBuilder implements ICharmSetBuilder {

  private final ICharmBuilder builder = new CharmBuilder(
      new IdStringBuilder(),
      new TraitPrerequisitesBuilder(),
      new AttributeRequirementBuilder(),
      new ComboRulesBuilder());
  private final GenericCharmBuilder genericsBuilder = new GenericCharmBuilder(
      new GenericIdStringBuilder(),
      new GenericTraitPrerequisitesBuilder(),
      new GenericAttributeRequirementBuilder(),
      new GenericComboRulesBuilder());

  public ICharm[] buildCharms(Document charmDoc, List<ICharm> existingCharms, IExaltedRuleSet rules)
      throws PersistenceException {
    // TODO : Hier kann man die Reihenfolge richtig drehen
    // Set<Charm> allCharms = new TreeSet<Charm>(new IdentificateComparator());
    Collection<Charm> allCharms = new HashSet<Charm>();
    for (ICharm charm : existingCharms) {
      Charm clone = ((Charm) charm).cloneUnconnected();
      allCharms.add(clone);
    }
    Element charmListElement = charmDoc.getRootElement();
    for (Element charmElementObject : ElementUtilities.elements(charmListElement, TAG_CHARM)) {
      createCharm(allCharms, builder, charmElementObject);
    }
    buildGenericCharms(allCharms, charmListElement);
    readAlternatives(charmListElement, allCharms);
    return allCharms.toArray(new ICharm[allCharms.size()]);
  }

  private void buildGenericCharms(Collection<Charm> allCharms, Element charmListElement) throws PersistenceException {
    final List<Element> elements = ElementUtilities.elements(charmListElement, TAG_GENERIC_CHARM);
    if (elements.isEmpty()) {
      return;
    }
    final ITraitType[] types = createTraitList(allCharms);
    for (ITraitType type : types) {
      genericsBuilder.setType(type);
      for (Element charmElementObject : elements) {
        createCharm(allCharms, genericsBuilder, charmElementObject);
      }
    }
  }

  private ITraitType[] createTraitList(Collection<Charm> allCharms) {
    Set<ITraitType> types = new HashSet<ITraitType>();
    for (Charm charm : allCharms) {
      final IGenericTrait[] prerequisites = charm.getPrerequisites();
      if (prerequisites.length > 0) {
        types.add(prerequisites[0].getType());
      }
    }
    return types.toArray(new ITraitType[types.size()]);
  }

  private void createCharm(Collection<Charm> allCharms, ICharmBuilder currentbuilder, Element charmElement)
      throws PersistenceException {
    Charm newCharm = currentbuilder.buildCharm(charmElement);
    if (allCharms.contains(newCharm)) {
      allCharms.remove(newCharm);
    }
    allCharms.add(newCharm);
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