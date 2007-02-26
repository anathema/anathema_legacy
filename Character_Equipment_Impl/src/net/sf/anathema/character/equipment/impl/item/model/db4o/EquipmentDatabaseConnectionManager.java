package net.sf.anathema.character.equipment.impl.item.model.db4o;

import java.io.File;

import net.sf.anathema.character.equipment.impl.character.model.EquipmentTemplate;
import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractWeaponStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ArmourStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ShieldStats;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

public class EquipmentDatabaseConnectionManager {

  public static ObjectContainer createConnection(File dbFile) {
    Db4o.configure().objectClass(EquipmentTemplate.class).cascadeOnUpdate(true);
    Db4o.configure().objectClass(ArmourStats.class).cascadeOnUpdate(true);
    Db4o.configure().objectClass(ShieldStats.class).cascadeOnUpdate(true);
    Db4o.configure().objectClass(AbstractWeaponStats.class).cascadeOnActivate(true);
    Db4o.configure().objectClass(AbstractWeaponStats.class).cascadeOnUpdate(true);
    Db4o.configure().allowVersionUpdates(true);
    Db4o.configure().automaticShutDown(true);
    Db4o.configure().readOnly(false);
    return Db4o.openFile(dbFile.getAbsolutePath());
  }
}