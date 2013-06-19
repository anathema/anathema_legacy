package net.sf.anathema.hero.persistence;

import java.io.InputStream;
import java.io.OutputStream;

public interface HeroModelPersistence {

  InputStream openInputStream(String persistenceId);

  OutputStream openOutputStream(String persistenceId);
}
