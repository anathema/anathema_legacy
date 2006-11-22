package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

import com.db4o.query.Predicate;

public class EquipmentAdditionalModel extends AbstractEquipmentAdditionalModel {
  private final IEquipmentTemplateProvider equipmentTemplateProvider;
  private final MagicalMaterial defaultMaterial;
  private final List<IEquipmentItem> naturalWeaponItems = new ArrayList<IEquipmentItem>();

  public EquipmentAdditionalModel(
      MagicalMaterial defaultMaterial,
      IArmourStats naturalArmour,
      IEquipmentTemplateProvider equipmentTemplateProvider,
      IExaltedRuleSet ruleSet,
      IEquipmentTemplate... naturalWeapons) {
    super(ruleSet, naturalArmour);
    this.defaultMaterial = defaultMaterial;
    this.equipmentTemplateProvider = equipmentTemplateProvider;
    for (IEquipmentTemplate template : naturalWeapons) {
      if (template == null) {
        continue;
      }
      naturalWeaponItems.add(addEquipmentObjectFor(template, null));
    }
  }

  public IEquipmentItem[] getNaturalWeapons() {
    return naturalWeaponItems.toArray(new IEquipmentItem[naturalWeaponItems.size()]);
  }

  public boolean canBeRemoved(IEquipmentItem item) {
    return !naturalWeaponItems.contains(item);
  }

  public String[] getAvailableTemplateIds() {
    final Set<String> idSet = new HashSet<String>();
    equipmentTemplateProvider.queryContainer(new Predicate<IEquipmentTemplate>() {
      @Override
      public boolean match(IEquipmentTemplate candidate) {
        if (candidate.getStats(getRuleSet()).length > 0) {
          idSet.add(candidate.getName());
        }
        else {
          for (IExaltedRuleSet rules : ExaltedRuleSet.values()) {
            if (candidate.getStats(rules).length > 0) {
              return false;
            }
          }
          idSet.add(candidate.getName());
        }
        return false;
      }
    });
    return idSet.toArray(new String[idSet.size()]);
  }

  @Override
  protected IEquipmentTemplate loadEquipmentTemplate(String templateId) {
    return equipmentTemplateProvider.loadTemplate(templateId);
  }

  @Override
  protected IEquipmentItem getSpecialManagedItem(String templateId) {
    for (IEquipmentItem item : naturalWeaponItems) {
      if (templateId.equals(item.getTemplateId())) {
        return item;
      }
    }
    return null;
  }

  public MagicalMaterial getDefaultMaterial() {
    return defaultMaterial;
  }
}