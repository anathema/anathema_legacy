package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.lib.resources.Resources;

import java.util.Collection;

public class ReportContentRegistry {

  private final Resources resources;
  private final Instantiater instantiater;

  public ReportContentRegistry(Instantiater instantiater, Resources resources) {
    this.instantiater = instantiater;
    this.resources = resources;
  }

  @SuppressWarnings("unchecked")
  public <C extends SubContent> ReportContentFactory<C> getFactory(Class<C> contentClass) {
    for (ReportContentFactory factory : instantiateFactories(contentClass)) {
      RegisteredReportContent annotation = factory.getClass().getAnnotation(RegisteredReportContent.class);
      if (annotation.produces().equals(contentClass)) {
        return (ReportContentFactory<C>) factory;
      }
    }
    throw new RuntimeException("Could not find content for " + contentClass.getName());
  }

  private <C extends SubContent> Collection<ReportContentFactory> instantiateFactories(Class<C> contentClass) {
    try {
      return instantiater.instantiateAll(RegisteredReportContent.class, resources);
    } catch (InitializationException e) {
      throw new RuntimeException("Could not create content for " + contentClass.getName(), e);
    }
  }
}