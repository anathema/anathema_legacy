package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.rules.IExaltedEdition;

public interface ISpellMapper {

  public String getId(String id, IExaltedEdition edition);
}