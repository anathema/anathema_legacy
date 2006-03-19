/* Anathema (C) 2004,2005 by Sandra Sieroux and Urs Reupke. Published under the GNU GPL. See license.txt for details. */
package net.sf.anathema.character.db.template.outcaste;

import net.sf.anathema.character.generic.impl.traits.AlternateMinimumsTraitTemplate;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;

public class LowClassOutcasteDbTraitTemplateFactory extends RealmOutcasteDbTraitTemplateFactory {

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
      ValuedTraitType presence = new ValuedTraitType(AbilityType.Presence, 1);
      return new AlternateMinimumsTraitTemplate(1, presence, 1);
    }
    if (type == AbilityType.Presence) {
      ValuedTraitType performance = new ValuedTraitType(AbilityType.Performance, 1);
      return new AlternateMinimumsTraitTemplate(0, performance, 1);
    }
    if (type == AbilityType.Ride) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.Lore) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    if (type == AbilityType.Socialize) {
      return SimpleTraitTemplate.createEssenceLimitedTemplate(1);
    }
    return super.createAbilityTemplate(type);
  }
}