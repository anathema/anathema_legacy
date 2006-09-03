package net.sf.anathema.campaign.music.impl.persistence;

import java.io.File;

import net.sf.anathema.campaign.music.model.track.Md5Checksum;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

public class MusicDatabaseConnectionManager {

  private static ObjectContainer connection = null;

  public static ObjectContainer createConnection(File dbFile) {
    if (connection != null) {
      return connection;
    }
    Db4o.configure().objectClass(DbMp3Track.class).cascadeOnUpdate(true);
    Db4o.configure().objectClass(DbMp3Track.class).cascadeOnDelete(true);
    Db4o.configure().objectClass(DbLibrary.class).cascadeOnUpdate(true);
    Db4o.configure().objectClass(DbLibrary.class).cascadeOnDelete(true);
    Db4o.configure().objectClass(Md5Checksum.class).cascadeOnUpdate(true);
    Db4o.configure().objectClass(Md5Checksum.class).cascadeOnDelete(true);
    Db4o.configure().allowVersionUpdates(true);
    Db4o.configure().automaticShutDown(true);
    connection = Db4o.openFile(dbFile.getAbsolutePath());
    return connection;
  }
}