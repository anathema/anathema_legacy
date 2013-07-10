package net.sf.anathema.hero.concept;

import net.sf.anathema.character.main.template.ITemplateType;

public interface CasteCollection {

  boolean containsCasteType(String casteTypeId);

  CasteType[] getAllCasteTypes(ITemplateType template);

  CasteType getById(String casteTypeId);

  boolean isEmpty();
}