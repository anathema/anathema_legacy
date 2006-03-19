package net.sf.anathema.character.db.template.cult;

import net.sf.anathema.character.generic.impl.traits.AlternateMinimumsTraitTemplate;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;

public class KetherRockDbTraitTemplateFactory extends AbstractCultDbTraitTemplateFactory {

  @Override
  public ITraitTemplate createAbilityTemplate(AbilityType type) {
    if (type == AbilityType.Archery) {
      ValuedTraitType brawl = new ValuedTraitType(AbilityType.Brawl, 1);
      return new AlternateMinimumsTraitTemplate(1, brawl, 1);
    }
    if (type == AbilityType.Brawl) {
      ValuedTraitType archery = new ValuedTraitType(AbilityType.Archery, 1);
      return new AlternateMinimumsTraitTemplate(0, archery, 1);
    }
    if (type == AbilityType.Endurance) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.Melee) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(2);
    }
    if (type == AbilityType.Medicine) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.Presence) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.Resistance) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.Survival) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(3);
    }
    return super.createAbilityTemplate(type);
  }
}