package net.sf.anathema.character.equipment.impl.item.model.db4o;

import java.io.File;

import net.sf.anathema.character.equipment.impl.character.model.EquipmentTemplate;
import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractWeaponStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ArmourStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ArtifactStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.ShieldStats;
import net.sf.anathema.framework.Version;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.config.Configuration;
import com.db4o.reflect.jdk.JdkReflector;

public class EquipmentDatabaseConnectionManager {

  public static ObjectContainer createConnection(File dbFile) {
    Configuration configuration = Db4o.newConfiguration();
    configuration.objectClass(EquipmentTemplate.class).cascadeOnUpdate(true);
    configuration.objectClass(ArmourStats.class).cascadeOnUpdate(true);
    configuration.objectClass(ShieldStats.class).cascadeOnUpdate(true);
    configuration.objectClass(AbstractWeaponStats.class).cascadeOnActivate(true);
    configuration.objectClass(AbstractWeaponStats.class).cascadeOnUpdate(true);
    configuration.objectClass(ArtifactStats.class).cascadeOnUpdate(true);
    configuration.allowVersionUpdates(true);
    configuration.automaticShutDown(true);
    configuration.readOnly(false);
    configuration.reflectWith(new JdkReflector(Version.class.getClassLoader()));
    return Db4o.openFile(configuration, dbFile.getAbsolutePath());
  }
}