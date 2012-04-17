package net.sf.anathema.character.equipment.impl.module;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentTemplate;
import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractWeaponStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.TraitModifyingStats;
import net.sf.anathema.character.equipment.impl.item.model.db4o.EquipmentDatabaseConnectionManager;
import net.sf.anathema.character.equipment.impl.item.model.gson.GsonEquipmentDatabase;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.impl.persistence.SecondEdition;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.Version;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IAnathemaBootJob;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.platform.database.DatabaseUtils;

import java.io.File;
import java.io.IOException;

import static net.sf.anathema.character.equipment.impl.item.model.db4o.Db4OEquipmentDatabase.DATABASE_FILE;
import static net.sf.anathema.character.equipment.impl.item.model.db4o.Db4OEquipmentDatabase.DATABASE_FOLDER;

@BootJob
public class DatabaseConversionBootJob implements IAnathemaBootJob {

  private final static Logger logger = Logger.getLogger(DatabaseConversionBootJob.class);

  @Override
  public void run(IResources resources, IAnathemaModel anathemaModel, IAnathemaView view) {
    ProxySplashscreen.getInstance().displayStatusMessage(
            resources.getString("Equipment.Bootjob.Splashmessage")); //$NON-NLS-1$
    File databaseFile = getDatabaseFile(anathemaModel);
    if (!databaseFile.exists()){
      return;
    }
    ObjectContainer container = EquipmentDatabaseConnectionManager.createConnection(databaseFile);
    Version dbVersion = DatabaseUtils.getDatabaseVersion(container);
    Version anathemaVersion = new Version(resources);
    logEquipmentDatabaseVersion(dbVersion);
    if (anathemaVersion.isLargerThan(dbVersion)) {
      backupDatabase(anathemaModel, container, anathemaVersion);
      updateContent(container);
      convertToGson(anathemaModel, container);
      removeDb4oDatabase(databaseFile, container);
    }
  }

  private void removeDb4oDatabase(File databaseFile, ObjectContainer container) {
    container.close();
    databaseFile.delete();
  }

  private void logEquipmentDatabaseVersion(Version dbVersion) {
    if( dbVersion != null )
		logger.info("Found equipment database at version " + dbVersion.asString());
	else
		logger.info("No equipment database found, creating new empty database");
  }

  private void convertToGson(IAnathemaModel anathemaModel, ObjectContainer container) {
    GsonEquipmentDatabase database = GsonEquipmentDatabase.CreateFrom(anathemaModel);
    Query query = container.query();
    query.constrain(EquipmentTemplate.class);
    ObjectSet set = query.execute();
    for (Object object : set) {
      EquipmentTemplate template = (EquipmentTemplate) object;
      database.saveTemplateNoOverwrite(template);
    }
  }

  private void backupDatabase(IAnathemaModel anathemaModel, ObjectContainer container, Version anathemaVersion) {
    try {
      container.ext().backup(getBackupFile(anathemaModel, anathemaVersion));
    } catch (IOException e) {
      throw new RuntimeException("Could not backup equipment database.");
    }
  }

  private void updateContent(ObjectContainer container) {
    Query query = container.query();
    query.constrain(EquipmentTemplate.class);
    ObjectSet set = query.execute();
    for (Object object : set) {
      EquipmentTemplate template = (EquipmentTemplate) object;
      if (!template.hasStats()) {
        continue;
      }
      deleteFirstEditionStats(template);
      deleteShieldStats(template);
      addMinimumDamage(template, container);
      changeDVToPools(template, container);
      if (template.hasStats()) {
        container.set(template);
      } else {
        container.delete(template);
      }
    }
  }
  
  private void changeDVToPools(EquipmentTemplate template, ObjectContainer container) {
	IEquipmentStats[] stats = template.getStats();
	for (IEquipmentStats stat : stats) {
	  if (stat instanceof TraitModifyingStats) {
	    TraitModifyingStats modifiers = (TraitModifyingStats) stat;
	    if (modifiers.getPDVPoolMod() != 0 ||
	       	modifiers.getDDVPoolMod() != 0 ||
	        modifiers.getMDDVPoolMod() != 0 ||
	        modifiers.getMPDVPoolMod() != 0) {
		    modifiers.setDDVPoolMod(2 * modifiers.getDDVPoolMod());
		    modifiers.setPDVPoolMod(2 * modifiers.getPDVPoolMod());
		    modifiers.setMDDVPoolMod(2 * modifiers.getMDDVPoolMod());
		    modifiers.setMPDVPoolMod(2 * modifiers.getMPDVPoolMod());
		    container.set(modifiers);
	    }
	  }
	}
  }

  private void addMinimumDamage(EquipmentTemplate template, ObjectContainer container) {
    IEquipmentStats[] stats = template.getStats();
    for (IEquipmentStats stat : stats) {
      if (stat instanceof AbstractWeaponStats) {
        AbstractWeaponStats weapon = (AbstractWeaponStats) stat;
        if (weapon.getMinimumDamage() == 0 && !weapon.inflictsNoDamage()) {
          weapon.setMinimumDamage(1);
          container.set(weapon);
        }
      }
    }
  }

  private void deleteShieldStats(EquipmentTemplate template) {
    IEquipmentStats[] stats = template.getStats();
    for (IEquipmentStats stat : stats) {
      if (stat instanceof IShieldStats) {
        template.removeStats(new SecondEdition(), stat);
      }
    }
  }

  private void deleteFirstEditionStats(EquipmentTemplate template) {
    template.removeStats("CoreRules");
    template.removeStats("PowerCombat");
  }

  private File getDatabaseFile(IAnathemaModel model) {
    return new File(model.getRepository().getDataBaseDirectory(DATABASE_FOLDER), DATABASE_FILE);
  }

  private String getBackupFile(IAnathemaModel anathemaModel, Version version) {
    return new File(anathemaModel.getRepository().getRepositoryPath(),
            "BackupForFirstLaunchOf" + version.asString()).getAbsolutePath() + ".Equipment.yap";
  }
}