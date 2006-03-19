package net.sf.anathema.character.db.template.pirates;

import net.sf.anathema.character.db.template.dynastic.DynasticDbTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class PirateRealmDbTraitTemplateFactory extends DynasticDbTraitTemplateFactory {

  @Override
  public ITraitTemplate createAbilityTemplate(AbilityType type) {
    if (type == AbilityType.Sail) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    return super.createAbilityTemplate(type);
  }
}