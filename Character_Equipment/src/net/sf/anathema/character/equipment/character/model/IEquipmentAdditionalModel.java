package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;

public interface IEquipmentAdditionalModel extends IAdditionalModel, IEquipmentItemCollection, IEquipmentPrintModel {

  public MagicalMaterial getDefaultMaterial();

  public MaterialComposition getMaterialComposition(String templateId);

  public MagicalMaterial getMagicalMaterial(String templateId);

}