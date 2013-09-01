package net.sf.anathema.hero.display.presenter;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.display.HeroModelGroup;
import net.sf.anathema.framework.environment.ObjectFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InitializerList {

  private final IApplicationModel applicationModel;
  private final ObjectFactory objectFactory;

  public InitializerList(ObjectFactory objectFactory, IApplicationModel applicationModel) {
    this.objectFactory = objectFactory;
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