package net.sf.anathema.character.main.presenter.initializers;

import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.initialization.ObjectFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InitializerList {

  private final IApplicationModel applicationModel;
  private final ObjectFactory objectFactory;

  public InitializerList(IApplicationModel applicationModel) {
    this.objectFactory = CharacterGenericsExtractor.getGenerics(applicationModel).getInstantiater();
    this.applicationModel = applicationModel;
  }

  public List<HeroModelInitializer> getInOrderFor(HeroModelGroup group) {
    ArrayList<HeroModelInitializer> initializerList = new ArrayList<>();
    Collection<HeroModelInitializer> collection = objectFactory.instantiateOrdered(RegisteredInitializer.class, applicationModel);
    for (HeroModelInitializer initializer : collection) {
      HeroModelGroup targetGroup = initializer.getClass().getAnnotation(RegisteredInitializer.class).value();
      if (targetGroup.equals(group)) {
        initializerList.add(initializer);
      }
    }
    return initializerList;
  }
}