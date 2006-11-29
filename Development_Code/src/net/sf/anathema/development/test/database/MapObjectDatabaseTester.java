package net.sf.anathema.development.test.database;

import net.disy.commons.core.util.Ensure;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class MapObjectDatabaseTester {
  public static void main(String[] args) {
    // fillDatabase();
    queryDatabase();
  }

  private static void queryDatabase() {
    ObjectContainer db = Db4o.openFile("TestMapObject.yap");
    ObjectSet<GenderMap> query = db.query(new Predicate<GenderMap>() {
      @Override
      public boolean match(GenderMap map) {
        return true;
      }
    });
    GenderMap map = query.get(0);
    System.out.println(map.get(Gender.Male).getName());
    Ensure.ensureArgumentEquals("Error", "Urs", map.get(Gender.Male).getName());
    db.close();
  }

  private static void fillDatabase() {
    ObjectContainer db = Db4o.openFile("TestMapObject.yap");
    GenderMap map = new GenderMap();
    Person sandra = new Person("Sandra", Gender.Female);
    Person urs = new Person("Urs", Gender.Male);
    map.put(Gender.Female, sandra);
    map.put(Gender.Male, urs);
    db.set(map);
    db.commit();
    db.close();
  }
}
