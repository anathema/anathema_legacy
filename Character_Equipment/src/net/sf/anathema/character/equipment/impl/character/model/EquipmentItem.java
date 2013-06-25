package net.sf.anathema.character.equipment.impl.character.model;

import com.google.common.base.Function;
import net.sf.anathema.character.equipment.ItemCost;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.ItemAttunementEvaluator;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.impl.character.model.stats.ProxyArmourStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ProxyWeaponStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.BaseMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.InertBaseMaterial;
import net.sf.anathema.character.equipment.impl.character.model.stats.modification.ReactiveBaseMaterial;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.ArtifactStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.util.IProxy;
import net.sf.anathema.lib.control.ChangeListener;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.sf.anathema.character.equipment.MaterialComposition.Variable;
import static net.sf.anathema.lib.lang.ArrayUtilities.transform;

public class EquipmentItem implements IEquipmentItem {

  private final Set<IEquipmentStats> printedStats = new HashSet<>();
  private final Announcer<ChangeListener> changeControl = Announcer.to(ChangeListener.class);
  private final IEquipmentTemplate template;
  private final MagicalMaterial material;
  private final ModifierFactory modifiers;
  private String customTitle;
  private String customDescription;

  public EquipmentItem(IEquipmentTemplate template, String title, String description, MagicalMaterial material, ItemAttunementEvaluator provider,
                       ModifierFactory modifiers) {
    this.modifiers = modifiers;
    if (template.getComposition() == Variable && material == null) {
      throw new MissingMaterialException("Variable material items must be created with material.");
    }
    this.template = template;
    this.material = material != null ? material : template.getMaterial();
    this.customTitle = title;
    this.customDescription = description;
    Collections.addAll(printedStats, template.getStats());
    initPrintStats(provider);
  }

  @Override
  public String getDescription() {
    return customDescription != null ? customDescription : getBaseDescription();
  }

  @Override
  public String getBaseDescription() {
    return template.getDescription();
  }

  @Override
  public void setPersonalization(String title, String description) {
    this.customTitle = title != null && !title.isEmpty() ? title : null;
    this.customDescription = description != null && !description.isEmpty() ? description : null;
  }

  @Override
  public void setPersonalization(IEquipmentItem item) {
    this.customTitle = ((EquipmentItem) item).customTitle;
    this.customDescription = ((EquipmentItem) item).customDescription;
  }

  @Override
  public ItemCost getCost() {
    return template.getCost();
  }

  @Override
  public IEquipmentStats[] getStats() {
    return transform(getViews(), IEquipmentStats.class, new MaterialWrapper());
  }

  private IEquipmentStats[] getViews() {
    IEquipmentStats[] statsArray = template.getStats();
    List<IEquipmentStats> views = new ArrayList<>();
    for (IEquipmentStats stats : statsArray) {
      if (stats instanceof IWeaponStats) {
        Collections.addAll(views, ((IWeaponStats) stats).getViews());
      } else if (stats instanceof ArtifactStats) {
        Collections.addAll(views, ((ArtifactStats) stats).getViews());
      } else {
        views.add(stats);
      }
    }
    return views.toArray(new IEquipmentStats[views.size()]);
  }

  @Override
  public String getTitle() {
    return customTitle != null ? customTitle : getTemplateId();
  }

  @Override
  public String getTemplateId() {
    return template.getName();
  }

  @Override
  public MagicalMaterial getMaterial() {
    return material;
  }

  @Override
  public MaterialComposition getMaterialComposition() {
    return template.getComposition();
  }

  @Override
  public ArtifactAttuneType getAttunementState() {
    for (IEquipmentStats stats : getViews()) {
      if (stats instanceof ArtifactStats) {
        if (isPrintEnabled(stats)) {
          return ((ArtifactStats) stats).getAttuneType();
        }
      }
    }
    return ArtifactAttuneType.Unattuned;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean isPrintEnabled(IEquipmentStats stats) {
    if (stats instanceof IProxy<?>) {
      stats = ((IProxy<? extends IEquipmentStats>) stats).getUnderlying();
    }
    return printedStats.contains(stats);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void setPrintEnabled(IEquipmentStats stats, boolean enabled) {
    if (stats instanceof IProxy<?>) {
      stats = ((IProxy<? extends IEquipmentStats>) stats).getUnderlying();
    }
    if (isPrintEnabled(stats) == enabled) {
      return;
    }
    if (enabled) {
      printedStats.add(stats);
    } else {
      printedStats.remove(stats);
    }
    announceChange();
  }

  @Override
  public void setUnprinted() {
    printedStats.clear();
    announceChange();
  }

  @Override
  public void setPrinted(String printedStatId) {
    for (IEquipmentStats view : getViews()) {
      if (view.getId().equals(printedStatId)) {
        setPrintEnabled(view, true);
        return;
      }
    }
  }

  @Override
  public IEquipmentStats getStat(String statId) {
    for (IEquipmentStats view : getViews()) {
      if (view.getId().equals(statId)) {
        return view;
      }
    }
    return null;
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    changeControl.addListener(listener);
  }

  @Override
  public void removeChangeListener(ChangeListener listener) {
    changeControl.removeListener(listener);
  }

  private void announceChange() {
    changeControl.announce().changeOccurred();
  }

  public String toString() {
    return template.getName() + (material != null ? " (" + material.toString() + ")" : "");
  }

  private void initPrintStats(ItemAttunementEvaluator provider) {
    ArtifactStats bestAttune = null;
    for (IEquipmentStats stat : getViews()) {
      if (stat instanceof ArtifactStats) {
        if (hasAttunementType((ArtifactStats) stat, provider.getAttuneTypes(this)) &&
            (bestAttune == null || ((ArtifactStats) stat).getAttuneType().compareTo(bestAttune.getAttuneType()) > 0)) {
          bestAttune = (ArtifactStats) stat;
        }
        continue;
      }
      printedStats.add(stat);
    }
    if (bestAttune != null) {
      printedStats.add(bestAttune);
    }
  }

  private boolean hasAttunementType(ArtifactStats stats, ArtifactAttuneType[] types) {
    for (ArtifactAttuneType type : types) {
      if (type.equals(stats.getAttuneType())) {
        return true;
      }
    }
    return false;
  }

  private class MaterialWrapper implements Function<IEquipmentStats, IEquipmentStats> {

    @Override
    public IEquipmentStats apply(IEquipmentStats stats) {
      BaseMaterial baseMaterial = createBaseMaterial(getAttunementState().grantsMaterialBonuses());
      return createStatsForMaterial(stats, baseMaterial);
    }

    private IEquipmentStats createStatsForMaterial(IEquipmentStats stats, BaseMaterial baseMaterial) {
      if (stats instanceof IArmourStats) {
        return new ProxyArmourStats((IArmourStats) stats, baseMaterial);
      }
      if (stats instanceof IWeaponStats) {
        return new ProxyWeaponStats((IWeaponStats) stats, baseMaterial, modifiers);
      }
      return stats;
    }

    private BaseMaterial createBaseMaterial(boolean allowMaterialBonuses) {
      if (Variable == template.getComposition() && allowMaterialBonuses) {
        return new ReactiveBaseMaterial(material);
      } else {
        return new InertBaseMaterial();
      }
    }
  }
}