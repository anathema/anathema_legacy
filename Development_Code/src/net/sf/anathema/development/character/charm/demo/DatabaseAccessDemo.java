package net.sf.anathema.development.character.charm.demo;

import net.sf.anathema.character.generic.impl.magic.persistence.CharmBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmIO;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMartialArtsCharm;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.Identificate;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class DatabaseAccessDemo {

  public static void getCharmsFromDatabase() {
    ObjectContainer db = Db4o.openFile("TestCharmDatabase");
    try {
      Query query = db.query();
      query.constrain(ICharm.class);
      ObjectSet set = query.execute();
      for (; set.hasNext();) {
        ICharm charm = (ICharm) set.next();
        System.out.println(charm.toString());
      }
    }
    finally {
      db.close();
    }
  }

  public static void writeCharmsToDatabase() throws PersistenceException {
    // Does not handle powercombat
    for (CharacterType type : CharacterType.values()) {
      if (type == CharacterType.MORTAL) {
        continue;
      }
      CharmIO reader = new CharmIO();
      CharmBuilder builder = new CharmBuilder();
      try {
        Document charmDocument = reader.readCharms(type);
        ICharm[] charmArray = builder.buildCharms(charmDocument, false);
        writeCharmArray(charmArray);
      }
      catch (DocumentException e) {
        throw new CharmException(e);
      }
      try {
        Document charmDocument = reader.readCharms(new Identificate("MartialArts"));
        IMartialArtsCharm[] martialArtsCharms = builder.buildMartialArtsCharms(charmDocument, false);
        writeCharmArray(martialArtsCharms);
      }
      catch (DocumentException e) {
        throw new CharmException(e);
      }
    }
  }

  private static void writeCharmArray(ICharm[] charms) {
    ObjectContainer db = Db4o.openFile("TestCharmDatabase");
    try {
      for (ICharm charm : charms) {
        db.set(charm);
      }
    }
    finally {
      db.close();
    }
  }
}