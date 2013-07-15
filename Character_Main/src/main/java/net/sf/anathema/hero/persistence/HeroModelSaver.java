package net.sf.anathema.hero.persistence;

import java.io.OutputStream;

public interface HeroModelSaver {

  OutputStream openOutputStream(String persistenceId);
}
