package net.sf.anathema.hero.equipment.model.natural;

import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.equipment.core.ItemCost;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;

public class NaturalWeaponTemplate implements IEquipmentTemplate {

  public static final IEquipmentStats[] SECOND_EDITION_WEAPONS = new IEquipmentStats[]{new Punch(), new Kick(), new Clinch()};
  private static final String NATURAL = "Natural";

  @Override
  public String getDescription() {
    return NATURAL;
  }

  @Override
  public IEquipmentStats[] getStats() {
    return SECOND_EDITION_WEAPONS;
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