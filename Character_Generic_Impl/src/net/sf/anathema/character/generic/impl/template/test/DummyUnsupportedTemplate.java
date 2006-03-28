package net.sf.anathema.character.generic.impl.template.test;

import net.sf.anathema.character.generic.impl.exalt.AbstractUnsupportedExaltTemplate;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identificate;

public class DummyUnsupportedTemplate extends AbstractUnsupportedExaltTemplate {

  private final String subtype;
  private final CharacterType type;
  private final IExaltedRuleSet[] rulesets;

  public DummyUnsupportedTemplate(CharacterType type, String subtype, IExaltedRuleSet[] rulesets) {
    this.type = type;
    this.subtype = subtype;
    this.rulesets = rulesets;
  }

  public IGroupedTraitType[] getAbilityGroups() {
    throw new NotYetImplementedException();
  }

  public IPresentationProperties getPresentationProperties() {
    throw new NotYetImplementedException();
  }

  public TemplateType getTemplateType() {
    if (subtype == null) {
      return new TemplateType(type);
    }
    return new TemplateType(type, new Identificate(subtype));
  }

  @Override
  public IExaltedRuleSet[] getRuleSets() {
    return rulesets;
  }

  public IMagicTemplate getMagicTemplate() {
    throw new NotYetImplementedException();
  }
}