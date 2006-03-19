package net.sf.anathema.character.lunar.beastform.model.gift;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.impl.equipment.MeleeWeaponType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class BrawlWeaponProvidingGift extends Gift {

  private final Map<IExaltedRuleSet, MeleeWeaponType> biteWeapons = new HashMap<IExaltedRuleSet, MeleeWeaponType>();
  private final Map<IExaltedRuleSet, MeleeWeaponType> handWeapons = new HashMap<IExaltedRuleSet, MeleeWeaponType>();

  public BrawlWeaponProvidingGift(String id) {
    super(id);
  }

  public void addHandWeapon(IExaltedRuleSet rules, MeleeWeaponType type) {
    handWeapons.put(rules, type);
  }

  public void addBiteWeapon(IExaltedRuleSet rules, MeleeWeaponType type) {
    biteWeapons.put(rules, type);
  }

  @Override
  public void accept(IGiftVisitor visitor) {
    visitor.acceptBrawlWeaponProvidingGift(this);
  }

  public BrawlWeaponProvidingGift getHighestInHierarchy(BrawlWeaponProvidingGift gift) {
    if (gift != null && gift.isPrerequisite(this)) {
      return gift;
    }
    return this;
  }

  public MeleeWeaponType getHandWeapon(ExaltedRuleSet rules) {
    return handWeapons.get(rules);
  }

  public MeleeWeaponType getBiteWeapon(ExaltedRuleSet rules) {
    return biteWeapons.get(rules);
  }
}