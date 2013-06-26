package net.sf.anathema.character.persistence;

import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.hero.persistence.HeroModelSaver;

import java.io.OutputStream;

public class HeroModelSaverImpl implements HeroModelSaver {
  private final IRepositoryWriteAccess writeAccess;

  public HeroModelSaverImpl(IRepositoryWriteAccess writeAccess) {
    this.writeAccess = writeAccess;
  }

  @Override
  public OutputStream openOutputStream(String persistenceId) {
    return writeAccess.createSubOutputStream(persistenceId);
  }
}
