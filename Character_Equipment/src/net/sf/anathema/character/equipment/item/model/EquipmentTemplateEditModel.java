package net.sf.anathema.character.equipment.item.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import net.sf.anathema.character.equipment.character.model.EquipmentTemplate;
import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.equipment.core.ItemCost;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.equipment.editor.model.IItemDescription;
import net.sf.anathema.equipment.editor.model.ItemDescription;
import net.sf.anathema.lib.control.ChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

public class EquipmentTemplateEditModel implements IEquipmentTemplateEditModel {

  public static final MagicalMaterial NO_MAGICAL_MATERIAL = null;
  private final IItemDescription description = new ItemDescription();
  private final IEquipmentDatabase database;
  private IEquipmentTemplate editedTemplate;
  private final List<IEquipmentStats> statses = new ArrayList<>();
  private final Announcer<ChangeListener> statsChangeControl = Announcer.to(ChangeListener.class);
  private final Announcer<ChangeListener> magicalMaterialControl = Announcer.to(ChangeListener.class);
  private final Announcer<ChangeListener> compositionControl = Announcer.to(ChangeListener.class);
  private final Announcer<ChangeListener> costControl = Announcer.to(ChangeListener.class);
  private String editTemplateId;
  private MaterialComposition composition;
  private MagicalMaterial material;
  private ItemCost cost;

  public EquipmentTemplateEditModel(IEquipmentDatabase database) {
    this.database = database;
  }

  @Override
  public IItemDescription getDescription() {
    return description;
  }

  @Override
  public void setEditTemplate(String templateId) {
    Preconditions.checkNotNull(templateId);
    this.editTemplateId = templateId;
    editedTemplate = database.loadTemplate(templateId);
    getDescription().getName().setText(editedTemplate.getName());
    getDescription().getContent().setText(editedTemplate.getDescription());
    setMaterial(editedTemplate.getComposition(), editedTemplate.getMaterial());
    setCost(editedTemplate.getCost());
    statses.clear();
    addAll(statses, editedTemplate.getStatsList());
    fireStatsChangedEvent();
  }

  @Override
  public String getEditTemplateId() {
    return editTemplateId;
  }

  private void fireStatsChangedEvent() {
    statsChangeControl.announce().changeOccurred();
  }

  @Override
  public void setNewTemplate() {
    editTemplateId = null;
    editedTemplate = null;
    getDescription().getName().setText(null);
    getDescription().getContent().setText(null);
    setMaterialComposition(MaterialComposition.None);
    setCost(new ItemCost("Resources", 0));
    statses.clear();
    fireStatsChangedEvent();
  }

  @Override
  public void copyNewTemplate(String salt) {
    editTemplateId += salt;
    getDescription().getName().setText(editTemplateId);
    editedTemplate = createTemplate();
    fireStatsChangedEvent();
  }

  @Override
  public boolean isDirty() {
    List<IEquipmentStats> currentStats = getAllCurrentStats();
    List<IEquipmentStats> previousStats = getAllPreviousStats();
    if (currentStats.size() != previousStats.size() || !currentStats.containsAll(previousStats)) {
      return true;
    }
    if (editedTemplate == null) {
      return !getDescription().getName().isEmpty() || !getDescription().getContent().isEmpty();
    }
    return !Objects.equal(editedTemplate.getName(), getDescription().getName().getText()) ||
           !Objects.equal(editedTemplate.getDescription(), getDescription().getContent().getText()) ||
           !(editedTemplate.getComposition() == getMaterialComposition()) || !(editedTemplate.getMaterial() == getMagicalMaterial()) ||
           (getCost() != null && !getCost().equals(editedTemplate.getCost()));
  }

  private List<IEquipmentStats> getAllPreviousStats() {
    List<IEquipmentStats> allStats = new ArrayList<>();
    if (editedTemplate != null) {
      addAll(allStats, editedTemplate.getStatsList());
    }
    return allStats;
  }

  private List<IEquipmentStats> getAllCurrentStats() {
    List<IEquipmentStats> allStats = new ArrayList<>();
    allStats.addAll(statses);
    return allStats;
  }

  @Override
  public void addStatistics(IEquipmentStats stats) {
    statses.add(stats);
    fireStatsChangedEvent();
  }

  @Override
  public void removeStatistics(IEquipmentStats... stats) {
    for (IEquipmentStats stat : stats) {
      statses.remove(stat);
    }
    fireStatsChangedEvent();
  }

  @Override
  public List<IEquipmentStats> getStats() {
    return statses;
  }

  @Override
  public void addStatsChangeListener(ChangeListener changeListener) {
    statsChangeControl.addListener(changeListener);
  }

  @Override
  public IEquipmentTemplate createTemplate() {
    String name = getDescription().getName().getText();
    String descriptionText = getDescription().getContent().getText();
    EquipmentTemplate template = new EquipmentTemplate(name, descriptionText, composition, material, cost);
    for (IEquipmentStats stats : statses) {
      template.addStats(stats);
    }
    return template;
  }

  @Override
  public void addMagicalMaterialChangeListener(ChangeListener listener) {
    magicalMaterialControl.addListener(listener);
  }

  @Override
  public void addCompositionChangeListener(ChangeListener listener) {
    compositionControl.addListener(listener);
  }

  @Override
  public void addCostChangeListener(ChangeListener listener) {
    costControl.addListener(listener);
  }

  @Override
  public void setCost(ItemCost newCost) {
    if (newCost != null && newCost.equals(cost)) {
      return;
    }
    this.cost = newCost;
    costControl.announce().changeOccurred();
  }

  @Override
  public ItemCost getCost() {
    return cost;
  }

  @Override
  public MagicalMaterial getMagicalMaterial() {
    return material;
  }

  @Override
  public void setMagicalMaterial(MagicalMaterial material) {
    if (material == this.material) {
      return;
    }
    if (!composition.requiresMaterial() && material != NO_MAGICAL_MATERIAL) {
      return;
    }
    this.material = material;
    magicalMaterialControl.announce().changeOccurred();
  }

  @Override
  public void setMaterialComposition(MaterialComposition composition) {
    if (composition == this.composition) {
      return;
    }
    this.composition = composition;
    if (composition.requiresMaterial()) {
      setMagicalMaterial(MagicalMaterial.Orichalcum);
    } else {
      setMagicalMaterial(NO_MAGICAL_MATERIAL);
    }
    compositionControl.announce().changeOccurred();
  }

  private void setMaterial(MaterialComposition composition, MagicalMaterial material) {
    if (composition != this.composition) {
      this.composition = composition;
    }
    if (composition.requiresMaterial()) {
      if (material != this.material) {
        this.material = material;
      }
    } else {
      setMagicalMaterial(NO_MAGICAL_MATERIAL);
    }
    compositionControl.announce().changeOccurred();
    magicalMaterialControl.announce().changeOccurred();
  }

  @Override
  public MaterialComposition getMaterialComposition() {
    return composition;
  }

  @Override
  public void replaceStatistics(IEquipmentStats oldStats, IEquipmentStats newStats) {
    int oldIndex = statses.indexOf(oldStats);
    statses.remove(oldStats);
    statses.add(oldIndex, newStats);
    fireStatsChangedEvent();
  }
}