package net.sf.anathema.character.equipment.impl.character.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.sf.anathema.character.equipment.CharacterMaterialRules;
import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.IEquipmentCharacterDataProvider;
import net.sf.anathema.character.equipment.character.IEquipmentCharacterOptionProvider;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentPrintModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.equipment.impl.character.model.print.EquipmentPrintModel;
import net.sf.anathema.character.equipment.impl.character.model.stats.CharacterStatsModifiers;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IGenericSpecialtyContext;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ICollectionListener;
import net.sf.anathema.lib.lang.ArrayUtilities;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EquipmentAdditionalModel extends AbstractAdditionalModelAdapter implements IEquipmentCharacterOptionProvider, IEquipmentAdditionalModel {
  private final IEquipmentTemplateProvider equipmentTemplateProvider;
  private final ICharacterType characterType;
  private final MagicalMaterial defaultMaterial;
  private final List<IEquipmentItem> naturalWeaponItems = new ArrayList<>();
  private final Table<IEquipmentItem, IEquipmentStats, List<IEquipmentStatsOption>> optionsTable = HashBasedTable.create();
  private final IEquipmentCharacterDataProvider dataProvider;
  private final Announcer<IChangeListener> modelChangeControl = Announcer.to(IChangeListener.class);
  private final Announcer<ICollectionListener> equipmentItemControl = Announcer.to(ICollectionListener.class);
  private final EquipmentCollection equipmentItems = new EquipmentCollection();
  private final IEquipmentPrintModel printModel;
  private final IChangeListener itemChangePropagator = new IChangeListener() {
    @Override
    public void changeOccurred() {
      fireModelChanged();
    }
  };

  public EquipmentAdditionalModel(ICharacterType characterType, IArmourStats naturalArmour,
                                  IEquipmentTemplateProvider equipmentTemplateProvider, IGenericSpecialtyContext context,
                                  IEquipmentCharacterDataProvider dataProvider,
                                  IEquipmentTemplate... naturalWeapons) {
    this.printModel = new EquipmentPrintModel(this, naturalArmour);
    this.characterType = characterType;
    this.defaultMaterial = evaluateDefaultMaterial();
    this.equipmentTemplateProvider = equipmentTemplateProvider;
    this.dataProvider = dataProvider;
    for (IEquipmentTemplate template : naturalWeapons) {
      if (template == null) {
        continue;
      }
      IEquipmentItem item = createItem(template, null);
      naturalWeaponItems.add(item);
    }
    context.addSpecialtyListChangeListener(new SpecialtyPrintRemover(dataProvider));
  }

  @Override
  public IEquipmentCharacterDataProvider getCharacterDataProvider() {
    return dataProvider;
  }

  @Override
  public IEquipmentCharacterOptionProvider getCharacterOptionProvider() {
    return this;
  }

  @Override
  public ICharacterStatsModifiers createStatsModifiers(IGenericCharacter character) {
    return CharacterStatsModifiers.extractFromCharacter(character);
  }

  private MagicalMaterial evaluateDefaultMaterial() {
    MagicalMaterial defaultMaterial = new CharacterMaterialRules().getDefault(characterType);
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
    return equipmentTemplateProvider.getAllAvailableTemplateIds();
  }

  private IEquipmentTemplate loadEquipmentTemplate(String templateId) {
    return equipmentTemplateProvider.loadTemplate(templateId);
  }

  private IEquipmentItem getSpecialManagedItem(String templateId) {
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
      list = new ArrayList<>();
      optionsTable.put(item, stats, list);
    }
    return list;
  }

  @Override
  public void enableStatOption(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option) {
    if (item == null || stats == null) return;
    getOptionsList(item, stats).add(option);
    fireModelChanged();
  }

  @Override
  public void disableStatOption(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option) {
    if (item == null || stats == null) return;
    getOptionsList(item, stats).remove(option);
    fireModelChanged();
  }

  @Override
  public boolean isStatOptionEnabled(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option) {
    if (item == null || stats == null) {
      return false;
    }
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
    if (stats == null) {
      return new IEquipmentStatsOption[0];
    }
    List<IEquipmentItem> itemList = new ArrayList<>();
    itemList.addAll(naturalWeaponItems);
    Collections.addAll(itemList, getEquipmentItems());
    for (IEquipmentItem item : itemList) {
      for (IEquipmentStats stat : item.getStats()) {
        if (stats.equals(stat)) {
          return getEnabledStatOptions(item, stat);
        }
      }
    }
    return new IEquipmentStatsOption[0];
  }

  @Override
  public boolean transferOptions(IEquipmentItem fromItem, IEquipmentItem toItem) {
    boolean transferred = false;
    if (fromItem != null && toItem != null) {
      for (IEquipmentStats fromStats : fromItem.getStats()) {
        List<IEquipmentStatsOption> specialtyList = optionsTable.remove(fromItem, fromStats);
        boolean printCheckboxEnabled = fromItem.isPrintEnabled(fromStats);
        IEquipmentStats toStats = toItem.getStat(fromStats.getId());
      
        if (toStats != null) {
          transferred = true;
          if( specialtyList != null) {
            optionsTable.put(toItem, toStats, specialtyList);
          }
          toItem.setPrintEnabled(toStats, printCheckboxEnabled);
        }
      }
    }
    return transferred;
  }

  @Override
  public IEquipmentPrintModel getPrintModel() {
    return printModel;
  }

  @Override
  public AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Miscellaneous;
  }

  @Override
  public String getTemplateId() {
    return IEquipmentAdditionalModelTemplate.ID;
  }

  @Override
  public IEquipmentItem[] getEquipmentItems() {
    return equipmentItems.asArray();
  }

  @Override
  public IEquipmentItem addEquipmentObjectFor(String templateId, MagicalMaterial material) {
    IEquipmentTemplate template = loadEquipmentTemplate(templateId);
    if (template == null) {
      return getSpecialManagedItem(templateId);
    }
    return addEquipmentObjectFor(template, material);
  }

  private IEquipmentItem addEquipmentObjectFor(IEquipmentTemplate template, MagicalMaterial material) {
    IEquipmentItem item = createItem(template, material);
    equipmentItems.add(item);
    announceItemAndListenForChanges(item);
    return item;
  }

  private IEquipmentItem createItem(IEquipmentTemplate template, MagicalMaterial material) {
    return new EquipmentItem(template, null, null, material, getCharacterDataProvider(), equipmentItems);
  }

  @Override
  public void removeItem(IEquipmentItem item) {
    equipmentItems.remove(item);
    equipmentItemControl.announce().itemRemoved(item);
    item.removeChangeListener(itemChangePropagator);
    fireModelChanged();
  }
  
  @Override
  public void updateItem(IEquipmentItem item) {
    fireModelChanged();
  }

  private void fireModelChanged() {
    modelChangeControl.announce().changeOccurred();
  }

  @Override
  public void refreshItems() {
    for (IEquipmentItem item : equipmentItems) {
      if (canBeRemoved(item)) {
        IEquipmentItem refreshedItem = refreshItem(item);
        if (refreshedItem != null) {
          refreshedItem.setPersonalization(item);
          getCharacterOptionProvider().transferOptions(item, refreshedItem);
          announceItemAndListenForChanges(refreshedItem);
        }
      }
    }
  }

  private void announceItemAndListenForChanges(IEquipmentItem refreshedItem) {
    equipmentItemControl.announce().itemAdded(refreshedItem);
    refreshedItem.addChangeListener(itemChangePropagator);
    fireModelChanged();
  }

  private IEquipmentItem refreshItem(IEquipmentItem item) {
    String templateId = item.getTemplateId();
    MagicalMaterial material = item.getMaterial();
    removeItem(item);
    return addEquipmentObjectFor(templateId, material);
  }

  @Override
  public void addEquipmentObjectListener(ICollectionListener<IEquipmentItem> listener) {
    equipmentItemControl.addListener(listener);
  }

  @Override
  public MaterialComposition getMaterialComposition(String templateId) {
    IEquipmentTemplate template = loadEquipmentTemplate(templateId);
    return template.getComposition();
  }

  @Override
  public MagicalMaterial getMagicalMaterial(String templateId) {
    IEquipmentTemplate template = loadEquipmentTemplate(templateId);
    return template.getMaterial();
  }

  @Override
  public void addChangeListener(IChangeListener listener) {
    modelChangeControl.addListener(listener);
  }

  @Override
  public int getMotesExpended() {
    int total = 0;
    for (IEquipmentItem item : equipmentItems) {
      for (IEquipmentStats stats : item.getStats())
        if (stats instanceof IArtifactStats && item.getAttunementState() == ((IArtifactStats) stats).getAttuneType())
          total += ((IArtifactStats) stats).getAttuneCost();
    }
    return total;
  }

  private class SpecialtyPrintRemover implements IChangeListener {
    private final IEquipmentCharacterDataProvider dataProvider;

    public SpecialtyPrintRemover(IEquipmentCharacterDataProvider dataProvider) {
      this.dataProvider = dataProvider;
    }

    @Override
    public void changeOccurred() {
      for (IEquipmentItem item : getEquipmentItems())
        for (IEquipmentStats stats : item.getStats()) {
          List<IEquipmentStatsOption> list = optionsTable.get(item, stats);
          if (list == null) {
            return;
          }
          List<IEquipmentStatsOption> optionsList = new ArrayList<>(list);
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
  }

	@Override
	public String[] getAllAvailableTemplateIds() {
		return equipmentTemplateProvider.getAllAvailableTemplateIds();
	}
	
	@Override
	public IEquipmentTemplate loadTemplate(String templateId) {
		return equipmentTemplateProvider.loadTemplate(templateId);
	}
}