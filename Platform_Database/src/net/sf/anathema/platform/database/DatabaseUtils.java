package net.sf.anathema.platform.database;

import net.sf.anathema.framework.Version;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class DatabaseUtils {

  public static Version getDatabaseVersion(ObjectContainer container) {
    ObjectSet<Version> query = container.query(Version.class);
    if (query.size() > 1) {
      throw new IllegalStateException("Database version must be unique."); //$NON-NLS-1$
    }
    if (query.size() == 0) {
      return null;
    }
    return query.get(0);
  }
}