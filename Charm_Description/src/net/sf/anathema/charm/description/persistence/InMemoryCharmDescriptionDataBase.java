package net.sf.anathema.charm.description.persistence;

import java.util.HashMap;
import java.util.Map;

public class InMemoryCharmDescriptionDataBase implements CharmDescriptionDataBase {

  private Map<String, String> descriptionById = new HashMap<String, String>();

  public void saveDescription(String charmId, String description) {
    descriptionById.put(charmId, description);
  }

  public String loadDescription(String charmId) {
    return descriptionById.get(charmId);
  }
}
