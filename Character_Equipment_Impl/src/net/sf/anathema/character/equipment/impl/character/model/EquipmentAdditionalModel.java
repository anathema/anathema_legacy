package net.sf.anathema.character.equipment.impl.character.model;

import com.db4o.query.Predicate;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.character.EquipmentCharacterDataProvider;
import net.sf.anathema.character.equipment.character.IEquipmentCharacterDataProvider;
import net.sf.anathema.character.equipment.character.IEquipmentCharacterOptionProvider;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.collection.Table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EquipmentAdditionalModel extends AbstractEquipmentAdditionalModel 
	implements IEquipmentCharacterOptionProvider {
  private final IEquipmentTemplateProvider equipmentTemplateProvider;
  private final ICharacterType characterType;
  private final MagicalMaterial defaultMaterial;
  private final List<IEquipmentItem> naturalWeaponItems = new ArrayList<IEquipmentItem>();
  private final Table<IEquipmentItem, IEquipmentStats, List<IEquipmentStatsOption>> options =
		    new Table<IEquipmentItem, IEquipmentStats, List<IEquipmentStatsOption>>();
  private final IEquipmentCharacterDataProvider provider;
  private final ICharacterListening changeListener;

  public EquipmentAdditionalModel(
		  ICharacterType characterType,
          IArmourStats naturalArmour,
          IEquipmentTemplateProvider equipmentTemplateProvider,
          IExaltedRuleSet ruleSet,
          ICharacterModelContext context,
          IEquipmentTemplate... naturalWeapons) {
	super(ruleSet, naturalArmour);
    this.characterType = characterType;
    this.defaultMaterial = evaluateDefaultMaterial();
    this.equipmentTemplateProvider = equipmentTemplateProvider;
    this.provider = new EquipmentCharacterDataProvider(context, this);
    this.changeListener = context.getCharacterListening();
    for (IEquipmentTemplate template : naturalWeapons) {
      if (template == null) {
        continue;
      }
      final IEquipmentItem item = new EquipmentItem(template, ruleSet, null);
      naturalWeaponItems.add(initItem(item));
    }
  }
  
  public IEquipmentCharacterDataProvider getCharacterDataProvider() {
	  return provider;
  }

  private MagicalMaterial evaluateDefaultMaterial() {
    MagicalMaterial defaultMaterial = MagicalMaterial.getDefault(characterType);
    if (defaultMaterial == null) {
      return MagicalMaterial.Orichalcum;
    }
    return defaultMaterial;
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

  public MagicalMaterial getDefaultMaterial() {
    return defaultMaterial;
  }
	
  private List<IEquipmentStatsOption> getOptionsList(IEquipmentItem item, IEquipmentStats stats)
  {
		List<IEquipmentStatsOption> list = options.get(item, stats);
		if (list == null)
		{
			list = new ArrayList<IEquipmentStatsOption>();
			options.add(item, stats, list);
		}
		return list;
  }

  @Override
  public void enableStatOption(IEquipmentItem item, IEquipmentStats stats,
			IEquipmentStatsOption option) {
		if (item == null || stats == null) return;
		getOptionsList(item, stats).add(option);
		modelChangeControl.fireChangedEvent();
  }

  @Override
  public void disableStatOption(IEquipmentItem item, IEquipmentStats stats,
			IEquipmentStatsOption option) {
		if (item == null || stats == null) return;
		getOptionsList(item, stats).remove(option);
		modelChangeControl.fireChangedEvent();
  }

  @Override
  public boolean isStatOptionEnabled(IEquipmentItem item,
			IEquipmentStats stats, IEquipmentStatsOption option) {
		if (item == null || stats == null) return false;
		return getOptionsList(item, stats).contains(option);
  }

  @Override
  public IEquipmentStatsOption[] getEnabledStatOptions(IEquipmentItem item,
			IEquipmentStats stats) {
		if (item == null || stats == null) return new IEquipmentStatsOption[0];
		return getOptionsList(item, stats).toArray(new IEquipmentStatsOption[0]);
  }
  
  @Override
  public IEquipmentStatsOption[] getEnabledStatOptions(IEquipmentStats stats) {
		if (stats == null) return new IEquipmentStatsOption[0];
		
		List<IEquipmentItem> itemList = new ArrayList<IEquipmentItem>();
		itemList.addAll(naturalWeaponItems);
		for (IEquipmentItem item : getEquipmentItems())
			itemList.add(item);
		for (IEquipmentItem item : itemList)
			for (IEquipmentStats stat : item.getStats())
				if (stats.equals(stat))
					return getEnabledStatOptions(item, stat);			
		return new IEquipmentStatsOption[0];
  }

  @Override
  public void addCharacterChangedListener(ICharacterChangeListener listener) {
	changeListener.addChangeListener(listener);
  }
}