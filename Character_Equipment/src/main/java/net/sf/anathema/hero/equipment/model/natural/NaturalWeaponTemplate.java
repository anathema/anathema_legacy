package net.sf.anathema.hero.equipment.model.natural;

import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.equipment.core.ItemCost;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;

import java.util.Collection;

import static com.google.common.collect.Lists.newArrayList;

public class NaturalWeaponTemplate implements IEquipmentTemplate {

  public static final Collection<IEquipmentStats> SECOND_EDITION_WEAPONS = newArrayList(new Punch(), new Kick(), new Clinch());
  private static final String NATURAL = "Natural";

  @Override
  public String getDescription() {
    return NATURAL;
  }

  @Override
  public Collection<IEquipmentStats> getStatsList() {
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