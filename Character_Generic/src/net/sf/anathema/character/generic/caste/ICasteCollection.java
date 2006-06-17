package net.sf.anathema.character.generic.caste;

public interface ICasteCollection {

  public boolean containsCasteType(String casteTypeId);

  public ICasteType<? extends ICasteTypeVisitor>[] getAllCasteTypes();

  public ICasteType<? extends ICasteTypeVisitor> getById(String casteTypeId);

  public boolean isEmpty();
}