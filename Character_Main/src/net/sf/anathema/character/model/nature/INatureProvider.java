package net.sf.anathema.character.model.nature;

import net.sf.anathema.lib.exception.AnathemaException;

public interface INatureProvider {

  public void init() throws AnathemaException;

  public INatureType[] getAll();

  public INatureType[] getAllSorted();

  public INatureType getById(final String id);

}