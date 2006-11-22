package net.sf.anathema.character.lunar.beastform.model.gift;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class BrawlWeaponProvidingGift extends Gift {

  private final Map<IExaltedRuleSet, IWeaponStats> biteWeapons = new HashMap<IExaltedRuleSet, IWeaponStats>();
  private final Map<IExaltedRuleSet, IWeaponStats> handWeapons = new HashMap<IExaltedRuleSet, IWeaponStats>();

  public BrawlWeaponProvidingGift(String id) {
    super(id);
  }

  public void addHandWeapon(IExaltedRuleSet rules, IWeaponStats type) {
    handWeapons.put(rules, type);
  }

  public void addBiteWeapon(IExaltedRuleSet rules, IWeaponStats type) {
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

  public IWeaponStats getHandWeapon(IExaltedRuleSet rules) {
    return handWeapons.get(rules);
  }

  public IWeaponStats getBiteWeapon(IExaltedRuleSet rules) {
    return biteWeapons.get(rules);
  }
}