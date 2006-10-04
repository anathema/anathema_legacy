package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;

public interface IEquipmentAdditionalModel extends IAdditionalModel, IEquipmentItemCollection {

  public MagicalMaterial getDefaultMaterial();

  public MaterialComposition getMaterialComposition(String templateId);

  public MagicalMaterial getMagicalMaterial(String templateId);

  public IArmourStats[] getPrintArmours();

  public IWeaponStats[] getPrintWeapons();

  public IArmourStats getTotalPrintArmour(int lineCount);
}