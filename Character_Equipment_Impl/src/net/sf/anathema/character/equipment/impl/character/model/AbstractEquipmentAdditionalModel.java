package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentPrintModel;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.collection.ICollectionListener;

public abstract class AbstractEquipmentAdditionalModel extends AbstractAdditionalModelAdapter implements
    IEquipmentAdditionalModel {

  private final GenericControl<ICollectionListener<IEquipmentItem>> equipmentItemControl = new GenericControl<ICollectionListener<IEquipmentItem>>();
  private final ChangeControl modelChangeControl = new ChangeControl();
  private final List<IEquipmentItem> equipmentItems = new ArrayList<IEquipmentItem>();
  private final IExaltedRuleSet ruleSet;
  private final IEquipmentPrintModel printModel;
  private final IChangeListener itemChangePropagator = new IChangeListener() {
    public void changeOccured() {
      modelChangeControl.fireChangedEvent();
    }
  };

  public AbstractEquipmentAdditionalModel(final IExaltedRuleSet ruleSet, final IArmourStats naturalArmour) {
    this.ruleSet = ruleSet;
    this.printModel = new EquipmentPrintModel(this, naturalArmour);
  }

  public IEquipmentPrintModel getPrintModel() {
    return printModel;
  }

  public final AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Miscellaneous;
  }

  public final String getTemplateId() {
    return IEquipmentAdditionalModelTemplate.ID;
  }

  public final IEquipmentItem[] getEquipmentItems() {
    return equipmentItems.toArray(new IEquipmentItem[equipmentItems.size()]);
  }

  public IEquipmentItem addEquipmentObjectFor(final String templateId, final MagicalMaterial material) {
    final IEquipmentTemplate template = loadEquipmentTemplate(templateId);
    if (template == null) {
      return getSpecialManagedItem(templateId);
    }
    return addEquipmentObjectFor(template, material);
  }

  protected abstract IEquipmentItem getSpecialManagedItem(String templateId);

  protected abstract IEquipmentTemplate loadEquipmentTemplate(String templateId);

  protected final IEquipmentItem addEquipmentObjectFor(final IEquipmentTemplate template, final MagicalMaterial material) {
    final IEquipmentItem item = new EquipmentItem(template, ruleSet, material);
    equipmentItems.add(item);
    equipmentItemControl.forAllDo(new IClosure<ICollectionListener<IEquipmentItem>>() {
      public void execute(final ICollectionListener<IEquipmentItem> input) {
        input.itemAdded(item);
      }
    });
    modelChangeControl.fireChangedEvent();
    item.addChangeListener(itemChangePropagator);
    return item;
  }

  public void removeItem(final IEquipmentItem item) {
    equipmentItems.remove(item);
    equipmentItemControl.forAllDo(new IClosure<ICollectionListener<IEquipmentItem>>() {
      public void execute(final ICollectionListener<IEquipmentItem> input) {
        input.itemRemoved(item);
      }
    });
    item.removeChangeListener(itemChangePropagator);
    modelChangeControl.fireChangedEvent();
  }

  public void refreshItems() {
    for (IEquipmentItem item : new ArrayList<IEquipmentItem>(equipmentItems)) {
      if (canBeRemoved(item)) {
        refreshItem(item);
      }
    }
  }

  private void refreshItem(final IEquipmentItem item) {
    String templateId = item.getTemplateId();
    MagicalMaterial material = item.getMaterial();
    removeItem(item);
    addEquipmentObjectFor(templateId, material);
  }

  public final void addEquipmentObjectListener(final ICollectionListener<IEquipmentItem> listener) {
    equipmentItemControl.addListener(listener);
  }

  protected final IExaltedRuleSet getRuleSet() {
    return ruleSet;
  }

  public MaterialComposition getMaterialComposition(final String templateId) {
    IEquipmentTemplate template = loadEquipmentTemplate(templateId);
    return template.getComposition();
  }

  public MagicalMaterial getMagicalMaterial(final String templateId) {
    IEquipmentTemplate template = loadEquipmentTemplate(templateId);
    return template.getMaterial();
  }

  public void addChangeListener(final IChangeListener listener) {
    modelChangeControl.addChangeListener(listener);
  }
}