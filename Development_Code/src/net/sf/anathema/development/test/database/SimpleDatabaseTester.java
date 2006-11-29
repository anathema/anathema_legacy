package net.sf.anathema.development.test.database;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class SimpleDatabaseTester {

  public static void main(String[] args) {
    fillDatabase();
    // queryDatabase();
  }

  private static void queryDatabase() {
    ObjectContainer db = Db4o.openFile("TestSimple.yap");
    ObjectSet<Map> query = db.query(new Predicate<Map>() {
      @Override
      public boolean match(Map map) {
        return true;
      }
    });
    Map map = query.get(0);
    Person person = (Person) map.get(Gender.Female);
    System.out.println(person.getName());
    db.close();
  }

  private static void fillDatabase() {
    ObjectContainer db = Db4o.openFile("TestSimple.yap");
    Map map = new HashedMap();
    Person sandra = new Person("Sandra", Gender.Female);
    map.put(Gender.Female, sandra);
    Person urs = new Person("Urs", Gender.Male);
    map.put(Gender.Male, urs);
    db.set(map);
    db.commit();
    db.close();
  }
}