package net.sf.anathema.character.generic.impl.magic.persistence.builder;

import net.sf.anathema.character.generic.impl.magic.persistence.IGenericsBuilder;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.ComboRestrictions;
import net.sf.anathema.character.generic.traits.ITraitType;

import org.dom4j.Element;

public class GenericComboRulesBuilder extends ComboRulesBuilder implements IComboRulesBuilder, IGenericsBuilder {

  private ITraitType type;

  @Override
  protected void buildRestrictionList(ComboRestrictions comboRules, Element restrictionElement) throws CharmException {
    super.buildRestrictionList(comboRules, restrictionElement);
    for (String charmId : GenericCharmUtilities.getAllReferencedGenericCharms(restrictionElement, type)) {
      comboRules.addRestrictedCharmId(charmId ); //$NON-NLS-1$
    }
  }

  public void setType(ITraitType type) {
    this.type = type;
  }
}