package net.sf.anathema.character.equipment.impl.creation.model;

import java.util.EnumMap;
import java.util.Map;

import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTag;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTagsModel;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

public class WeaponTagsModel implements IWeaponTagsModel {

  private final Map<WeaponTag, BooleanValueModel> selectedMap = new EnumMap<WeaponTag, BooleanValueModel>(
      WeaponTag.class);
  private final Map<WeaponTag, BooleanValueModel> enabledMap = new EnumMap<WeaponTag, BooleanValueModel>(
      WeaponTag.class);
  private final WeaponTag[] rangedWeaponTags = new WeaponTag[] {
      WeaponTag.BowType,
      WeaponTag.Thrown,
      WeaponTag.FlameType };
  private final IBooleanValueChangedListener updateRangeEnabledListener = new IBooleanValueChangedListener() {
    public void valueChanged(boolean newValue) {
      setTagsRangedCombatStyle();
    }
  };

  public WeaponTagsModel() {
    for (WeaponTag tag : WeaponTag.values()) {
      selectedMap.put(tag, new BooleanValueModel(false));
      enabledMap.put(tag, new BooleanValueModel(true));
    }
    for (WeaponTag rangedTag : rangedWeaponTags) {
      getSelectedModel(rangedTag).addChangeListener(updateRangeEnabledListener);
    }
  }

  public void setTagsRangedCombatStyle() {
    int selectedCount = getSelectedRangedWeaponTagCount();
    if (selectedCount == 0) {
      setAllRangedWeaponTagsEnabled(true);
    }
    else {
      for (WeaponTag tag : rangedWeaponTags) {
        getEnabledModel(tag).setValue(isSelected(tag));
      }
    }
  }

  private void setAllRangedWeaponTagsEnabled(boolean enabled) {
    for (WeaponTag tag : rangedWeaponTags) {
      getEnabledModel(tag).setValue(enabled);
    }
  }

  public void setTagsCloseCombatStyle() {
    setAllRangedWeaponTagsEnabled(false);
  }

  public int getSelectedRangedWeaponTagCount() {
    int selectionCount = 0;
    for (WeaponTag tag : rangedWeaponTags) {
      if (isSelected(tag)) {
        selectionCount++;
      }
    }
    return selectionCount;
  }

  private boolean isSelected(WeaponTag tag) {
    return getSelectedModel(tag).getValue();
  }

  public IWeaponTag[] getAllTags() {
    return WeaponTag.values();
  }

  public BooleanValueModel getSelectedModel(IWeaponTag tag) {
    return selectedMap.get(tag);
  }

  public BooleanValueModel getEnabledModel(IWeaponTag tag) {
    return enabledMap.get(tag);
  }
}