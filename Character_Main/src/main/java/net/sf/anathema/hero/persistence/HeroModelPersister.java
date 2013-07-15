package net.sf.anathema.hero.persistence;

import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.Identifier;

public interface HeroModelPersister {

  void setMessaging(IMessaging messaging);

  Identifier getModelId();

  void load(Hero hero, HeroModel model, HeroModelLoader loader) throws PersistenceException;

  void save(HeroModel heroModel, HeroModelSaver saver);
}
