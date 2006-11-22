package net.sf.anathema.character.lunar.beastform.model;

import net.sf.anathema.character.equipment.character.model.IEquipmentItemCollection;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentPrintModel;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.lunar.beastform.model.gift.IGiftModel;

public class BeastformEquipmentModel extends EquipmentPrintModel {

  public BeastformEquipmentModel(
      IEquipmentItemCollection itemCollection,
      BeastformGenericTraitCollection traitCollection,
      IGiftModel giftModel) {
    super(itemCollection, new BeastformNaturalSoak(traitCollection.getTrait(AttributeType.Stamina), giftModel));
  }
}