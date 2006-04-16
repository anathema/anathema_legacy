package net.sf.anathema.character.generic.impl.magic.persistence;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ALL_ABILITIES;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ATTRIBUTE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_COMBOABLE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_EXALT;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_GROUP;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_LEVEL;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_NAME;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_PAGE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_SOURCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_VISUALIZE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_ALTERNATIVE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_ALTERNATIVES;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_ATTRIBUTE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARMTYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM_REFERENCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_COMBO;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_COST;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_DURATION;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_MARTIAL_ARTS_LEVEL;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_PERMANENT;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_PREREQUISITE_LIST;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_RESTRICTIONS;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_RULESET;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_SOURCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_TEMPORARY;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_TRAIT_REFERENCE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.VALUE_POWERCOMBAT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants;
import net.sf.anathema.character.generic.impl.magic.MagicSource;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.CharmPrerequisiteListBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.CostListBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.DurationBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.ICostListBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.prerequisite.CharmPrerequisiteList;
import net.sf.anathema.character.generic.impl.traits.TraitTypeUtils;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.magic.charms.ICharmAlternative;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.general.ICostList;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.character.generic.magic.general.IPermanentCostList;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.Element;

public class CharmBuilder {

  private final ICostListBuilder costListBuilder = new CostListBuilder();
  private final TraitTypeUtils traitUtils = new TraitTypeUtils();
  private final DurationBuilder durationBuilder = new DurationBuilder();

  private void ensureNotNull(Object object, String message) throws CharmException {
    if (object == null) {
      throw new CharmException(message);
    }
  }

