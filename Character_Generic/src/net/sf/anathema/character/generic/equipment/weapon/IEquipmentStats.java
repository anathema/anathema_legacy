package net.sf.anathema.character.generic.equipment.weapon;

import net.sf.anathema.character.generic.util.IStats;
import net.sf.anathema.lib.util.IIdentificate;

public interface IEquipmentStats extends IStats, IIdentificate {
  public boolean useAttunementModifiers();
  public void setUseAttunementModifiers(boolean value);
  public Object[] getApplicableMaterials();
  public void setApplicableMaterials(Object[] materials);
}