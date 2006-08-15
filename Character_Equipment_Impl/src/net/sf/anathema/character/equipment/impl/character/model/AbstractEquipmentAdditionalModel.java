package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentTemplate;
import net.sf.anathema.character.equipment.impl.character.model.natural.TotalArmour;
import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
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

  public final AdditionalModelType getAdditionalModelType() {
    return AdditionalModelType.Miscellaneous;
  }

  public final String getTemplateId() {
    return EquipmentAdditonalModelTemplate.ID;
  }

  public final IArmourStats getTotalPrintArmour(int lineCount) {
    TotalArmour armour = new TotalArmour();
    IArmourStats[] printArmours = getPrintArmours();
    for (int index = 0; index < Math.min(lineCount, printArmours.length); index++) {
      armour.addArmour(printArmours[index]);
    }
    return armour;
  }

  public final IEquipmentItem[] getEquipmentItems() {
    return equipmentItems.toArray(new IEquipmentItem[equipmentItems.size()]);
  }

  public final void addEquipmentObject(final IEquipmentTemplate template) {
    final IEquipmentItem item = new EquipmentItem(template, ruleSet);
    equipmentItems.add(item);
    equipmentItemControl.forAllDo(new IClosure<ICollectionListener<IEquipmentItem>>() {
      public void execute(ICollectionListener<IEquipmentItem> input) {
        input.itemAdded(item);
      }
    });
  }

  public final void addEquipmentObjectListener(ICollectionListener<IEquipmentItem> listener) {
    equipmentItemControl.addListener(listener);
  }
}