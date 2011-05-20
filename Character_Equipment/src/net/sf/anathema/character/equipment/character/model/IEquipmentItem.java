package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IEquipmentItem {

  public String getTemplateId();

  public String getDescription();

  public IEquipmentStats[] getStats();

  public void setPrintEnabled(IEquipmentStats equipment, boolean enabled);

  public boolean isPrintEnabled(IEquipmentStats stats);

  public void setUnprinted();

  public void setPrinted(String printedStatId);

  public MagicalMaterial getMaterial();

  public MaterialComposition getMaterialComposition();
  
  public ArtifactAttuneType getAttunementState();

  public void addChangeListener(IChangeListener listener);

  public void removeChangeListener(IChangeListener listener);
}