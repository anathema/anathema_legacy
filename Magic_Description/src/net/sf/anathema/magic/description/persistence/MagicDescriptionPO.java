package net.sf.anathema.magic.description.persistence;

public class MagicDescriptionPO {

  public static MagicDescriptionPO ForIdAndDescription(String charmId, String description) {
    MagicDescriptionPO persistenceObject = new MagicDescriptionPO();
    persistenceObject.charmId = charmId;
    persistenceObject.description = description;
    return persistenceObject;
  }
  
  public String charmId;
  public String description;
}
