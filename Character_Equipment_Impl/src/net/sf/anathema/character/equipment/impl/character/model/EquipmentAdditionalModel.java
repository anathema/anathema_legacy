package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.character.model.IEquipmentObject;
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
  private final List<IEquipmentObject> equipmentObjects = new ArrayList<IEquipmentObject>();
  private final GenericControl<ICollectionListener<IEquipmentObject>> equipmentObjectControl = new GenericControl<ICollectionListener<IEquipmentObject>>();

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

  public IEquipmentObject[] getAvailableObjects() {
    return new IEquipmentObject[0];
  }

  public void addEquipmentObject(final IEquipmentObject object) {
    equipmentObjects.add(object);
    equipmentObjectControl.forAllDo(new IClosure<ICollectionListener<IEquipmentObject>>() {
      public void execute(ICollectionListener<IEquipmentObject> input) {
        input.itemAdded(object);
      }
    });
  }

  public void addEquipmentObjectListener(ICollectionListener<IEquipmentObject> listener) {
    equipmentObjectControl.addListener(listener);
  }
}