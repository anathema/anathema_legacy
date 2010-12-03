package net.sf.anathema.campaign.music.impl.persistence;

import java.io.File;

import net.sf.anathema.campaign.music.model.track.Md5Checksum;
import net.sf.anathema.framework.Version;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.config.Configuration;
import com.db4o.reflect.jdk.JdkReflector;

public class MusicDatabaseConnectionManager {

  private static ObjectContainer connection = null;

  public static ObjectContainer createConnection(File dbFile) {
    if (connection != null) {
      return connection;
    }
    Configuration configuration = Db4o.newConfiguration();
    configuration.objectClass(DbMp3Track.class).cascadeOnUpdate(true);
    configuration.objectClass(DbMp3Track.class).cascadeOnDelete(true);
    configuration.objectClass(DbLibrary.class).cascadeOnUpdate(true);
    configuration.objectClass(DbLibrary.class).cascadeOnDelete(true);
    configuration.objectClass(Md5Checksum.class).cascadeOnUpdate(true);
    configuration.objectClass(Md5Checksum.class).cascadeOnDelete(true);
    configuration.allowVersionUpdates(true);
    configuration.automaticShutDown(true);
    configuration.reflectWith(new JdkReflector(Version.class.getClassLoader()));
    connection = Db4o.openFile(configuration, dbFile.getAbsolutePath());
    return connection;
  }
}