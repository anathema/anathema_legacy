package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentTemplate;
import net.sf.anathema.character.equipment.impl.character.model.natural.Clinch;
import net.sf.anathema.character.equipment.impl.character.model.natural.Kick;
import net.sf.anathema.character.equipment.impl.character.model.natural.NaturalSoak;
import net.sf.anathema.character.equipment.impl.character.model.natural.Punch;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.control.collection.ICollectionListener;

public class EquipmentAdditionalModel extends AbstractEquipmentAdditionalModel {

  private final List<IArmourStats> printArmourStats = new ArrayList<IArmourStats>();
  private final List<IWeaponStats> printWeaponsStats = new ArrayList<IWeaponStats>();
  private final List<IEquipmentItem> equipmentObjects = new ArrayList<IEquipmentItem>();
  private final GenericControl<ICollectionListener<IEquipmentItem>> equipmentObjectControl = new GenericControl<ICollectionListener<IEquipmentItem>>();

  public EquipmentAdditionalModel(ICharacterModelContext context) {
    IBasicCharacterData basicCharacterContext = context.getBasicCharacterContext();
    printArmourStats.add(new NaturalSoak(
        context.getTraitCollection().getTrait(AttributeType.Stamina),
        basicCharacterContext.getCharacterType()));
    if (basicCharacterContext.getRuleSet().getEdition() == ExaltedEdition.SecondEdition) {
      printWeaponsStats.add(new Punch());
      printWeaponsStats.add(new Kick());
      printWeaponsStats.add(new Clinch());
    }
  }

  public IArmourStats[] getPrintArmours() {
    return printArmourStats.toArray(new IArmourStats[printArmourStats.size()]);
  }

  public IWeaponStats[] getPrintWeapons() {
    return printWeaponsStats.toArray(new IWeaponStats[printWeaponsStats.size()]);
  }

  public IEquipmentTemplate[] getAvailableTemplates() {
    return new IEquipmentTemplate[0];
  }

  public void addEquipmentObject(final IEquipmentTemplate template) {
    final IEquipmentItem item = new EquipmentItem(template); 
    equipmentObjects.add(item);
    equipmentObjectControl.forAllDo(new IClosure<ICollectionListener<IEquipmentItem>>() {
      public void execute(ICollectionListener<IEquipmentItem> input) {
        input.itemAdded(item);
      }
    });
  }

  public void addEquipmentObjectListener(ICollectionListener<IEquipmentItem> listener) {
    equipmentObjectControl.addListener(listener);
  }
}