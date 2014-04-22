package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.character.equipment.creation.presenter.IWeaponTag;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTagsModel;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeaponTagsModel implements IWeaponTagsModel {

  private final Map<IWeaponTag, BooleanValueModel> enabledMap = new HashMap<>();
  private final Map<IWeaponTag, BooleanValueModel> selectedMap = new HashMap<>();

  public WeaponTagsModel() {
    for (WeaponTag tag : WeaponTag.values()) {
      selectedMap.put(tag, new BooleanValueModel(false));
      enabledMap.put(tag, new BooleanValueModel(true));
    }
    IBooleanValueChangedListener updateRangeEnabledListener = new IBooleanValueChangedListener() {
      @Override
      public void valueChanged(boolean newValue) {
        setTagsRangedCombatStyle();
      }
    };
    for (WeaponTag rangedTag : WeaponTag.getRangedWeaponTypeTags()) {
      getSelectedModel(rangedTag).addChangeListener(updateRangeEnabledListener);
    }
  }

  @Override
  public IWeaponTag[] getAllTags() {
    return WeaponTag.values();
  }

  @Override
  public BooleanValueModel getEnabledModel(IWeaponTag tag) {
    return enabledMap.get(tag);
  }

  @Override
  public BooleanValueModel getSelectedModel(IWeaponTag tag) {
    return selectedMap.get(tag);
  }

  @Override
  public IWeaponTag[] getSelectedTags() {
    List<IWeaponTag> tags = new ArrayList<>();
    for (IWeaponTag tag : selectedMap.keySet()) {
      if (isSelected(tag)) {
        tags.add(tag);
      }
    }
    return tags.toArray(new IWeaponTag[tags.size()]);
  }

  private boolean isSelected(IWeaponTag tag) {
    return getSelectedModel(tag).getValue();
  }

  private void setAllCloseCombatTagsEnabled(boolean enabled) {
    for (WeaponTag tag : WeaponTag.getMeleeWeaponTags()) {
      setEnabled(enabled, tag);
    }
  }

  private void setAllRangedWeaponTagsEnabled(boolean enabled) {
    for (WeaponTag tag : WeaponTag.getRangedWeaponTypeTags()) {
      setEnabled(enabled, tag);
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

  @Override
  public void setTagsCloseCombatStyle() {
    setAllRangedWeaponTagsEnabled(false);
    setAllCloseCombatTagsEnabled(true);
  }

  @Override
  public void setTagsRangedCombatStyle() {
    setAllCloseCombatTagsEnabled(false);
    if (isRangedTypeTagSelected()) {
      for (WeaponTag tag : WeaponTag.getRangedWeaponTypeTags()) {
        getEnabledModel(tag).setValue(isSelected(tag));
      }
    } else {
      setAllRangedWeaponTagsEnabled(true);
    }
  }

  @Override
  public boolean isRangedTypeTagSelected() {
    int selectionCount = 0;
    for (WeaponTag tag : WeaponTag.getRangedWeaponTypeTags()) {
      if (isSelected(tag)) {
        selectionCount++;
      }
    }
    return selectionCount == 1;
  }

  @Override
  public boolean isThrownTypeTagSelected() {
    return isSelected(WeaponTag.Thrown);
  }

  @Override
  public boolean isThrownWeaponTagSelected() {
    for (WeaponTag tag : WeaponTag.getThrownWeaponTags()) {
      if (isSelected(tag)) {
        return true;
      }
    }
    return false;
  }
}