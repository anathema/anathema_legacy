package net.sf.anathema.character.mutations.model.types;

import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.mutations.model.IMutationVisitor;
import net.sf.anathema.character.mutations.model.Mutation;

public class BrawlWeaponProvidingMutation extends Mutation {

  @SuppressWarnings("unused")
  private final IEquipmentTemplate template;

  public BrawlWeaponProvidingMutation(String id, IEquipmentTemplate template) {
    super(id);
    this.template = template;
  }

  @Override
  public void accept(IMutationVisitor visitor) {
    visitor.acceptBrawlWeaponProvidingMutation(this);
  }

  public BrawlWeaponProvidingMutation getHighestInHierarchy(BrawlWeaponProvidingMutation gift) {
    if (gift != null && gift.isPrerequisite(this)) {
      return gift;
    }
    return this;
  }
}