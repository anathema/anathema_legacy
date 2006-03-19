package net.sf.anathema.character.db.template.dynastic;

import net.sf.anathema.character.generic.impl.traits.AlternateMinimumsTraitTemplate;
import net.sf.anathema.character.generic.impl.traits.ExaltTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;

public class DynasticDbTraitTemplateFactory extends ExaltTraitTemplateFactory {

  @Override
  public ITraitTemplate createAbilityTemplate(AbilityType type) {
    if (type == AbilityType.Archery) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.Brawl) {
      ValuedTraitType martialArts = new ValuedTraitType(AbilityType.MartialArts, 1);
      return new AlternateMinimumsTraitTemplate(0, martialArts, 1);
    }
    if (type == AbilityType.MartialArts) {
      ValuedTraitType brawl = new ValuedTraitType(AbilityType.Brawl, 1);
      return new AlternateMinimumsTraitTemplate(1, brawl, 1);
    }
    if (type == AbilityType.Melee) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.Performance) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.Presence) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.Ride) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.Lore) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(2);
    }
    if (type == AbilityType.Socialize) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(2);
    }
    return super.createAbilityTemplate(type);
  }
}