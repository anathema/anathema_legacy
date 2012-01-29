package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.lib.resources.IResources;

import java.util.Collection;

public class ReportContentRegistry {

  private IResources resources;
  private Instantiater instantiater;

  public ReportContentRegistry(Instantiater instantiater) {
    this.instantiater = instantiater;
  }

  public <C extends SubContent> ReportContentFactory<C> getFactory(Class<C> contentClass) {
    try {
      Collection<ReportContentFactory> contentFactories = instantiater.instantiateAll(RegisteredReportContent.class, IResources.class, resources);
      for (ReportContentFactory factory : contentFactories) {
        RegisteredReportContent annotation = factory.getClass().getAnnotation(RegisteredReportContent.class);
        if (annotation.produces().equals(contentClass)) {
          return (ReportContentFactory<C>) factory;
        }
      }
      throw new RuntimeException("Could not find content for " + contentClass.getName());
    } catch (InitializationException e) {
      throw new RuntimeException("Could not create content for " + contentClass.getName(), e);
    }
  }

  public void setResources(IResources resources) {
    this.resources = resources;
  }
}