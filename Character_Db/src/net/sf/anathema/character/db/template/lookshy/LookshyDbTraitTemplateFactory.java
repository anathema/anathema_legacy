package net.sf.anathema.character.db.template.lookshy;

import net.sf.anathema.character.db.template.dynastic.DynasticDbTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.AlternateMinimumsTraitTemplate;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;

public class LookshyDbTraitTemplateFactory extends DynasticDbTraitTemplateFactory {

  @Override
  public ITraitTemplate createAbilityTemplate(AbilityType type) {
    if (type == AbilityType.Archery) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(2);
    }
    if (type == AbilityType.Brawl) {
      ValuedTraitType martialArts = new ValuedTraitType(AbilityType.MartialArts, 2);
      return new AlternateMinimumsTraitTemplate(0, martialArts, 2);
    }
    if (type == AbilityType.MartialArts) {
      ValuedTraitType brawl = new ValuedTraitType(AbilityType.Brawl, 2);
      return new AlternateMinimumsTraitTemplate(2, brawl, 2);
    }
    if (type == AbilityType.Melee) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(2);
    }
    if (type == AbilityType.Socialize) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(0);
    }
    if (type == AbilityType.Stealth) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.Linguistics) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(3);
    }
    return super.createAbilityTemplate(type);
  }
}