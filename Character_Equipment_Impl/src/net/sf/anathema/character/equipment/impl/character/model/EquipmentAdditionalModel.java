package net.sf.anathema.character.equipment.impl.character.model;

import com.db4o.query.Predicate;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.character.IEquipmentCharacterDataProvider;
import net.sf.anathema.character.equipment.character.IEquipmentCharacterOptionProvider;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.collection.Table;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.lang.ArrayUtilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EquipmentAdditionalModel extends AbstractEquipmentAdditionalModel implements IEquipmentCharacterOptionProvider {
  private final IEquipmentTemplateProvider equipmentTemplateProvider;
  private final ICharacterType characterType;
  private final MagicalMaterial defaultMaterial;
  private final List<IEquipmentItem> naturalWeaponItems = new ArrayList<IEquipmentItem>();
  private final Table<IEquipmentItem, IEquipmentStats, List<IEquipmentStatsOption>> optionsTable = new Table<IEquipmentItem, IEquipmentStats, List<IEquipmentStatsOption>>();
  private final IEquipmentCharacterDataProvider dataProvider;

  public EquipmentAdditionalModel(ICharacterType characterType, IArmourStats naturalArmour,
                                  IEquipmentTemplateProvider equipmentTemplateProvider, IExaltedRuleSet ruleSet,
                                  ICharacterModelContext context, final IEquipmentCharacterDataProvider dataProvider,
                                  IEquipmentTemplate... naturalWeapons) {
    super(ruleSet, naturalArmour);
    this.characterType = characterType;
    this.defaultMaterial = evaluateDefaultMaterial();
    this.equipmentTemplateProvider = equipmentTemplateProvider;
    this.dataProvider = dataProvider;
    for (IEquipmentTemplate template : naturalWeapons) {
      if (template == null) {
        continue;
      }
      final IEquipmentItem item = new EquipmentItem(template, ruleSet, null, getCharacterDataProvider());
      naturalWeaponItems.add(initItem(item));
    }

    context.getSpecialtyContext().addSpecialtyListChangeListener(new IChangeListener() {

      @Override
      public void changeOccurred() {
        for (IEquipmentItem item : getEquipmentItems())
          for (IEquipmentStats stats : item.getStats()) {
            List<IEquipmentStatsOption> list = optionsTable.get(item, stats);
            if (list == null) {
              return;
            }
            List<IEquipmentStatsOption> optionsList = new ArrayList<IEquipmentStatsOption>(list);
            for (IEquipmentStatsOption option : optionsList) {
              if (!characterStillHasCorrespondingSpecialty(option)) {
                disableStatOption(item, stats, option);
              }
            }
          }
      }

      private boolean characterStillHasCorrespondingSpecialty(IEquipmentStatsOption option) {
        try {
          AbilityType trait = AbilityType.valueOf(option.getType());
          INamedGenericTrait[] specialties = dataProvider.getSpecialties(trait);
          ArrayUtilities.indexOf(specialties, option.getUnderlyingTrait());
          return true;
        } catch (IllegalArgumentException e) {
          return false;
        }
      }
    });
  }

  @Override
  public IEquipmentCharacterDataProvider getCharacterDataProvider() {
    return dataProvider;
  }

  @Override
  public IEquipmentCharacterOptionProvider getCharacterOptionProvider() {
    return this;
  }

  private MagicalMaterial evaluateDefaultMaterial() {
    MagicalMaterial defaultMaterial = MagicalMaterial.getDefault(characterType);
    if (defaultMaterial == null) {
      return MagicalMaterial.Orichalcum;
    }
    return defaultMaterial;
  }

  @Override
  public IEquipmentItem[] getNaturalWeapons() {
    return naturalWeaponItems.toArray(new IEquipmentItem[naturalWeaponItems.size()]);
  }

  @Override
  public boolean canBeRemoved(IEquipmentItem item) {
    return !naturalWeaponItems.contains(item);
  }

  @Override
  public String[] getAvailableTemplateIds() {
    final Set<String> idSet = new HashSet<String>();
    equipmentTemplateProvider.queryContainer(new Predicate<IEquipmentTemplate>() {
      private static final long serialVersionUID = 1L;

      @Override
      public boolean match(IEquipmentTemplate candidate) {
        if (candidate.getStats(getRuleSet()).length > 0) {
          idSet.add(candidate.getName());
        } else {
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

  @Override
  public MagicalMaterial getDefaultMaterial() {
    return defaultMaterial;
  }

  private List<IEquipmentStatsOption> getOptionsList(IEquipmentItem item, IEquipmentStats stats) {
    List<IEquipmentStatsOption> list = optionsTable.get(item, stats);
    if (list == null) {
      list = new ArrayList<IEquipmentStatsOption>();
      optionsTable.add(item, stats, list);
    }
    return list;
  }

  @Override
  public void enableStatOption(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option) {
    if (item == null || stats == null) return;
    getOptionsList(item, stats).add(option);
    modelChangeControl.fireChangedEvent();
  }

  @Override
  public void disableStatOption(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option) {
    if (item == null || stats == null) return;
    getOptionsList(item, stats).remove(option);
    modelChangeControl.fireChangedEvent();
  }

  @Override
  public boolean isStatOptionEnabled(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option) {
    if (item == null || stats == null) return false;
    return getOptionsList(item, stats).contains(option);
  }

  @Override
  public IEquipmentStatsOption[] getEnabledStatOptions(IEquipmentItem item, IEquipmentStats stats) {
    if (item == null || stats == null) return new IEquipmentStatsOption[0];
    List<IEquipmentStatsOption> options = getOptionsList(item, stats);
    return options.toArray(new IEquipmentStatsOption[options.size()]);
  }

  @Override
  public IEquipmentStatsOption[] getEnabledStatOptions(IEquipmentStats stats) {
    if (stats == null) return new IEquipmentStatsOption[0];

    List<IEquipmentItem> itemList = new ArrayList<IEquipmentItem>();
    itemList.addAll(naturalWeaponItems);
    Collections.addAll(itemList, getEquipmentItems());
    for (IEquipmentItem item : itemList)
      for (IEquipmentStats stat : item.getStats())
        if (stats.equals(stat)) return getEnabledStatOptions(item, stat);
    return new IEquipmentStatsOption[0];
  }

  @Override
  public boolean transferOptions(IEquipmentItem fromItem, IEquipmentItem toItem) {
    if (fromItem == null || toItem == null) {
      return false;
    }
    boolean transferred = false;
    for (IEquipmentStats fromStats : fromItem.getStats()) {
      List<IEquipmentStatsOption> optionList = optionsTable.get(fromItem, fromStats);
      optionsTable.add(fromItem, fromStats, null);
      IEquipmentStats toStats = toItem.getStat(fromStats.getId());
      if (toStats != null && optionList != null) {
        optionsTable.add(toItem, toStats, optionList);
        transferred = true;
      }
    }
    return transferred;
  }
}