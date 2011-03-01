package net.sf.anathema.character.generic.caste;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;

public interface ICasteCollection {

  public boolean containsCasteType(String casteTypeId);

  public ICasteType[] getAllCasteTypes(IExaltedEdition edition, ITemplateType template);

  public ICasteType getById(String casteTypeId);

  public boolean isEmpty();
}