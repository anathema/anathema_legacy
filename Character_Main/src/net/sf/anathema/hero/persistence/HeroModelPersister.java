package net.sf.anathema.hero.persistence;

import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.Identifier;

public interface HeroModelPersister {

  Identifier getModelId();

  void load(HeroModel model, HeroModelLoader loader) throws PersistenceException;

  void save(HeroModel heroModel, HeroModelSaver saver);
}
