package net.sf.anathema.character.main.magic.parser.charms.prerequisite;

import net.sf.anathema.character.main.magic.charm.CharmException;
import net.sf.anathema.character.main.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.DirectGroupCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.SimpleCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.parser.charms.CharmPrerequisiteList;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.sf.anathema.character.main.magic.charm.ICharmXMLConstants.ATTRIB_THRESHOLD;
import static net.sf.anathema.character.main.magic.charm.ICharmXMLConstants.ATTRIB_VALUE;
import static net.sf.anathema.character.main.magic.charm.ICharmXMLConstants.TAG_ESSENCE;
import static net.sf.anathema.character.main.magic.charm.ICharmXMLConstants.TAG_SELECTIVE_CHARM_GROUP;
import static net.sf.anathema.character.main.traits.types.OtherTraitType.Essence;
import static net.sf.anathema.lib.xml.ElementUtilities.elements;

public class PrerequisiteListBuilder {

  private final ITraitPrerequisitesBuilder traitBuilder;
  private final IAttributePrerequisiteBuilder attributeBuilder;
  private final ICharmPrerequisiteBuilder charmBuilder;

  public PrerequisiteListBuilder(ITraitPrerequisitesBuilder traitBuilder,
                                 IAttributePrerequisiteBuilder attributeBuilder,
                                 ICharmPrerequisiteBuilder charmBuilder) {
    this.traitBuilder = traitBuilder;
    this.attributeBuilder = attributeBuilder;
    this.charmBuilder = charmBuilder;
  }

  public CharmPrerequisiteList buildPrerequisiteList(Element prerequisiteListElement) throws PersistenceException {
    ValuedTraitType[] traitPrerequisites = traitBuilder.buildTraitPrerequisites(prerequisiteListElement);
    ValuedTraitType essencePrerequisite = buildEssencePrerequisite(prerequisiteListElement);
    CharmLearnPrerequisite[] learnPrerequisites = buildCharmPrerequisites(prerequisiteListElement);
    return new CharmPrerequisiteList(traitPrerequisites, essencePrerequisite, learnPrerequisites);
  }

  private CharmLearnPrerequisite[] buildCharmPrerequisites(Element prerequisiteListElement) {
    List<CharmLearnPrerequisite> prerequisites = new ArrayList<>();
    prerequisites.addAll(buildSimpleCharmPrerequisites(prerequisiteListElement));
    prerequisites.addAll(buildSelectiveCharmGroups(prerequisiteListElement));
    prerequisites.addAll(Arrays.asList(attributeBuilder.getCharmAttributePrerequisites(prerequisiteListElement)));
    return prerequisites.toArray(new CharmLearnPrerequisite[prerequisites.size()]);
  }

  private List<CharmLearnPrerequisite> buildSimpleCharmPrerequisites(Element prerequisiteListElement) {
    String[] prerequisiteCharmIDs = charmBuilder.buildCharmPrerequisites(prerequisiteListElement);
    List<CharmLearnPrerequisite> prerequisites = new ArrayList<>();
    for (String id : prerequisiteCharmIDs) {
      prerequisites.add(new SimpleCharmLearnPrerequisite(id));
    }
    return prerequisites;
  }

  private ValuedTraitType buildEssencePrerequisite(Element prerequisiteListElement) throws CharmException {
    Element essenceElement = prerequisiteListElement.element(TAG_ESSENCE);
    if (essenceElement == null) {
      throw new CharmException("Cannot process Charm without essence prerequisite.");
    }
    int minValue;
    try {
      minValue = Integer.parseInt(essenceElement.attributeValue(ATTRIB_VALUE));
    } catch (NumberFormatException e) {
      throw new CharmException("Bad value on essence prerequisite.");
    }
    return new net.sf.anathema.character.main.traits.types.ValuedTraitType(Essence, minValue);
  }

  private List<CharmLearnPrerequisite> buildSelectiveCharmGroups(
          Element prerequisiteListElement) throws PersistenceException {
    List<Element> selectiveCharmGroupElements = elements(prerequisiteListElement, TAG_SELECTIVE_CHARM_GROUP);
    List<CharmLearnPrerequisite> prerequisites = new ArrayList<>();
    for (Element groupElement : selectiveCharmGroupElements) {
      String[] groupCharmIds = charmBuilder.buildCharmPrerequisites(groupElement);
      int threshold = ElementUtilities.getRequiredIntAttrib(groupElement, ATTRIB_THRESHOLD);
      prerequisites.add(new DirectGroupCharmLearnPrerequisite(groupCharmIds, threshold));
    }
    return prerequisites;
  }
}