package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.impl.character.model.stats.ProxyArmourStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ProxyArtifactStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ProxyShieldStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ProxyWeaponStats;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.util.IProxy;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class EquipmentItem implements IEquipmentItem {

  private final Set<IEquipmentStats> printedStats = new HashSet<IEquipmentStats>();
  private final ChangeControl changeControl = new ChangeControl();
  private final IEquipmentTemplate template;
  private final IExaltedRuleSet ruleSet;
  private final MagicalMaterial material;

  public EquipmentItem(IEquipmentTemplate template, IExaltedRuleSet ruleSet, MagicalMaterial material) {
    if (template.getComposition() == MaterialComposition.Variable && material == null) {
      throw new MissingMaterialException("Variable material items must be created with material."); //$NON-NLS-1$
    }
    this.template = template;
    this.ruleSet = ruleSet;
    this.material = material != null ? material : template.getMaterial();
    Collections.addAll(printedStats, template.getStats(ruleSet));
  }

  public String getDescription() {
    return template.getDescription();
  }

  public IEquipmentStats[] getStats() {
    IEquipmentStats[] views = getViews();
    if (template.getComposition() != MaterialComposition.Variable) {
      return views;
    }
    return ArrayUtilities.transform(views, IEquipmentStats.class, new ITransformer<IEquipmentStats, IEquipmentStats>() {
      public IEquipmentStats transform(IEquipmentStats input) {
        if (input instanceof IArmourStats) {
          return new ProxyArmourStats((IArmourStats) input, material, ruleSet);
        }
        if (input instanceof IWeaponStats) {
          return new ProxyWeaponStats((IWeaponStats) input, material, ruleSet);
        }
        if (input instanceof IArtifactStats)
          return new ProxyArtifactStats((IArtifactStats) input, material, ruleSet);
        return new ProxyShieldStats((IShieldStats) input, material, ruleSet);
      }
    });
  }

  private IEquipmentStats[] getViews() {
    IEquipmentStats[] statsArray = template.getStats(ruleSet);
    if (ExaltedRuleSet.SecondEdition != ruleSet) {
      return statsArray;
    }
    List<IEquipmentStats> views = new ArrayList<IEquipmentStats>();
    for (IEquipmentStats stats : statsArray) {
      if (stats instanceof IWeaponStats) {
        Collections.addAll(views, ((IWeaponStats) stats).getViews());
      }
      else if (stats instanceof IArtifactStats)
    	Collections.addAll(views, ((IArtifactStats)stats).getViews());
      else {
        views.add(stats);
      }
    }
    return views.toArray(new IEquipmentStats[views.size()]);
  }

  public String getTemplateId() {
    return template.getName();
  }

  public MagicalMaterial getMaterial() {
    return material;
  }

  public MaterialComposition getMaterialComposition() {
    return template.getComposition();
  }
  
  @Override
  public ArtifactAttuneType getAttunementState() {
  	for (IEquipmentStats stats : getStats())
  		if (stats instanceof IArtifactStats)
  			if (isPrintEnabled(stats))
  				return ((IArtifactStats)stats).getAttuneType();
  	return ArtifactAttuneType.Unattuned;
  }

  @SuppressWarnings("unchecked")
  public boolean isPrintEnabled(IEquipmentStats stats) {
    if (stats instanceof IProxy<?>) {
      stats = ((IProxy<? extends IEquipmentStats>) stats).getUnderlying();
    }

    return printedStats.contains(stats);
  }

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
    }
    else {
      printedStats.remove(stats);
    }
    changeControl.fireChangedEvent();
  }

  public void setUnprinted() {
    printedStats.clear();
    changeControl.fireChangedEvent();
  }

  public void setPrinted(String printedStatId) {
    for (IEquipmentStats view : getViews()) {
      if (view.getId().equals(printedStatId)) {
        setPrintEnabled(view, true);
        return;
      }
    }
  }

  public void addChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  public void removeChangeListener(IChangeListener listener) {
    changeControl.removeChangeListener(listener);
  }
}