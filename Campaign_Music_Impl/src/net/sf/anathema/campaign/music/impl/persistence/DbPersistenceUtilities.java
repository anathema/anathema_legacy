package net.sf.anathema.campaign.music.impl.persistence;

import java.util.ArrayList;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class DbPersistenceUtilities {

  @SuppressWarnings("unchecked")
  public static <C> List<C> getAllObjects(Class<C> componentType, ObjectContainer db) {
    Query query = db.query();
    query.constrain(componentType);
    List<C> resultList = new ArrayList<C>();
    for (ObjectSet objectSet = query.execute(); objectSet.hasNext();) {
      resultList.add((C) objectSet.next());
    }
    return resultList;
  }
}