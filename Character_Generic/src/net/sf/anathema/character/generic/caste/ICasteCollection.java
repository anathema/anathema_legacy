package net.sf.anathema.character.generic.caste;

import net.sf.anathema.character.generic.rules.IExaltedEdition;

public interface ICasteCollection {

  public boolean containsCasteType(String casteTypeId);

  public ICasteType[] getAllCasteTypes(IExaltedEdition edition);

  public ICasteType getById(String casteTypeId);

  public boolean isEmpty();
}