package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.EquipmentOptionsProvider;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.generic.framework.essence.IEssencePoolModifier;

public interface EquipmentModel extends IAdditionalModel, IEquipmentItemCollection, IEquipmentTemplateProvider, IEssencePoolModifier {

  MagicalMaterial getDefaultMaterial();

  MaterialComposition getMaterialComposition(String templateId);

  MagicalMaterial getMagicalMaterial(String templateId);

  IEquipmentPrintModel getPrintModel();

  EquipmentHeroEvaluator getCharacterDataProvider();

  void updateItem(IEquipmentItem item);

  void refreshItems();

  EquipmentOptionsProvider getCharacterOptionProvider();

  ICharacterStatsModifiers createStatsModifiers(IGenericCharacter character);
}