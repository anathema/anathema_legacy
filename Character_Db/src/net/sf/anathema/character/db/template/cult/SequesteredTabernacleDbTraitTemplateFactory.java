package net.sf.anathema.character.db.template.cult;

import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class SequesteredTabernacleDbTraitTemplateFactory extends AbstractCultDbTraitTemplateFactory {
  @Override
  public ITraitTemplate createAbilityTemplate(AbilityType type) {
    if (type == AbilityType.Endurance) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.MartialArts) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(3);
    }
    if (type == AbilityType.Lore) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.Linguistics) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.Presence) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(3);
    }
    if (type == AbilityType.Occult) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.Socialize) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    return super.createAbilityTemplate(type);
  }

  @Override
  public ITraitTemplate createEssenceTemplate() {
    return EssenceTemplate.createDbTemplate(3);
  }
}