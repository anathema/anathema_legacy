package net.sf.anathema.character.generic.caste;

public interface ICasteCollection {

  public boolean containsCasteType(String casteTypeId);

  public ICasteType[] getAllCasteTypes();

  public ICasteType getById(String casteTypeId);

  public boolean isEmpty();
}