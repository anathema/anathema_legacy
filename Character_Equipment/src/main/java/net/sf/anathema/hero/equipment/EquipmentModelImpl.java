package net.sf.anathema.hero.equipment;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.EquipmentOptionsProvider;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.equipment.item.model.gson.GsonEquipmentDatabase;
import net.sf.anathema.character.framework.library.HeroStatsModifiers;
import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.hero.equipment.model.CharacterStatsModifiers;
import net.sf.anathema.hero.equipment.model.EquipmentCollection;
import net.sf.anathema.hero.equipment.model.EquipmentDirectAccess;
import net.sf.anathema.hero.equipment.model.EquipmentHeroEvaluatorImpl;
import net.sf.anathema.hero.equipment.model.EquipmentItem;
import net.sf.anathema.hero.equipment.model.MaterialRules;
import net.sf.anathema.hero.equipment.model.ReflectionMaterialRules;
import net.sf.anathema.hero.equipment.model.natural.DefaultNaturalSoak;
import net.sf.anathema.hero.equipment.model.natural.NaturalWeaponTemplate;
import net.sf.anathema.hero.equipment.sheet.content.stats.ArtifactStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IArmourStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.change.UnspecifiedChangeListener;
import net.sf.anathema.hero.sheet.pdf.content.stats.StatsModelFetcher;
import net.sf.anathema.hero.specialties.Specialty;
import net.sf.anathema.hero.spiritual.model.pool.EssencePoolModelFetcher;
import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.traits.model.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.types.AbilityType;
import net.sf.anathema.hero.traits.model.types.AttributeType;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ICollectionListener;
import net.sf.anathema.lib.util.Identifier;
import org.apache.commons.lang3.ArrayUtils;
import org.jmock.example.announcer.Announcer;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.sf.anathema.character.equipment.item.model.gson.GsonEquipmentDatabase.DATABASE_FOLDER;

