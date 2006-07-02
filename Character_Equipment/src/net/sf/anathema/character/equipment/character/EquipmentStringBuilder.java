package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.generic.equipment.weapon.IEquipment;
import net.sf.anathema.character.generic.equipment.weapon.IWeapon;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentStringBuilder implements IEquipmentStringBuilder {

  private final IResources resources;
  
  public EquipmentStringBuilder(IResources resources) {
    this.resources = resources;
  }

  private String createWeaponString(IWeapon weapon) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(weapon.getName().getId());
    stringBuilder.append(":");
    stringBuilder.append(getStatsString("Speed", weapon.getSpeed(), false));
    stringBuilder.append(getStatsString("Accuracy", weapon.getAccuracy(), true));
    stringBuilder.append(getStatsString("Damage", weapon.getDamage(), true));
    stringBuilder.append(resources.getString("Weapons.Damage." + weapon.getDamageType().getId() + ".Short"));
    stringBuilder.append(getStatsString("Defence", weapon.getDefence(), true));
    stringBuilder.append(getStatsString("Range", weapon.getRange(), false));
    stringBuilder.append(getStatsString("Rate", weapon.getRate(), false));
    return stringBuilder.toString();
  }
  
  private String getStatsString(String keyPart, Integer value, boolean printSignum) {
    if (value == null) {
      return "";
    }
    String signum = printSignum && value >= 0 ? "+" : "";
    return " " + resources.getString("Equipment.Stats.Short." + keyPart) + ":" + signum + value;
  }

  /* (non-Javadoc)
   * @see net.sf.anathema.character.equipment.IEquipmentStringBuilder#createString(net.sf.anathema.character.generic.equipment.weapon.IEquipment)
   */
  public String createString(IEquipment equipment) {
    if (equipment instanceof IWeapon) {
      return createWeaponString((IWeapon) equipment);
    }
    throw new UnsupportedOperationException();

  }
}