package net.sf.anathema.character.main.persistence;

import net.sf.anathema.framework.repository.access.RepositoryReadAccess;
import net.sf.anathema.hero.persistence.HeroModelLoader;

import java.io.InputStream;

public class HeroModelLoaderImpl implements HeroModelLoader {

  private RepositoryReadAccess readAccess;

  public HeroModelLoaderImpl(RepositoryReadAccess readAccess) {
    this.readAccess = readAccess;
  }

  @Override
  public InputStream openInputStream(String persistenceId) {
    return readAccess.openSubInputStream(persistenceId);
  }
}