  public Charm buildCharm(Element charmElement, boolean powerCombat) throws PersistenceException {
    Element rulesElement = charmElement;
    Element fallBackElement = null;
    if (powerCombat) {
      Element potentialRulesElement = charmElement.element(TAG_RULESET);
      if (potentialRulesElement != null && VALUE_POWERCOMBAT.equals(potentialRulesElement.attributeValue(ATTRIB_NAME))) {
        rulesElement = potentialRulesElement;
        fallBackElement = charmElement;
      }
    }
    String id = charmElement.attributeValue(ATTRIB_ID);
    ensureNotNull(id, "Cannot process Charms without id."); //$NON-NLS-1$
    if (id == "") { //$NON-NLS-1$
      throw new CharmException("Cannot process Charms without id."); //$NON-NLS-1$
    }
    String typeAttribute = charmElement.attributeValue(ATTRIB_EXALT);
    CharacterType characterType;
    try {
      characterType = CharacterType.getById(typeAttribute);
    }
    catch (IllegalArgumentException e) {
      throw new CharmException("No chararacter type given for Charm: " + id, e); //$NON-NLS-1$
    }
    String group = getCharmGroupId(charmElement, id);

    Element costElement = getElementFromRules(rulesElement, fallBackElement, TAG_COST);
    ensureNotNull(costElement, "No cost specified for Charm: " + id); //$NON-NLS-1$
    ICostList temporaryCost = costListBuilder.buildTemporaryCostList(costElement.element(TAG_TEMPORARY));
    IPermanentCostList permanentCost = costListBuilder.buildPermanentCostList(costElement.element(TAG_PERMANENT));
    IComboRestrictions comboRules = getComboRules(rulesElement, fallBackElement, id);
    Duration duration = durationBuilder.buildDuration(getElementFromRules(rulesElement, fallBackElement, TAG_DURATION));
    CharmType charmType = getCharmType(rulesElement, fallBackElement, id);

    List<IMagicSource> sources = new ArrayList<IMagicSource>();
    List<Element> sourceElements = ElementUtilities.elements(rulesElement, TAG_SOURCE);
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

    Element prerequisiteListElement = getPrerequisiteListElement(charmElement);
    CharmPrerequisiteList prerequisiteList = new CharmPrerequisiteListBuilder().buildPrerequisiteList(
        prerequisiteListElement,
        id);
    IGenericTrait[] prerequisites = prerequisiteList.getPrerequisites();
    Charm charm = new Charm(
        characterType,
        id,
        group,
        prerequisiteList,
        temporaryCost,
        permanentCost,
        comboRules,
        duration,
        charmType,
        sources.toArray(new IMagicSource[0]));
    String[] primaryTrait = prerequisites.length == 0 ? new String[0] : new String[] { prerequisites[0].getType()
        .getId() };
    for (ICharmAttribute attribute : getCharmAttributes(rulesElement, fallBackElement, primaryTrait)) {
      charm.addCharmAttribute(attribute);
    }
    loadSpecialLearning(charmElement, charm);
    return charm;
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

  private String getCharmGroupId(Element charmElement, String id) throws CharmException {
    String group = charmElement.attributeValue(ATTRIB_GROUP);
    ensureNotNull(group, "Cannot process Charm without group id:" + id); //$NON-NLS-1$
    return group;
  }

  private Element getPrerequisiteListElement(Element charmElement) throws CharmException {
    Element prerequisiteListElement = charmElement.element(TAG_PREREQUISITE_LIST);
    ensureNotNull(prerequisiteListElement, "Required element 'prerequisite' is missing in Charm."); //$NON-NLS-1$    
    return prerequisiteListElement;
  }

  private ICharmAttribute[] getCharmAttributes(
      Element rulesElement,
      Element fallBackElement,
      String[] additionalAttributes) {
    List<ICharmAttribute> attributes = new ArrayList<ICharmAttribute>();
    for (Element attributeElement : getElementsFromRules(rulesElement, fallBackElement, TAG_ATTRIBUTE)) {
      String attributeId = attributeElement.attributeValue(ATTRIB_ATTRIBUTE);
      boolean visualizeAttribute = ElementUtilities.getBooleanAttribute(attributeElement, ATTRIB_VISUALIZE, false);
      attributes.add(new CharmAttribute(attributeId, visualizeAttribute));
    }
    for (String attributeString : additionalAttributes) {
      attributes.add(new CharmAttribute(attributeString, false));
    }
    return attributes.toArray(new ICharmAttribute[attributes.size()]);
  }

  private CharmType getCharmType(Element rulesElement, Element fallBackElement, String id) throws CharmException {
    CharmType charmType;
    Element typeElement = getElementFromRules(rulesElement, fallBackElement, TAG_CHARMTYPE);
    if (typeElement == null) {
      throw new CharmException("Type required for charm: " + id); //$NON-NLS-1$
    }
    try {
      charmType = CharmType.valueOf(typeElement.attributeValue(ATTRIB_TYPE));
    }
    catch (IllegalArgumentException e) {
      throw new CharmException("Bad type in charm: " + id); //$NON-NLS-1$
    }
    catch (NullPointerException e) {
      throw new CharmException("Bad type in charm: " + id); //$NON-NLS-1$
    }
    return charmType;
  }

  private IComboRestrictions getComboRules(Element rulesElement, Element fallBackElement, String id)
      throws CharmException {
    String typeAttribute;
    Element comboElement = getElementFromRules(rulesElement, fallBackElement, TAG_COMBO);
    if (comboElement == null) {
      return new ComboRestrictions();
    }
    Boolean allAbilities = Boolean.valueOf(comboElement.attributeValue(ATTRIB_ALL_ABILITIES));
    String comboAllowedValue = comboElement.attributeValue(ATTRIB_COMBOABLE);
    Boolean comboAllowed = comboAllowedValue == null ? new Boolean(true) : Boolean.valueOf(comboAllowedValue);
    Element restrictionElement = comboElement.element(TAG_RESTRICTIONS);
    ComboRestrictions comboRules = new ComboRestrictions(allAbilities, comboAllowed);
    if (restrictionElement != null) {
      List<Element> restrictedCharmList = ElementUtilities.elements(restrictionElement, TAG_CHARM);
      for (int i = 0; i < restrictedCharmList.size(); i++) {
        comboRules.addRestrictedCharmId(restrictedCharmList.get(i).attributeValue(ATTRIB_ID));
      }
      List<Element> restrictedCharmTypeList = ElementUtilities.elements(restrictionElement, TAG_CHARMTYPE);
      for (int i = 0; i < restrictedCharmTypeList.size(); i++) {
        try {
          typeAttribute = restrictedCharmTypeList.get(i).attributeValue(ATTRIB_TYPE);
          comboRules.addRestrictedCharmType(CharmType.valueOf(typeAttribute));
        }
        catch (IllegalArgumentException e) {
          throw new CharmException("Bad charm type in Combo restrictions for Charm: " + id, e); //$NON-NLS-1$
        }
      }
      List<Element> restrictedTraitTypeList = ElementUtilities.elements(restrictionElement, TAG_TRAIT_REFERENCE);
      for (int i = 0; i < restrictedTraitTypeList.size(); i++) {
        try {
          typeAttribute = restrictedTraitTypeList.get(i).attributeValue(ATTRIB_ID);
          comboRules.addRestrictedTraitType(traitUtils.getTraitTypeById(typeAttribute));
        }
        catch (IllegalArgumentException e) {
          throw new CharmException("Bad trait type in Combo restrictions for Charm: " + id); //$NON-NLS-1$
        }
      }
    }
    return comboRules;
  }

  private Element getElementFromRules(Element rulesElement, Element fallBackElement, String elementName) {
    Element element = rulesElement.element(elementName);
    if (element == null && fallBackElement != null) {
      element = fallBackElement.element(elementName);
    }
    return element;
  }

  private Element[] getElementsFromRules(Element rulesElement, Element fallBackElement, String elementName) {
    List<Element> elements = ElementUtilities.elements(rulesElement, elementName);
    if (elements.isEmpty() && fallBackElement != null) {
      elements = ElementUtilities.elements(fallBackElement, elementName);
    }
    return elements.toArray(new Element[elements.size()]);
  }

  public ICharm[] buildCoreRulesCharms(Document charmDoc) throws PersistenceException {
    return buildCharms(charmDoc, false);
  }

  public ICharm[] buildPowerCombatCharms(Document charmDoc) throws PersistenceException {
    return buildCharms(charmDoc, true);
  }

  public ICharm[] buildCharms(Document charmDoc, boolean powerCombat) throws PersistenceException {
    Set<Charm> allCharms = new HashSet<Charm>();
    Map<String, Charm> charmsById = new HashMap<String, Charm>();
    Element charmListElement = charmDoc.getRootElement();
    for (Object charmElementObject : charmListElement.elements(TAG_CHARM)) {
      Element charmElement = (Element) charmElementObject;
      Charm newCharm = buildCharm(charmElement, powerCombat);
      allCharms.add(newCharm);
      if (!charmsById.containsKey(newCharm.getId())) {
        charmsById.put(newCharm.getId(), newCharm);
      }
    }
    extractParents(charmsById, allCharms);
    readAlternatives(charmsById, charmListElement);
    return allCharms.toArray(new ICharm[allCharms.size()]);
  }

  private void extractParents(Map<String, ? extends Charm> charmsById, Set< ? extends Charm> allCharms) {
    for (Charm charm : allCharms) {
      charm.extractParentCharms(charmsById);
    }
  }

  private void readAlternatives(Map<String, Charm> charmsById, Element charmListElement) {
    Element alternativesElement = charmListElement.element(TAG_ALTERNATIVES);
    if (alternativesElement == null) {
      return;
    }
    for (Element alternativeElement : ElementUtilities.elements(alternativesElement, TAG_ALTERNATIVE)) {
      readAlternative(alternativeElement, charmsById);
    }
  }

  private void readAlternative(Element alternativeElement, Map<String, Charm> charmsById) {
    List<Element> charmReferences = ElementUtilities.elements(alternativeElement, TAG_CHARM_REFERENCE);
    Charm[] charms = new Charm[charmReferences.size()];
    for (int index = 0; index < charms.length; index++) {
      String charmId = charmReferences.get(index).attributeValue(ATTRIB_ID);
      Charm charm = charmsById.get(charmId);
      Ensure.ensureNotNull("Charm not found " + charmId, charm); //$NON-NLS-1$
      charms[index] = charm;
    }
    ICharmAlternative charmAlternative = new CharmAlternative(charms);
    for (Charm charm : charms) {
      charm.addAlternative(charmAlternative);
    }
  }

  public ICharm[] buildMartialArtsCharms(Document charmDocument, boolean powerCombat) throws PersistenceException {
    Set<Charm> allMartialArtsCharms = new HashSet<Charm>();
    Map<String, Charm> charmsById = new HashMap<String, Charm>();
    Element charmListElement = charmDocument.getRootElement();
    for (Object charmElementObject : charmListElement.elements(TAG_CHARM)) {
      Element charmElement = (Element) charmElementObject;
      Charm charm = buildCharm(charmElement, powerCombat);
      String martialArtsLevel = charmElement.element(TAG_MARTIAL_ARTS_LEVEL).attributeValue(ATTRIB_LEVEL);
      MartialArtsLevel level = MartialArtsLevel.valueOf(martialArtsLevel);
      charm.addCharmAttribute(new CharmAttribute(level.getId(), false));
      allMartialArtsCharms.add(charm);
      charmsById.put(charm.getId(), charm);
    }
    extractParents(charmsById, allMartialArtsCharms);
    return allMartialArtsCharms.toArray(new ICharm[0]);
  }
}