public class EquipmentModelImpl implements EquipmentOptionsProvider, EquipmentModel {
  private final List<IEquipmentItem> naturalWeaponItems = new ArrayList<>();
  private final Table<IEquipmentItem, IEquipmentStats, List<IEquipmentStatsOption>> optionsTable = HashBasedTable.create();
  private final Announcer<ChangeListener> modelChangeControl = Announcer.to(ChangeListener.class);
  private final Announcer<ICollectionListener> equipmentItemControl = Announcer.to(ICollectionListener.class);
  private final EquipmentCollection equipmentItems = new EquipmentCollection();
  private IEquipmentTemplateProvider equipmentTemplateProvider;
  private final ChangeListener itemChangePropagator = this::fireModelChanged;
  private CharacterType characterType;
  private MagicalMaterial defaultMaterial;
  private EquipmentHeroEvaluator dataProvider;
  private IArmourStats naturalArmor;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    StatsModelFetcher.fetch(hero).addModifierFactory(this);
    Path dataBaseDirectory = environment.getDataFileProvider().getDataBaseDirectory(DATABASE_FOLDER);
    EquipmentDirectAccess access = new EquipmentDirectAccess(dataBaseDirectory);
    ObjectFactory objectFactory = environment.getObjectFactory();
    MaterialRules materialRules = new ReflectionMaterialRules(objectFactory);
    CharacterType characterType = hero.getTemplate().getTemplateType().getCharacterType();
    Trait stamina = TraitModelFetcher.fetch(hero).getTrait(AttributeType.Stamina);
    this.naturalArmor = new DefaultNaturalSoak(stamina, characterType);
    EquipmentHeroEvaluatorImpl dataProvider = new EquipmentHeroEvaluatorImpl(hero, materialRules);
    this.characterType = hero.getTemplate().getTemplateType().getCharacterType();
    this.defaultMaterial = evaluateDefaultMaterial(materialRules);
    this.equipmentTemplateProvider = new GsonEquipmentDatabase(access);
    this.dataProvider = dataProvider;
    IEquipmentItem item = createItem(new NaturalWeaponTemplate(), null);
    naturalWeaponItems.add(item);
    new SpecialtiesCollectionImpl(hero).addSpecialtyListChangeListener(new SpecialtyPrintRemover(dataProvider));
    EssencePoolModelFetcher.fetch(hero).addEssencePoolModifier(this);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    addChangeListener(new UnspecifiedChangeListener(announcer));
  }

  @Override
  public EquipmentHeroEvaluator getHeroEvaluator() {
    return dataProvider;
  }

  @Override
  public EquipmentOptionsProvider getOptionProvider() {
    return this;
  }

  @Override
  public IArmourStats getNaturalArmor() {
    return naturalArmor;
  }

  private MagicalMaterial evaluateDefaultMaterial(MaterialRules materialRules) {
    MagicalMaterial defaultMaterial = materialRules.getDefault(characterType);
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
    if (item == null || stats == null) {
      return;
    }
    getOptionsList(item, stats).add(option);
    fireModelChanged();
  }

  @Override
  public void disableStatOption(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option) {
    if (item == null || stats == null) {
      return;
    }
    getOptionsList(item, stats).remove(option);
    fireModelChanged();
  }

  @Override
  public boolean isStatOptionEnabled(IEquipmentItem item, IEquipmentStats stats, IEquipmentStatsOption option) {
    return item != null && stats != null && getOptionsList(item, stats).contains(option);
  }

  @Override
  public IEquipmentStatsOption[] getEnabledStatOptions(IEquipmentItem item, IEquipmentStats stats) {
    if (item == null || stats == null) {
      return new IEquipmentStatsOption[0];
    }
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
          if (specialtyList != null) {
            optionsTable.put(toItem, toStats, specialtyList);
          }
          toItem.setPrintEnabled(toStats, printCheckboxEnabled);
        }
      }
    }
    return transferred;
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
    return new EquipmentItem(template, null, null, material, getHeroEvaluator(), equipmentItems);
  }

  @Override
  public void removeItem(IEquipmentItem item) {
    equipmentItems.remove(item);
    announce().itemRemoved(item);
    item.removeChangeListener(itemChangePropagator);
    fireModelChanged();
  }

  @SuppressWarnings("unchecked")
  private ICollectionListener<IEquipmentItem> announce() {
    return equipmentItemControl.announce();
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
          getOptionProvider().transferOptions(item, refreshedItem);
          announceItemAndListenForChanges(refreshedItem);
        }
      }
    }
  }

  private void announceItemAndListenForChanges(IEquipmentItem refreshedItem) {
    announce().itemAdded(refreshedItem);
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

  public void addChangeListener(ChangeListener listener) {
    modelChangeControl.addListener(listener);
  }

  @Override
  public int getMotesExpended() {
    int total = 0;
    for (IEquipmentItem item : equipmentItems) {
      for (IEquipmentStats stats : item.getStats()) {
        if (stats instanceof ArtifactStats && item.getAttunementState() == ((ArtifactStats) stats).getAttuneType()) {
          total += ((ArtifactStats) stats).getAttuneCost();
        }
      }
    }
    return total;
  }

  @Override
  public HeroStatsModifiers createStatsModifiers(Hero hero) {
    return CharacterStatsModifiers.extractFromCharacter(hero);
  }

  private class SpecialtyPrintRemover implements ChangeListener {
    private final EquipmentHeroEvaluator dataProvider;

    public SpecialtyPrintRemover(EquipmentHeroEvaluator dataProvider) {
      this.dataProvider = dataProvider;
    }

    @Override
    public void changeOccurred() {
      for (IEquipmentItem item : getEquipmentItems()) {
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
    }

    private boolean characterStillHasCorrespondingSpecialty(IEquipmentStatsOption option) {
      AbilityType trait = AbilityType.valueOf(option.getType());
      Specialty[] specialties = dataProvider.getSpecialties(trait);
      return ArrayUtils.contains(specialties, option.getUnderlyingTrait());
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