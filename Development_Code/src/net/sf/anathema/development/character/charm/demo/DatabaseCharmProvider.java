package net.sf.anathema.development.character.charm.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.impl.magic.MartialArtsCharm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMartialArtsCharm;
import net.sf.anathema.character.generic.type.CharacterType;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class DatabaseCharmProvider {

  private static final String CHARM_DATABASE_FILENAME = "TestCharmDatabase"; //$NON-NLS-1$
  private static DatabaseCharmProvider instance = new DatabaseCharmProvider();
  private final Map<CharacterType, ICharm[]> charmsByType = new HashMap<CharacterType, ICharm[]>();
  private final List<IMartialArtsCharm> martialArtsCharms = new ArrayList<IMartialArtsCharm>();

  private DatabaseCharmProvider() {
    // Nothing to do
  }

  public static DatabaseCharmProvider getInstance() {
    return instance;
  }

  public ICharm[] getCharms(final CharacterType type) {
    if (charmsByType.containsKey(type)) {
      return charmsByType.get(type);
    }
    ICharm[] charmArray = getCharmsFromDatabase(type);
    charmsByType.put(type, charmArray);
    return charmArray;
  }

  private ICharm[] getCharmsFromDatabase(CharacterType type) {
    List<ICharm> charms = new ArrayList<ICharm>();
    ObjectContainer db = Db4o.openFile(CHARM_DATABASE_FILENAME);
    try {
      Query query = db.query();
      query.constrain(ICharm.class);
      query.constrain(MartialArtsCharm.class).not();
      query.descend("characterType").constrain(type); //$NON-NLS-1$
      ObjectSet set = query.execute();
      for (; set.hasNext();) {
        ICharm charm = (ICharm) set.next();
        charms.add(charm);
      }
    }
    finally {
      db.close();
    }
    return charms.toArray(new ICharm[charms.size()]);
  }

  public IMartialArtsCharm[] getMartialArtsCharms() {
    if (martialArtsCharms.isEmpty()) {
      ObjectContainer db = Db4o.openFile(CHARM_DATABASE_FILENAME);
      try {
        Query query = db.query();
        query.constrain(IMartialArtsCharm.class);
        ObjectSet set = query.execute();
        for (; set.hasNext();) {
          IMartialArtsCharm charm = (IMartialArtsCharm) set.next();
          martialArtsCharms.add(charm);
        }
      }
      finally {
        db.close();
      }
    }
    return martialArtsCharms.toArray(new IMartialArtsCharm[martialArtsCharms.size()]);
  }
}