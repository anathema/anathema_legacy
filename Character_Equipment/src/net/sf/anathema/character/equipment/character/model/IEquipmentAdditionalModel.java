package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.IEquipmentCharacterDataProvider;
import net.sf.anathema.character.equipment.character.IEquipmentCharacterOptionProvider;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;

public interface IEquipmentAdditionalModel extends IAdditionalModel, IEquipmentItemCollection {

  MagicalMaterial getDefaultMaterial();
  
  MaterialComposition getMaterialComposition(String templateId);

  MagicalMaterial getMagicalMaterial(String templateId);
  
  int getTotalAttunementCost();

  IEquipmentPrintModel getPrintModel();
  
  IEquipmentCharacterDataProvider getCharacterDataProvider();

  void refreshItems();

  IEquipmentCharacterOptionProvider getCharacterOptionProvider();
}