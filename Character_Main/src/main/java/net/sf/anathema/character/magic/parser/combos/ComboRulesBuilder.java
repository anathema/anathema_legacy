package net.sf.anathema.character.magic.parser.combos;

import net.sf.anathema.character.magic.charm.CharmException;
import net.sf.anathema.character.magic.charm.type.CharmType;
import net.sf.anathema.character.magic.charm.combos.ComboRestrictions;
import net.sf.anathema.character.magic.charm.combos.IComboRestrictions;
import net.sf.anathema.hero.traits.model.TraitTypeUtils;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.util.List;

import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.ATTRIB_ALL_ABILITIES;
import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.ATTRIB_SELECT_ABILITIES;
import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.ATTRIB_TYPE;
import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.TAG_CHARM;
import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.TAG_CHARMTYPE;
import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.TAG_COMBO;
import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.TAG_RESTRICTIONS;
import static net.sf.anathema.character.magic.charm.ICharmXMLConstants.TAG_TRAIT_REFERENCE;

public class ComboRulesBuilder implements IComboRulesBuilder {

  private final TraitTypeUtils traitUtils = new TraitTypeUtils();

  @Override
  public IComboRestrictions buildComboRules(Element rulesElement) throws CharmException {
    Element comboElement = rulesElement.element(TAG_COMBO);
    if (comboElement == null) {
      return new ComboRestrictions();
    }
    Boolean allAbilities = ElementUtilities.getBooleanAttribute(comboElement, ATTRIB_ALL_ABILITIES, false);
    String selectAbilities = comboElement.attributeValue(ATTRIB_SELECT_ABILITIES, "");
    ComboRestrictions comboRules = new ComboRestrictions(allAbilities, selectAbilities);
    Element restrictionElement = comboElement.element(TAG_RESTRICTIONS);
    if (restrictionElement != null) {
      buildRestrictionList(comboRules, restrictionElement);
    }
    return comboRules;
  }

  protected void buildRestrictionList(ComboRestrictions comboRules, Element restrictionElement) throws CharmException {
    List<Element> restrictedCharmList = ElementUtilities.elements(restrictionElement, TAG_CHARM);
    for (Element element : restrictedCharmList) {
      String charmId = element.attributeValue(ATTRIB_ID);
      comboRules.addRestrictedCharmId(charmId);
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