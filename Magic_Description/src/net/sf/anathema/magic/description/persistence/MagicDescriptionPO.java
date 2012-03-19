package net.sf.anathema.magic.description.persistence;

public class MagicDescriptionPO {

  public static MagicDescriptionPO ForIdAndDescription(String magicId, String description) {
    MagicDescriptionPO persistenceObject = new MagicDescriptionPO();
    persistenceObject.magicId = magicId;
    persistenceObject.description = description;
    return persistenceObject;
  }
  
  public String magicId;
  public String description;
}
