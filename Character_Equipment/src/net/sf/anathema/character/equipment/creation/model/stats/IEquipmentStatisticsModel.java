package net.sf.anathema.character.equipment.creation.model.stats;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IEquipmentStatisticsModel {

  public ITextualDescription getName();
  
  public MagicalMaterial getMaterialType();
  
  public void setMaterialType(MagicalMaterial type);
}