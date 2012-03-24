package net.sf.anathema.character.equipment.impl.character.model;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentPrintModel;
import net.sf.anathema.character.equipment.impl.character.model.print.EquipmentPrintModel;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.collection.ICollectionListener;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEquipmentAdditionalModel extends AbstractAdditionalModelAdapter implements IEquipmentAdditionalModel {

  protected final ChangeControl modelChangeControl = new ChangeControl();
  private final GenericControl<ICollectionListener<IEquipmentItem>> equipmentItemControl = new GenericControl<ICollectionListener<IEquipmentItem>>();
  private final List<IEquipmentItem> equipmentItems = new ArrayList<IEquipmentItem>();
  private final IExaltedRuleSet ruleSet;
  private final IEquipmentPrintModel printModel;
  protected final IChangeListener itemChangePropagator = new IChangeListener() {
    @Override
    public void changeOccurred() {
      modelChangeControl.fireChangedEvent();
    }
  };

  public AbstractEquipmentAdditionalModel(final IExaltedRuleSet ruleSet, final IArmourStats naturalArmour) {
    this.ruleSet = ruleSet;
    this.printModel = new EquipmentPrintModel(this, naturalArmour);
  }

  @Override
  public IEquipmentPrintModel getPrintModel() {
    return printModel;
  }

  @Override
  public final AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Miscellaneous;
  }

  @Override
  public final String getTemplateId() {
    return IEquipmentAdditionalModelTemplate.ID;
  }

  @Override
  public final IEquipmentItem[] getEquipmentItems() {
    return equipmentItems.toArray(new IEquipmentItem[equipmentItems.size()]);
  }

  @Override
  public IEquipmentItem addEquipmentObjectFor(final String templateId, final MagicalMaterial material) {
    final IEquipmentTemplate template = loadEquipmentTemplate(templateId);
    if (template == null) {
      return getSpecialManagedItem(templateId);
    }
    return addEquipmentObjectFor(template, material);
  }

  protected abstract IEquipmentItem getSpecialManagedItem(String templateId);

  protected abstract IEquipmentTemplate loadEquipmentTemplate(String templateId);

  private IEquipmentItem addEquipmentObjectFor(final IEquipmentTemplate template, final MagicalMaterial material) {
    final IEquipmentItem item = new EquipmentItem(template, ruleSet, material, getCharacterDataProvider());
    equipmentItems.add(item);
    return initItem(item);
  }

  protected final IEquipmentItem initItem(final IEquipmentItem item) {
    equipmentItemControl.forAllDo(new IClosure<ICollectionListener<IEquipmentItem>>() {
      @Override
      public void execute(final ICollectionListener<IEquipmentItem> input) {
        input.itemAdded(item);
      }
    });
    modelChangeControl.fireChangedEvent();
    item.addChangeListener(itemChangePropagator);
    return item;
  }

  @Override
  public void removeItem(final IEquipmentItem item) {
    equipmentItems.remove(item);
    equipmentItemControl.forAllDo(new IClosure<ICollectionListener<IEquipmentItem>>() {
      @Override
      public void execute(final ICollectionListener<IEquipmentItem> input) {
        input.itemRemoved(item);
      }
    });
    item.removeChangeListener(itemChangePropagator);
    modelChangeControl.fireChangedEvent();
  }

  @Override
  public void refreshItems() {
    for (IEquipmentItem item : new ArrayList<IEquipmentItem>(equipmentItems)) {
      if (canBeRemoved(item)) {
        IEquipmentItem refreshedItem = refreshItem(item);
        if (getCharacterOptionProvider().transferOptions(item, refreshedItem)) {
          initItem(refreshedItem);
        }
      }
    }
  }

  private IEquipmentItem refreshItem(final IEquipmentItem item) {
    String templateId = item.getTemplateId();
    MagicalMaterial material = item.getMaterial();
    removeItem(item);
    return addEquipmentObjectFor(templateId, material);
  }

  @Override
  public final void addEquipmentObjectListener(final ICollectionListener<IEquipmentItem> listener) {
    equipmentItemControl.addListener(listener);
  }

  protected final IExaltedRuleSet getRuleSet() {
    return ruleSet;
  }

  @Override
  public MaterialComposition getMaterialComposition(final String templateId) {
    IEquipmentTemplate template = loadEquipmentTemplate(templateId);
    return template.getComposition();
  }

  @Override
  public MagicalMaterial getMagicalMaterial(final String templateId) {
    IEquipmentTemplate template = loadEquipmentTemplate(templateId);
    return template.getMaterial();
  }

  @Override
  public int getTotalAttunementCost() {
    int total = 0;
    for (IEquipmentItem item : equipmentItems) {
      for (IEquipmentStats stats : item.getStats())
        if (stats instanceof IArtifactStats && item.getAttunementState() == ((IArtifactStats) stats).getAttuneType())
          total += ((IArtifactStats) stats).getAttuneCost();
    }
    return total;
  }

  @Override
  public void addChangeListener(final IChangeListener listener) {
    modelChangeControl.addChangeListener(listener);
  }
}