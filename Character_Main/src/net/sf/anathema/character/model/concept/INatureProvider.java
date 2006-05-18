package net.sf.anathema.character.model.concept;

import net.sf.anathema.lib.exception.PersistenceException;

public interface INatureProvider {

  public INatureType getById(final String id);

  public INatureType[] getNatures();

  public void init() throws PersistenceException;  }