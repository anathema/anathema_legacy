package net.sf.anathema.character.equipment.dummy;

import net.sf.anathema.character.equipment.ItemCost;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractStats;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.control.IChangeListener;

import java.util.ArrayList;
import java.util.List;

public class DummyEquipmentObject extends AbstractStats implements IEquipmentItem {

  private final List<IEquipmentStats> allEquipments = new ArrayList<>();
  private final String name;
  private final String description;

  public DummyEquipmentObject(String title, String description) {
    this.name = title;
    this.description = description;
  }

  public void addEquipment(IEquipmentStats equipment) {
    this.allEquipments.add(equipment);
  }

  @Override
  public IEquipmentStats[] getStats() {
    return allEquipments.toArray(new IEquipmentStats[allEquipments.size()]);
  }

  @Override
  public String getTemplateId() {
    return name;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public ItemCost getCost() {
    return null;
  }

  @Override
  public void setPersonalization(String title, String description) {
    // nothing to do
  }

  @Override
  public void setPersonalization(IEquipmentItem item) {
    // nothing to do
  }

  @Override
  public void setPrintEnabled(IEquipmentStats equipment, boolean enabled) {
    //nothing to do;    
  }

  @Override
  public boolean isPrintEnabled(IEquipmentStats stats) {
    return false;
  }

  @Override
  public void setUnprinted() {
    //nothing to do    
  }

  @Override
  public void setPrinted(String printedStatId) {
    //nothing to do
  }

  @Override
  public MagicalMaterial getMaterial() {
    return null;
  }

  @Override
  public MaterialComposition getMaterialComposition() {
    return MaterialComposition.None;
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    // nothing to do
  }

  @Override
  public void removeChangeListener(IChangeListener listener) {
    // nothing to do
  }

  @Override
  public ArtifactAttuneType getAttunementState() {
    return null;
  }

  @Override
  public String getId() {
    return null;
  }

  @Override
  public IEquipmentStats getStat(String name) {
    return null;
  }

  @Override
  public boolean representsItemForUseInCombat() {
    return false;
  }

  @Override
  public String getBaseDescription() {
    return getDescription();
  }

  @Override
  public String getTitle() {
    return getTemplateId();
  }
}