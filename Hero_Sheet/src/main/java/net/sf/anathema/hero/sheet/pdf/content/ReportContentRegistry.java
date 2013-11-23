package net.sf.anathema.hero.sheet.pdf.content;

import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.Resources;

import java.util.Collection;

public class ReportContentRegistry {

  private final Resources resources;
  private final ObjectFactory objectFactory;

  public ReportContentRegistry(ObjectFactory objectFactory, Resources resources) {
    this.objectFactory = objectFactory;
    this.resources = resources;
  }

  @SuppressWarnings("unchecked")
  public <C extends SubContent> ReportContentFactory<C> getFactory(Class<C> contentClass) {
    for (ReportContentFactory factory : instantiateFactories(contentClass)) {
      Produces annotation = factory.getClass().getAnnotation(Produces.class);
      Class producedClass = annotation.value();
      if (producedClass.equals(contentClass)) {
        return (ReportContentFactory<C>) factory;
      }
    }
    throw new RuntimeException("Could not find content for " + contentClass.getName());
  }

  private Collection<ReportContentFactory> instantiateFactories(Class contentClass) {
    try {
      return objectFactory.instantiateAllImplementers(ReportContentFactory.class, resources);
    } catch (InitializationException e) {
      throw new RuntimeException("Could not create content for " + contentClass.getName(), e);
    }
  }
}