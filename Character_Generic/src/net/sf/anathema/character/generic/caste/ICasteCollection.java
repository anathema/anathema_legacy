package net.sf.anathema.character.generic.caste;

import net.sf.anathema.character.generic.template.ITemplateType;

public interface ICasteCollection {

  boolean containsCasteType(String casteTypeId);

  ICasteType[] getAllCasteTypes(ITemplateType template);

  ICasteType getById(String casteTypeId);

  boolean isEmpty();
}