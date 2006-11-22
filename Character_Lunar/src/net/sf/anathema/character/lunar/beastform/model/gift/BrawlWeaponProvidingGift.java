package net.sf.anathema.character.lunar.beastform.model.gift;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;

public class BrawlWeaponProvidingGift extends Gift {

  private final IEquipmentTemplate template;

  public BrawlWeaponProvidingGift(String id, IEquipmentTemplate template) {
    super(id);
    this.template = template;
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
}