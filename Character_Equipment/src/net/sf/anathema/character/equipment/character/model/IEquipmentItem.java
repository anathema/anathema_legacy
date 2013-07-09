package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.main.equipment.ArtifactAttuneType;
import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.equipment.core.ItemCost;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.lib.control.ChangeListener;

public interface IEquipmentItem {

  String getTitle();
  
  String getDescription();
	
  String getTemplateId();

  String getBaseDescription();
  
  void setPersonalization(String title, String description);
  
  void setPersonalization(IEquipmentItem item);
  
  ItemCost getCost();

  IEquipmentStats[] getStats();
  
  IEquipmentStats getStat(String name);

  void setPrintEnabled(IEquipmentStats equipment, boolean enabled);

  boolean isPrintEnabled(IEquipmentStats stats);

  void setUnprinted();

  void setPrinted(String printedStatId);

  MagicalMaterial getMaterial();

  MaterialComposition getMaterialComposition();
  
  ArtifactAttuneType getAttunementState();

  void addChangeListener(ChangeListener listener);

  void removeChangeListener(ChangeListener listener);
}