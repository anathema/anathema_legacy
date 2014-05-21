package net.sf.anathema.hero.concept;

import net.sf.anathema.character.main.template.TemplateType;

public interface CasteCollection {

  boolean containsCasteType(String casteTypeId);

  CasteType[] getAllCasteTypes(TemplateType template);

  CasteType getById(String casteTypeId);

  boolean isEmpty();
}