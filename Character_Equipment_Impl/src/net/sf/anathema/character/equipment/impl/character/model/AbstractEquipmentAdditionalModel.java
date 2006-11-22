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
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.collection.ICollectionListener;

public abstract class AbstractEquipmentAdditionalModel extends AbstractAdditionalModelAdapter implements
    IEquipmentAdditionalModel {

  private final GenericControl<ICollectionListener<IEquipmentItem>> equipmentItemControl = new GenericControl<ICollectionListener<IEquipmentItem>>();
  private final List<IEquipmentItem> equipmentItems = new ArrayList<IEquipmentItem>();
  private final IExaltedRuleSet ruleSet;

  public AbstractEquipmentAdditionalModel(IExaltedRuleSet ruleSet) {
    this.ruleSet = ruleSet;
  }

  public IEquipmentPrintModel getPrintModel() {
    return new EquipmentPrintModel(this);
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

  public IEquipmentItem addEquipmentObjectFor(String templateId, MagicalMaterial material) {
    final IEquipmentTemplate template = loadEquipmentTemplate(templateId);
    if (template == null) {
      return getSpecialManagedItem(templateId);
    }
    return addEquipmentObjectFor(template, material);
  }

  protected abstract IEquipmentItem getSpecialManagedItem(String templateId);

  protected abstract IEquipmentTemplate loadEquipmentTemplate(String templateId);

  protected final IEquipmentItem addEquipmentObjectFor(final IEquipmentTemplate template, MagicalMaterial material) {
    final IEquipmentItem item = new EquipmentItem(template, ruleSet, material);
    equipmentItems.add(item);
    equipmentItemControl.forAllDo(new IClosure<ICollectionListener<IEquipmentItem>>() {
      public void execute(ICollectionListener<IEquipmentItem> input) {
        input.itemAdded(item);
      }
    });
    return item;
  }

  public void removeItem(final IEquipmentItem item) {
    equipmentItems.remove(item);
    equipmentItemControl.forAllDo(new IClosure<ICollectionListener<IEquipmentItem>>() {
      public void execute(ICollectionListener<IEquipmentItem> input) {
        input.itemRemoved(item);
      }
    });
  }

  public final void addEquipmentObjectListener(ICollectionListener<IEquipmentItem> listener) {
    equipmentItemControl.addListener(listener);
  }

  protected final IExaltedRuleSet getRuleSet() {
    return ruleSet;
  }

  public MaterialComposition getMaterialComposition(String templateId) {
    IEquipmentTemplate template = loadEquipmentTemplate(templateId);
    return template.getComposition();
  }

  public MagicalMaterial getMagicalMaterial(String templateId) {
    IEquipmentTemplate template = loadEquipmentTemplate(templateId);
    return template.getMaterial();
  }
}