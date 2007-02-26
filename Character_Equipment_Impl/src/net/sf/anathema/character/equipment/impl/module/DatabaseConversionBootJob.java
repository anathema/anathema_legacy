package net.sf.anathema.character.equipment.impl.module;

import java.io.File;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.character.equipment.impl.character.model.stats.AbstractWeaponStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.MeleeWeaponStats;
import net.sf.anathema.character.equipment.impl.character.model.stats.RangedWeaponStats;
import net.sf.anathema.character.equipment.impl.item.model.EquipmentTemplateEditModel;
import net.sf.anathema.character.equipment.impl.item.model.db4o.Db4OEquipmentDatabase;
import net.sf.anathema.character.equipment.impl.item.model.db4o.EquipmentDatabaseConnectionManager;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.Version;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.IAnathemaBootJob;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.platform.database.DatabaseUtils;

import com.db4o.ObjectContainer;

public class DatabaseConversionBootJob implements IAnathemaBootJob {

  @Override
  public void run(IResources resources, IAnathemaModel anathemaModel, IAnathemaView view) {
    File databaseFile = getDatabaseFile(anathemaModel);
    ObjectContainer container = EquipmentDatabaseConnectionManager.createConnection(databaseFile);
    Version dbVersion = DatabaseUtils.getDatabaseVersion(container);
    Version anathemaVersion = new Version(resources);
    if (dbVersion != null) {
      dbVersion.updateTo(anathemaVersion);
      finish(container, dbVersion);
      return;
    }
    ProxySplashscreen.getInstance().displayStatusMessage(resources.getString("Equipment.Bootjob.Splashmessage")); //$NON-NLS-1$
    Db4OEquipmentDatabase database = new Db4OEquipmentDatabase(container);
    EquipmentTemplateEditModel model = new EquipmentTemplateEditModel(database);
    String[] allAvailableTemplateIds = database.getAllAvailableTemplateIds();
    for (String id : allAvailableTemplateIds) {
      model.setEditTemplate(id);
      for (IExaltedRuleSet rules : ExaltedRuleSet.values()) {
        for (IEquipmentStats stats : model.getStats(rules)) {
          if (!(stats instanceof AbstractWeaponStats)) {
            continue;
          }
          IEquipmentStats newStats = convertStats((AbstractWeaponStats) stats, database);
          model.replaceStatistics(rules, stats, newStats);
        }
      }
      database.updateTemplate(id, model.createTemplate());
      model.setNewTemplate();
    }
    finish(container, anathemaVersion);
  }

  private void finish(ObjectContainer container, Version version) {
    container.set(version);
    container.commit();
    container.close();
  }

  private IEquipmentStats convertStats(AbstractWeaponStats stats, Db4OEquipmentDatabase database) {
    if (stats instanceof MeleeWeaponStats) {
      return new MeleeWeaponStats(database.getCollectionFactory(), (MeleeWeaponStats) stats);
    }
    else if (stats instanceof RangedWeaponStats) {
      return new RangedWeaponStats(database.getCollectionFactory(), (RangedWeaponStats) stats);
    }
    throw new IllegalStateException("Unknown implementation of AbstractWeaponStats."); //$NON-NLS-1$
  }

  private File getDatabaseFile(IAnathemaModel model) {
    return new File(
        model.getRepository().getDataBaseDirectory(Db4OEquipmentDatabase.DATABASE_FOLDER),
        Db4OEquipmentDatabase.DATABASE_FILE);
  }
}