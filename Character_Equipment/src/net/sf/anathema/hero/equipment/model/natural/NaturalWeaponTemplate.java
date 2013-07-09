package net.sf.anathema.hero.equipment.model.natural;

import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.equipment.core.ItemCost;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;

public class NaturalWeaponTemplate implements IEquipmentTemplate {

  private static final String NATURAL = "Natural";

  @Override
  public String getDescription() {
    return NATURAL;
  }

  @Override
  public IEquipmentStats[] getStats() {
    return INaturalWeaponConstants.SECOND_EDITION;
  }

  @Override
  public String getName() {
    return NATURAL;
  }

  @Override
  public MaterialComposition getComposition() {
    return MaterialComposition.None;
  }

  @Override
  public MagicalMaterial getMaterial() {
    return null;
  }

  @Override
  public ItemCost getCost() {
    return null;
  }
}