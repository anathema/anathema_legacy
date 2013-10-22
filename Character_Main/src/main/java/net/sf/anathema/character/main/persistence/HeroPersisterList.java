package net.sf.anathema.character.main.persistence;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.persistence.HeroModelPersister;
import net.sf.anathema.hero.persistence.HeroModelPersisterAutoCollector;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HeroPersisterList {

  private final HeroModelPersisterAutoCollector persisterAutoCollector;

  public HeroPersisterList(ObjectFactory objectFactory) {
    this.persisterAutoCollector = new HeroModelPersisterAutoCollector(objectFactory);
  }

  public Iterable<HeroModelPersister> iterator(Hero hero) {
    Collection<HeroModelPersister> allPersisters = persisterAutoCollector.collect();
    List<HeroModelPersister> heroPersisters = new ArrayList<>();
    for (HeroModel model : hero) {
      Collection<HeroModelPersister> foundPersisters = findPersisters(allPersisters, model.getId());
      heroPersisters.addAll(foundPersisters);
    }
    return heroPersisters;
  }

  private Collection<HeroModelPersister> findPersisters(Collection<HeroModelPersister> allPersisters, Identifier modelId) {
    return Collections2.filter(allPersisters, new IsForModelWithId(modelId));
  }

  private static class IsForModelWithId implements Predicate<HeroModelPersister> {
    private final Identifier modelId;

    public IsForModelWithId(Identifier modelId) {
      this.modelId = modelId;
    }

    @Override
    public boolean apply(HeroModelPersister input) {
      return input.getModelId().equals(modelId);
    }
  }
}
