package net.sf.anathema.character.equipment.impl.item.model;

import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.ObjectUtilities;
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

  public EquipmentTemplateEditModel(IEquipmentDatabase database) {
    this.database = database;
  }

  public IItemDescription getDescription() {
    return description;
  }

  public void setEditTemplate(String templateId) {
    Ensure.ensureArgumentNotNull(templateId);
    editedTemplate = database.loadTemplate(templateId);
    // TODO Fehlerbehandlung bei Template nicht gefunden
    getDescription().getName().setText(editedTemplate.getName());
    getDescription().getContent().setText(editedTemplate.getDescription());
    statsByRuleSet.clear();
    for (ExaltedRuleSet ruleSet : ExaltedRuleSet.values()) {
      statsByRuleSet.add(ruleSet, editedTemplate.getStats(ruleSet));
    }
    fireStatsChangedEvent();
  }

  private void fireStatsChangedEvent() {
    statsChangeControl.fireChangedEvent();
  }

  public void setNewTemplate() {
    editedTemplate = null;
    getDescription().getName().setText(null);
    getDescription().getContent().setText(new ITextPart[0]);
    statsByRuleSet.clear();
    fireStatsChangedEvent();
  }

  public boolean isDirty() {
    if (editedTemplate == null) {
      return !getDescription().getName().isEmpty() || !getDescription().getContent().isEmpty();
    }
    return !ObjectUtilities.equals(editedTemplate.getName(), getDescription().getName().getText())
        || !ObjectUtilities.equals(editedTemplate.getDescription(), getDescription().getContent().getText());
  }

  public void addStatistics(IExaltedRuleSet ruleSet, IEquipmentStats stats) {
    statsByRuleSet.add(ruleSet, stats);
    fireStatsChangedEvent();
  }

  public IEquipmentStats[] getStats(IExaltedRuleSet ruleSet) {
    List<IEquipmentStats> allStats = statsByRuleSet.get(ruleSet);
    return allStats.toArray(new IEquipmentStats[allStats.size()]);
  }

  public void addStatsChangeListener(IChangeListener changeListener) {
    statsChangeControl.addChangeListener(changeListener);
  }
}