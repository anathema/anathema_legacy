package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.ATTRIB_ID;
import static net.sf.anathema.character.generic.impl.magic.ICharmXMLConstants.TAG_GENERIC_CHARM_REFERENCE;

import java.util.List;

import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class GenericComboRulesBuilder extends ComboRulesBuilder implements IComboRulesBuilder {

  private ITraitType type;

  @Override
  protected void buildRestrictionList(ComboRestrictions comboRules, Element restrictionElement) {
    super.buildRestrictionList(comboRules, restrictionElement);
    List<Element> restrictedCharmList = ElementUtilities.elements(restrictionElement, TAG_GENERIC_CHARM_REFERENCE);
    for (Element element : restrictedCharmList) {
      String charmId = element.attributeValue(ATTRIB_ID);
      comboRules.addRestrictedCharmId(charmId + "." + type.getId());
    }
  }

  public void setType(ITraitType type) {
    this.type = type;
  }
}