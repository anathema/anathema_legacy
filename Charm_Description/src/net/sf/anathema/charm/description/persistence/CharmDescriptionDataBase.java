package net.sf.anathema.charm.description.persistence;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import net.disy.commons.core.util.StringUtilities;

import java.net.UnknownHostException;

public class CharmDescriptionDataBase {

  public static final String CHARM_ID_FIELD = "charmId";
  public static final String DESCRIPTION_FIELD = "detail";
  public static final String DB_NAME = "anathema";
  public static final String COLLECTION_NAME = "charmDescriptions";
  private Mongo mongo;

  private CharmDescriptionDataBase() {
    try {
      this.mongo = new Mongo();
      getDbCollection();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }

  private DBCollection getDbCollection() {
    return mongo.getDB(DB_NAME).getCollection(COLLECTION_NAME);
  }

  public void saveDescription(String charmId, String description) {
    DBObject foundObject = findDbObject(charmId);
    if (foundObject != null) {
      update(foundObject, description);
    } else if (description != null) {
      insertNewObject(charmId, description);
    }
  }

  private void update(DBObject dbObject, String description) {
    if (StringUtilities.isNullOrTrimmedEmpty(description)) {
      getDbCollection().remove(dbObject);
    } else {
      getDbCollection().update(null, null);
    }
  }

  private void insertNewObject(String charmId, String description) {
    BasicDBObject newObject = createDbObjectWithCharmId(charmId);
    newObject.put(DESCRIPTION_FIELD, description);
    getDbCollection().insert(newObject);
  }

  public String loadDescription(String charmId) {
    DBObject dbObject = findDbObject(charmId);
    return dbObject == null ? dbObject.get(DESCRIPTION_FIELD).toString() : null;
  }

  private DBObject findDbObject(String charmId) {
    BasicDBObject query = createDbObjectWithCharmId(charmId);
    DBCursor cursor = getDbCollection().find(query);
    return cursor.hasNext() ? cursor.next() : null;
  }

  private BasicDBObject createDbObjectWithCharmId(String charmId) {
    BasicDBObject doc = new BasicDBObject();
    doc.put(CHARM_ID_FIELD, charmId);
    return doc;
  }
}
