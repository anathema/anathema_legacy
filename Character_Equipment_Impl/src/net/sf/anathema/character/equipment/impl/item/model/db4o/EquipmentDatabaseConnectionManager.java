package net.sf.anathema.character.equipment.impl.item.model.db4o;

import java.io.File;

import net.sf.anathema.character.equipment.impl.character.model.EquipmentTemplate;
import net.sf.anathema.character.equipment.impl.character.model.stats.ArmourStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ShieldStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.WeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

public class EquipmentDatabaseConnectionManager {

  public static ObjectContainer createConnection(File dbFile) {
    Db4o.configure().objectClass(EquipmentTemplate.class).cascadeOnUpdate(true);
    Db4o.configure().objectClass(ArmourStats.class).cascadeOnUpdate(true);
    Db4o.configure().objectClass(ShieldStats.class).cascadeOnUpdate(true);
    Db4o.configure().objectClass(WeaponStats.class).cascadeOnUpdate(true);
    Db4o.configure().objectClass(HealthType.class).persistStaticFieldValues();
    Db4o.configure().objectClass(ExaltedRuleSet.class).persistStaticFieldValues();
    Db4o.configure().objectClass(AbilityType.class).persistStaticFieldValues();
    Db4o.configure().objectClass(AttributeType.class).persistStaticFieldValues();
    Db4o.configure().objectClass(VirtueType.class).persistStaticFieldValues();
    Db4o.configure().objectClass(OtherTraitType.class).persistStaticFieldValues();
    Db4o.configure().allowVersionUpdates(true);
    Db4o.configure().automaticShutDown(true);
    Db4o.configure().readOnly(false);
    return Db4o.openFile(dbFile.getAbsolutePath());
  }
}