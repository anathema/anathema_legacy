package net.sf.anathema.character.equipment.impl.creation.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTag;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponTagsModel;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

public class WeaponTagsModel implements IWeaponTagsModel {

  private final Map<WeaponTag, BooleanValueModel> enabledMap = new EnumMap<WeaponTag, BooleanValueModel>(
      WeaponTag.class);
  private final Map<WeaponTag, BooleanValueModel> selectedMap = new EnumMap<WeaponTag, BooleanValueModel>(
      WeaponTag.class);

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
    for (WeaponTag rangedTag : WeaponTag.getRangedWeaponTypeTags()) {
      getSelectedModel(rangedTag).addChangeListener(updateRangeEnabledListener);
    }
  }

  public IWeaponTag[] getAllTags() {
    return WeaponTag.values();
  }

  public BooleanValueModel getEnabledModel(IWeaponTag tag) {
    return enabledMap.get(tag);
  }

  public BooleanValueModel getSelectedModel(IWeaponTag tag) {
    return selectedMap.get(tag);
  }

  public IWeaponTag[] getSelectedTags() {
    List<IWeaponTag> tags = new ArrayList<IWeaponTag>();
    for (WeaponTag tag : selectedMap.keySet()) {
      if (isSelected(tag)) {
        tags.add(tag);
      }
    }
    return tags.toArray(new IWeaponTag[tags.size()]);
  }

  private boolean isSelected(WeaponTag tag) {
    return getSelectedModel(tag).getValue();
  }

  private void setAllCloseCombatTagsEnabled(boolean enabled) {
    for (WeaponTag tag : WeaponTag.getMeleeWeaponTags()) {
      setEnabled(enabled, tag);
    }
  }

  private void setAllRangedWeaponTagsEnabled(boolean enabled) {
    if (!enabled) {
      for (WeaponTag tag : WeaponTag.getRangedWeaponTypeTags()) {
        getSelectedModel(tag).setValue(false);
      }
    }
    for (WeaponTag tag : WeaponTag.getRangedWeaponTypeTags()) {
      getEnabledModel(tag).setValue(enabled);
    }
    for (WeaponTag tag : WeaponTag.getRangedWeaponTags()) {
      setEnabled(enabled, tag);
    }
  }

  private void setEnabled(boolean enabled, WeaponTag tag) {
    getEnabledModel(tag).setValue(enabled);
    if (!enabled) {
      getSelectedModel(tag).setValue(false);
    }
  }

  public void setTagsCloseCombatStyle() {
    setAllRangedWeaponTagsEnabled(false);
    setAllCloseCombatTagsEnabled(true);
  }

  public void setTagsRangedCombatStyle() {
    setAllCloseCombatTagsEnabled(false);
    if (isRangedWeaponTagSelected()) {
      for (WeaponTag tag : WeaponTag.getRangedWeaponTypeTags()) {
        getEnabledModel(tag).setValue(isSelected(tag));
      }
    }
    else {
      setAllRangedWeaponTagsEnabled(true);
    }
  }

  @Override
  public boolean isRangedWeaponTagSelected() {
    int selectionCount = 0;
    for (WeaponTag tag : WeaponTag.getRangedWeaponTypeTags()) {
      if (isSelected(tag)) {
        selectionCount++;
      }
    }
    return selectionCount == 1;
  }
}