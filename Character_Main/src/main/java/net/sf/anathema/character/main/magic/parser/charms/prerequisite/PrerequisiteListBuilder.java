package net.sf.anathema.character.main.magic.parser.charms.prerequisite;

import static net.sf.anathema.character.main.magic.charm.ICharmXMLConstants.ATTRIB_THRESHOLD;
import static net.sf.anathema.character.main.magic.charm.ICharmXMLConstants.ATTRIB_VALUE;
import static net.sf.anathema.character.main.magic.charm.ICharmXMLConstants.TAG_ESSENCE;
import static net.sf.anathema.character.main.magic.charm.ICharmXMLConstants.TAG_SELECTIVE_CHARM_GROUP;
import static net.sf.anathema.character.main.traits.types.OtherTraitType.Essence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.main.magic.charm.CharmException;
import net.sf.anathema.character.main.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.DirectGroupCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.IndirectGroupCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.charm.prerequisite.impl.SimpleCharmLearnPrerequisite;
import net.sf.anathema.character.main.magic.parser.charms.CharmPrerequisiteList;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class PrerequisiteListBuilder {

  private static final String ATTRIB_LABEL = "label";

  private final ITraitPrerequisitesBuilder traitBuilder;
  private final IAttributePrerequisiteBuilder attributeBuilder;
  private final ICharmPrerequisiteBuilder charmBuilder;

  public PrerequisiteListBuilder(ITraitPrerequisitesBuilder traitBuilder, IAttributePrerequisiteBuilder attributeBuilder,
                                 ICharmPrerequisiteBuilder charmBuilder) {
    this.traitBuilder = traitBuilder;
    this.attributeBuilder = attributeBuilder;
    this.charmBuilder = charmBuilder;
  }

  public CharmPrerequisiteList buildPrerequisiteList(Element prerequisiteListElement) throws PersistenceException {
    ValuedTraitType[] allPrerequisites = traitBuilder.buildTraitPrerequisites(prerequisiteListElement);
    ValuedTraitType essence = buildEssencePrerequisite(prerequisiteListElement);
    String[] prerequisiteCharmIDs = charmBuilder.buildCharmPrerequisites(prerequisiteListElement);
    List<CharmLearnPrerequisite> prerequisites = new ArrayList<>();
    prerequisites.addAll(Arrays.asList(buildSimpleCharmPrerequisites(prerequisiteCharmIDs)));
    prerequisites.addAll(Arrays.asList(buildSelectiveCharmGroups(prerequisiteListElement)));
    prerequisites.addAll(Arrays.asList(attributeBuilder.getCharmAttributePrerequisites(prerequisiteListElement)));
    CharmLearnPrerequisite[] learnPrerequisites = prerequisites.toArray(new CharmLearnPrerequisite[prerequisites.size()]);
    return new CharmPrerequisiteList(allPrerequisites, essence, prerequisiteCharmIDs, learnPrerequisites);
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
  
  private CharmLearnPrerequisite[] buildSimpleCharmPrerequisites(String[] ids) {
	  List<CharmLearnPrerequisite> prerequisites = new ArrayList<>();
	  for (String id : ids) {
		  prerequisites.add(new SimpleCharmLearnPrerequisite(id));
	  }
	  return prerequisites.toArray(new CharmLearnPrerequisite[prerequisites.size()]);
  }

  private CharmLearnPrerequisite[] buildSelectiveCharmGroups(Element prerequisiteListElement) throws PersistenceException {
    List<Element> selectiveCharmGroupElements = ElementUtilities.elements(prerequisiteListElement, TAG_SELECTIVE_CHARM_GROUP);
    CharmLearnPrerequisite[] charmGroups = new CharmLearnPrerequisite[selectiveCharmGroupElements.size()];
    for (int index = 0; index < selectiveCharmGroupElements.size(); index++) {
      Element groupElement = selectiveCharmGroupElements.get(index);
      String[] groupCharmIds = charmBuilder.buildCharmPrerequisites(groupElement);
      int threshold = ElementUtilities.getRequiredIntAttrib(groupElement, ATTRIB_THRESHOLD);
      String label = groupElement.attributeValue(ATTRIB_LABEL);
      charmGroups[index] = label == null ?
    		  new DirectGroupCharmLearnPrerequisite(groupCharmIds, threshold) : 
    		  new IndirectGroupCharmLearnPrerequisite(label, groupCharmIds, threshold);
    }
    return charmGroups;
  }
}