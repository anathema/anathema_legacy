package net.sf.anathema.character.generic.impl.magic.persistence;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ATTRIBUTE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_EXALT;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_PAGE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_SOURCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_VISUALIZE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_ATTRIBUTE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_COST;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_DURATION;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_PERMANENT;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_PREREQUISITE_LIST;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_SOURCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_TEMPORARY;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants;
import net.sf.anathema.character.generic.impl.magic.MagicSource;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.CharmTypeBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.ComboRulesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.CostListBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.DurationBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.GroupStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.ICostListBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.IIdStringBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.ITraitPrerequisitesBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.prerequisite.PrerequisiteListBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.CharmPrerequisiteList;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.character.generic.magic.general.IPermanentCostList;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class CharmBuilder implements ICharmBuilder {

  private final CharmTypeBuilder charmTypeBuilder = new CharmTypeBuilder();
  private final ICostListBuilder costListBuilder = new CostListBuilder();
  private final DurationBuilder durationBuilder = new DurationBuilder();
  private final GroupStringBuilder groupBuilder = new GroupStringBuilder();
  private final ComboRulesBuilder comboBuilder = new ComboRulesBuilder();
  private final IIdStringBuilder idBuilder;
  private final ITraitPrerequisitesBuilder traitsBuilder;

  public CharmBuilder(IIdStringBuilder idBuilder, ITraitPrerequisitesBuilder traitsBuilder) {
    this.idBuilder = idBuilder;
    this.traitsBuilder = traitsBuilder;
  }

  public Charm buildCharm(Element charmElement) throws PersistenceException {
    String id = idBuilder.build(charmElement);
    CharacterType characterType = getCharacterType(charmElement, id);
    Element costElement = charmElement.element(TAG_COST);
    Ensure.ensureArgumentNotNull("No cost specified for Charm: " + id, costElement); //$NON-NLS-1$
    ICostList temporaryCost = costListBuilder.buildTemporaryCostList(costElement.element(TAG_TEMPORARY));
    IPermanentCostList permanentCost = costListBuilder.buildPermanentCostList(costElement.element(TAG_PERMANENT));
    IComboRestrictions comboRules;
    try {
      comboRules = comboBuilder.buildComboRules(charmElement);
    }
    catch (IllegalArgumentException e) {
      throw new CharmException("Error in Charm " + id, e); //$NON-NLS-1$
    }
    Duration duration = durationBuilder.buildDuration(charmElement.element(TAG_DURATION));
    ICharmTypeModel charmTypeModel = charmTypeBuilder.build(charmElement);
    List<IMagicSource> sources = buildSourceList(charmElement);
    CharmPrerequisiteList prerequisiteList = getPrerequisites(charmElement, id);
    IGenericTrait[] prerequisites = prerequisiteList.getPrerequisites();
    final IGenericTrait primaryPrerequisite = (prerequisites.length != 0) ? prerequisites[0] : null;
    String group = groupBuilder.build(charmElement, primaryPrerequisite);
    Charm charm = new Charm(
        characterType,
        id,
        group,
        prerequisiteList,
        temporaryCost,
        permanentCost,
        comboRules,
        duration,
        charmTypeModel,
        sources.toArray(new IMagicSource[0]));
    for (ICharmAttribute attribute : getCharmAttributes(charmElement, primaryPrerequisite)) {
      charm.addCharmAttribute(attribute);
    }
    loadSpecialLearning(charmElement, charm);
    return charm;
  }

  private CharmPrerequisiteList getPrerequisites(Element charmElement, String id) throws CharmException {
    CharmPrerequisiteList prerequisiteList;
    try {
      Element prerequisiteListElement = ElementUtilities.getRequiredElement(charmElement, TAG_PREREQUISITE_LIST);
      prerequisiteList = new PrerequisiteListBuilder(traitsBuilder).buildPrerequisiteList(prerequisiteListElement);
    }
    catch (PersistenceException e) {
      throw new CharmException("Error in Charm " + id, e); //$NON-NLS-1$
    }
    return prerequisiteList;
  }

  private CharacterType getCharacterType(Element charmElement, String id) throws CharmException {
    String typeAttribute = charmElement.attributeValue(ATTRIB_EXALT);
    CharacterType characterType;
    try {
      characterType = CharacterType.getById(typeAttribute);
    }
    catch (IllegalArgumentException e) {
      throw new CharmException("No chararacter type given for Charm: " + id, e); //$NON-NLS-1$
    }
    return characterType;
  }

  private List<IMagicSource> buildSourceList(Element charmElement) {
    List<IMagicSource> sources = new ArrayList<IMagicSource>();
    List<Element> sourceElements = ElementUtilities.elements(charmElement, TAG_SOURCE);
    if (sourceElements.isEmpty()) {
      sources.add(MagicSource.CUSTOM_SOURCE);
    }
    else {
      for (Element sourceElement : sourceElements) {
        String source = sourceElement.attributeValue(ATTRIB_SOURCE);
        String page = String.valueOf(sourceElement.attributeValue(ATTRIB_PAGE));
        sources.add(new MagicSource(source, page));
      }
    }
    return sources;
  }

  private ICharmAttribute[] getCharmAttributes(Element rulesElement, IGenericTrait primaryPrerequisite) {
    List<ICharmAttribute> attributes = new ArrayList<ICharmAttribute>();
    for (Element attributeElement : getElementsFromRules(rulesElement, TAG_ATTRIBUTE)) {
      String attributeId = attributeElement.attributeValue(ATTRIB_ATTRIBUTE);
      boolean visualizeAttribute = ElementUtilities.getBooleanAttribute(attributeElement, ATTRIB_VISUALIZE, false);
      attributes.add(new CharmAttribute(attributeId, visualizeAttribute));
    }
    if (primaryPrerequisite != null) {
      attributes.add(new CharmAttribute(primaryPrerequisite.getType().getId(), false));
    }
    return attributes.toArray(new ICharmAttribute[attributes.size()]);
  }

  private Element[] getElementsFromRules(Element rulesElement, String elementName) {
    List<Element> elements = ElementUtilities.elements(rulesElement, elementName);
    return elements.toArray(new Element[elements.size()]);
  }

  private void loadSpecialLearning(Element charmElement, Charm charm) {
    Element learningElement = charmElement.element(ICharmXMLConstants.TAG_LEARNING);
    if (learningElement == null) {
      return;
    }
    for (Element favoredElement : ElementUtilities.elements(learningElement, ICharmXMLConstants.ATTRB_FAVORED)) {
      String casteId = favoredElement.attributeValue(ICharmXMLConstants.TAG_CASTE);
      charm.addFavoredCasteId(casteId);
    }
  }
}