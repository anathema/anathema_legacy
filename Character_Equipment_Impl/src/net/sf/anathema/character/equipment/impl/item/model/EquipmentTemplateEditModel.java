package net.sf.anathema.character.equipment.impl.item.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentTemplate;
import net.sf.anathema.character.equipment.item.model.IEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.framework.itemdata.model.ItemDescription;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class EquipmentTemplateEditModel implements IEquipmentTemplateEditModel {

  private final IItemDescription description = new ItemDescription();
  private final IEquipmentDatabase database;
  private IEquipmentTemplate editedTemplate;
  private final MultiEntryMap<IExaltedRuleSet, IEquipmentStats> statsByRuleSet = new MultiEntryMap<IExaltedRuleSet, IEquipmentStats>();
  private final ChangeControl statsChangeControl = new ChangeControl();
  private final ChangeControl magicalMaterialControl = new ChangeControl();
  private String editTemplateId;
  private MagicalMaterial material;

  public EquipmentTemplateEditModel(IEquipmentDatabase database) {
    this.database = database;
  }

  public IItemDescription getDescription() {
    return description;
  }

  public void setEditTemplate(String templateId) {
    Ensure.ensureArgumentNotNull(templateId);
    this.editTemplateId = templateId;
    editedTemplate = database.loadTemplate(templateId);
    // TODO Fehlerbehandlung bei Template nicht gefunden
    getDescription().getName().setText(editedTemplate.getName());
    getDescription().getContent().setText(editedTemplate.getDescription());
    setMagicalMaterial(editedTemplate.getMaterial());
    statsByRuleSet.clear();
    for (ExaltedRuleSet ruleSet : ExaltedRuleSet.values()) {
      statsByRuleSet.add(ruleSet, editedTemplate.getStats(ruleSet));
    }
    fireStatsChangedEvent();
  }

  public String getEditTemplateId() {
    return editTemplateId;
  }

  private void fireStatsChangedEvent() {
    statsChangeControl.fireChangedEvent();
  }

  public void setNewTemplate() {
    editTemplateId = null;
    editedTemplate = null;
    getDescription().getName().setText(null);
    getDescription().getContent().setText(new ITextPart[0]);
    setMagicalMaterial(MagicalMaterial.None);
    statsByRuleSet.clear();
    fireStatsChangedEvent();
  }

  public boolean isDirty() {
    List<IEquipmentStats> currentStats = getAllCurrentStats();
    List<IEquipmentStats> previousStats = getAllPreviousStats();
    if (currentStats.size() != previousStats.size() || !currentStats.containsAll(previousStats)) {
      return true;
    }
    if (editedTemplate == null) {
      return !getDescription().getName().isEmpty() || !getDescription().getContent().isEmpty();
    }
    return !ObjectUtilities.equals(editedTemplate.getName(), getDescription().getName().getText())
        || !ObjectUtilities.equals(editedTemplate.getDescription(), getDescription().getContent().getText())
        || !(editedTemplate.getMaterial() == getMagicalMaterial());
  }

  private List<IEquipmentStats> getAllPreviousStats() {
    List<IEquipmentStats> allStats = new ArrayList<IEquipmentStats>();
    if (editedTemplate != null) {
      for (ExaltedRuleSet ruleSet : ExaltedRuleSet.values()) {
        allStats.addAll(Arrays.asList(editedTemplate.getStats(ruleSet)));
      }
    }
    return allStats;
  }

  private List<IEquipmentStats> getAllCurrentStats() {
    List<IEquipmentStats> allStats = new ArrayList<IEquipmentStats>();
    for (IExaltedRuleSet ruleSet : statsByRuleSet.keySet()) {
      allStats.addAll(statsByRuleSet.get(ruleSet));
    }
    return allStats;
  }

  public void addStatistics(IExaltedRuleSet ruleSet, IEquipmentStats stats) {
    statsByRuleSet.add(ruleSet, stats);
    fireStatsChangedEvent();
  }

  public void removeStatistics(IExaltedRuleSet ruleSet, IEquipmentStats... stats) {
    for (IEquipmentStats stat : stats) {
      statsByRuleSet.removeValue(ruleSet, stat);
    }
    fireStatsChangedEvent();
  }

  public IEquipmentStats[] getStats(IExaltedRuleSet ruleSet) {
    List<IEquipmentStats> allStats = statsByRuleSet.get(ruleSet);
    return allStats.toArray(new IEquipmentStats[allStats.size()]);
  }

  public void addStatsChangeListener(IChangeListener changeListener) {
    statsChangeControl.addChangeListener(changeListener);
  }

  public IEquipmentTemplate createTemplate() {
    String name = getDescription().getName().getText();
    String descriptionText = getDescription().getContent().getText();
    EquipmentTemplate template = new EquipmentTemplate(name, descriptionText, material, database.getCollectionFactory());
    for (IExaltedRuleSet ruleSet : statsByRuleSet.keySet()) {
      for (IEquipmentStats stats : statsByRuleSet.get(ruleSet)) {
        template.addStats(ruleSet, stats);
      }
    }
    return template;
  }

  public void addMagicalMaterialChangeListener(IChangeListener listener) {
    magicalMaterialControl.addChangeListener(listener);
  }

  public MagicalMaterial getMagicalMaterial() {
    return material;
  }

  public void setMagicalMaterial(MagicalMaterial material) {
    if (material == this.material) {
      return;
    }
    this.material = material;
    magicalMaterialControl.fireChangedEvent();
  }
}