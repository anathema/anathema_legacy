package net.sf.anathema.character.equipment.impl.module;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentTemplate;
import net.sf.anathema.character.equipment.impl.item.model.db4o.EquipmentDatabaseConnectionManager;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.Version;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IAnathemaBootJob;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.platform.database.DatabaseUtils;

import java.io.File;

import static net.sf.anathema.character.equipment.impl.item.model.db4o.Db4OEquipmentDatabase.DATABASE_FILE;
import static net.sf.anathema.character.equipment.impl.item.model.db4o.Db4OEquipmentDatabase.DATABASE_FOLDER;

@BootJob
public class DatabaseConversionBootJob implements IAnathemaBootJob {

  @Override
  public void run(IResources resources, IAnathemaModel anathemaModel, IAnathemaView view) {
    ProxySplashscreen.getInstance().displayStatusMessage(
            resources.getString("Equipment.Bootjob.Splashmessage")); //$NON-NLS-1$
    File databaseFile = getDatabaseFile(anathemaModel);
    ObjectContainer container = EquipmentDatabaseConnectionManager.createConnection(databaseFile);
    Version dbVersion = DatabaseUtils.getDatabaseVersion(container);
    Version anathemaVersion = new Version(resources);
    Version updatedVersion = updateDbVersion(dbVersion, anathemaVersion);
    deleteFirstEditionAndShields(container);
    finish(container, updatedVersion);
  }

  private void deleteFirstEditionAndShields(ObjectContainer container) {
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
      if (template.hasStats()) {
        container.set(template);
      } else {
        container.delete(template);
      }
    }
  }

  private void deleteShieldStats(EquipmentTemplate template) {
    IEquipmentStats[] stats = template.getStats(ExaltedRuleSet.SecondEdition);
    for (IEquipmentStats stat : stats) {
      if (stat instanceof IShieldStats) {
        template.removeStats(ExaltedRuleSet.SecondEdition, stat);
      }
    }
  }

  private void deleteFirstEditionStats(EquipmentTemplate template) {
    template.removeStats("CoreRules");
    template.removeStats("PowerCombat");
  }

  private Version updateDbVersion(Version dbVersion, Version anathemaVersion) {
    if (dbVersion != null) {
      dbVersion.updateTo(anathemaVersion);
      return dbVersion;
    }
    return anathemaVersion;
  }

  private void finish(ObjectContainer container, Version version) {
    container.set(version);
    container.commit();
    container.close();
  }

  private File getDatabaseFile(IAnathemaModel model) {
    return new File(model.getRepository().getDataBaseDirectory(DATABASE_FOLDER), DATABASE_FILE);
  }
}