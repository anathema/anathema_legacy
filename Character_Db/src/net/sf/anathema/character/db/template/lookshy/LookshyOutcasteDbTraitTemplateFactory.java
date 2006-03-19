package net.sf.anathema.character.db.template.lookshy;

import net.sf.anathema.character.generic.impl.traits.ExaltTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class LookshyOutcasteDbTraitTemplateFactory extends ExaltTraitTemplateFactory {

  @Override
  public ITraitTemplate createAbilityTemplate(AbilityType type) {
    if (type == AbilityType.Linguistics) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(3);
    }
    if (type == AbilityType.Lore) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(2);
    }
    return super.createAbilityTemplate(type);
  }
}