package net.sf.anathema.character.db.template.pirates;

import net.sf.anathema.character.db.DbCharacterModule;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.impl.traits.ExaltTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class PirateOutcasteDbTraitTemplateFactory extends ExaltTraitTemplateFactory {

  @Override
  public ITraitTemplate createAbilityTemplate(AbilityType type) {
    if (type == AbilityType.Sail) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    return super.createAbilityTemplate(type);
  }

  @Override
  public ITraitTemplate createBackgroundTemplate(IBackgroundTemplate template) {
    if (template.getId().equals(DbCharacterModule.BACKGROUND_ID_BREEDING)) {
      return SimpleTraitTemplate.createStaticLimitedTemplate(0, 3, template.getExperiencedState());
    }
    return super.createBackgroundTemplate(template);
  }
}