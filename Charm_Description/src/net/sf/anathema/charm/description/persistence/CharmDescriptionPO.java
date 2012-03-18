package net.sf.anathema.charm.description.persistence;

public class CharmDescriptionPO {

  public static CharmDescriptionPO ForIdAndDescription(String charmId, String description) {
    CharmDescriptionPO persistenceObject = new CharmDescriptionPO();
    persistenceObject.charmId = charmId;
    persistenceObject.description = description;
    return persistenceObject;
  }
  
  public String charmId;
  public String description;
}
