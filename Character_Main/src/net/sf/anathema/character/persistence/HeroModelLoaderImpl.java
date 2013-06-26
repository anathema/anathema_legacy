package net.sf.anathema.character.persistence;

import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.hero.persistence.HeroModelLoader;

import java.io.InputStream;

public class HeroModelLoaderImpl implements HeroModelLoader {

  private IRepositoryReadAccess readAccess;

  public HeroModelLoaderImpl(IRepositoryReadAccess readAccess) {
    this.readAccess = readAccess;
  }

  @Override
  public InputStream openInputStream(String persistenceId) {
    return readAccess.openSubInputStream(persistenceId);
  }
}
