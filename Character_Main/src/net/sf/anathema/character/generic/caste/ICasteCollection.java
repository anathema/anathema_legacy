package net.sf.anathema.character.generic.caste;

import net.sf.anathema.character.generic.template.ITemplateType;

public interface ICasteCollection {

  boolean containsCasteType(String casteTypeId);

  CasteType[] getAllCasteTypes(ITemplateType template);

  CasteType getById(String casteTypeId);

  boolean isEmpty();
}