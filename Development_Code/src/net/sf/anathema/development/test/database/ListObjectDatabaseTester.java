package net.sf.anathema.development.test.database;

import net.disy.commons.core.util.Ensure;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class ListObjectDatabaseTester {
  public static void main(String[] args) {
    // fillDatabase();
    queryDatabase();
  }

  private static void queryDatabase() {
    ObjectContainer db = Db4o.openFile("TestListObject.yap");
    ObjectSet<GenderList> query = db.query(new Predicate<GenderList>() {
      @Override
      public boolean match(GenderList map) {
        return true;
      }
    });
    GenderList list = query.get(0);
    System.out.println(list.frequency(Gender.Female));
    Ensure.ensureArgumentEquals(3, list.frequency(Gender.Female));
    db.close();
  }

  private static void fillDatabase() {
    ObjectContainer db = Db4o.openFile("TestListObject.yap");
    GenderList list = new GenderList();
    list.add(Gender.Male);
    list.add(Gender.Female);
    list.add(Gender.Female);
    list.add(Gender.Male);
    list.add(Gender.Female);
    Ensure.ensureArgumentEquals(3, list.frequency(Gender.Female));
    db.set(list);
    db.commit();
    db.close();
  }
}
