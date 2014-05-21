package net.sf.anathema.hero.combat.model.social;

import net.sf.anathema.character.framework.library.HeroStatsModifiers;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.hero.combat.model.CharacterUtilities;
import net.sf.anathema.hero.combat.sheet.social.stats.ISocialCombatStats;
import net.sf.anathema.hero.traits.model.TraitMap;

public abstract class AbstractSocialAttack implements ISocialCombatStats {

  private final TraitMap collection;
  private HeroStatsModifiers equipmentModifiers;

  public AbstractSocialAttack(TraitMap collection, HeroStatsModifiers equipmentModifiers) {
    this.collection = collection;
    this.equipmentModifiers = equipmentModifiers;
  }

  @Override
  public final int getDeceptionAttackValue() {
    return CharacterUtilities.getSocialAttackValue(collection, AttributeType.Manipulation, getName());
  }

  @Override
  public final int getDeceptionMDV() {
    return CharacterUtilities.getParryMdv(collection, equipmentModifiers, AttributeType.Manipulation, getName());
  }

  @Override
  public final int getHonestyAttackValue() {
    return CharacterUtilities.getSocialAttackValue(collection, AttributeType.Charisma, getName());
  }

  @Override
  public final int getHonestyMDV() {
    return CharacterUtilities.getParryMdv(collection, equipmentModifiers, AttributeType.Charisma, getName());
  }

  @Override
  public abstract TraitType getName();

}