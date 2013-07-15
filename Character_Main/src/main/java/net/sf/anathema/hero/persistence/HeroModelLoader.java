package net.sf.anathema.hero.persistence;

import java.io.InputStream;

public interface HeroModelLoader {

  InputStream openInputStream(String persistenceId);
}
