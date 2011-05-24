package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.util.IIdentificate;

public abstract class AbstractStats implements IEquipmentStats {

  private IIdentificate name;
  private boolean useAttunementModifiers = true;
  private MagicalMaterial[] materials = null;

  public IIdentificate getName() {
    return name;
  }

  public final void setName(IIdentificate name) {
	if (name == null)
		name = null;
    this.name = name;
  }
  
  public boolean useAttunementModifiers()
  {
	  return useAttunementModifiers;
  }
  
  public void setUseAttunementModifiers(boolean value)
  {
	  useAttunementModifiers = value;
  }
  
  public Object[] getApplicableMaterials()
  {
	  return materials;
  }
  
  public void setApplicableMaterials(Object[] materials)
  {
	  this.materials = (MagicalMaterial[]) materials;
  }
}