package net.sf.anathema.character.framework.persistence;

import net.sf.anathema.framework.repository.access.RepositoryWriteAccess;
import net.sf.anathema.hero.persistence.HeroModelSaver;

import java.io.OutputStream;

public class HeroModelSaverImpl implements HeroModelSaver {
  private final RepositoryWriteAccess writeAccess;

  public HeroModelSaverImpl(RepositoryWriteAccess writeAccess) {
    this.writeAccess = writeAccess;
  }

  @Override
  public OutputStream openOutputStream(String persistenceId) {
    return writeAccess.createSubOutputStream(persistenceId);
  }
}
