package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ALL_ABILITIES;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_COMBOABLE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARM;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_CHARMTYPE;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_COMBO;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_RESTRICTIONS;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_TRAIT_REFERENCE;

import java.util.List;

import net.sf.anathema.character.generic.impl.traits.TraitTypeUtils;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class ComboRulesBuilder implements IComboRulesBuilder {

  private final TraitTypeUtils traitUtils = new TraitTypeUtils();

  public IComboRestrictions buildComboRules(Element rulesElement) throws CharmException {
    Element comboElement = rulesElement.element(TAG_COMBO);
    if (comboElement == null) {
      return new ComboRestrictions();
    }
    Boolean allAbilities = ElementUtilities.getBooleanAttribute(comboElement, ATTRIB_ALL_ABILITIES, false);
    String comboAllowedValue = comboElement.attributeValue(ATTRIB_COMBOABLE);
    Boolean comboAllowed = comboAllowedValue == null ? null : Boolean.valueOf(comboAllowedValue);
    ComboRestrictions comboRules = new ComboRestrictions(allAbilities, comboAllowed);
    Element restrictionElement = comboElement.element(TAG_RESTRICTIONS);
    if (restrictionElement != null) {
      buildRestrictionList(comboRules, restrictionElement);
    }
    return comboRules;
  }

  protected void buildRestrictionList(ComboRestrictions comboRules, Element restrictionElement) throws CharmException {
    List<Element> restrictedCharmList = ElementUtilities.elements(restrictionElement, TAG_CHARM);
    for (Element element : restrictedCharmList) {
      comboRules.addRestrictedCharmId(element.attributeValue(ATTRIB_ID));
    }
    List<Element> restrictedCharmTypeList = ElementUtilities.elements(restrictionElement, TAG_CHARMTYPE);
    for (Element element : restrictedCharmTypeList) {
      String charmType = element.attributeValue(ATTRIB_TYPE);
      comboRules.addRestrictedCharmType(CharmType.valueOf(charmType));
    }
    List<Element> restrictedTraitTypeList = ElementUtilities.elements(restrictionElement, TAG_TRAIT_REFERENCE);
    for (Element element : restrictedTraitTypeList) {
      String traitType = element.attributeValue(ATTRIB_ID);
      comboRules.addRestrictedTraitType(traitUtils.getTraitTypeById(traitType));
    }
  }
}