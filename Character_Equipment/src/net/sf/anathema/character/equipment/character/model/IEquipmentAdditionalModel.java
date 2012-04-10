package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.IEquipmentCharacterDataProvider;
import net.sf.anathema.character.equipment.character.IEquipmentCharacterOptionProvider;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.model.traits.essence.IEssencePoolModifier;

public interface IEquipmentAdditionalModel extends IAdditionalModel, IEquipmentItemCollection,
	IEquipmentTemplateProvider, IEssencePoolModifier{

  MagicalMaterial getDefaultMaterial();

  MaterialComposition getMaterialComposition(String templateId);

  MagicalMaterial getMagicalMaterial(String templateId);

  IEquipmentPrintModel getPrintModel();

  IEquipmentCharacterDataProvider getCharacterDataProvider();
  
  void updateItem(IEquipmentItem item);

  void refreshItems();

  IEquipmentCharacterOptionProvider getCharacterOptionProvider();

  ICharacterStatsModifiers createStatsModifiers(IGenericCharacter character);
}