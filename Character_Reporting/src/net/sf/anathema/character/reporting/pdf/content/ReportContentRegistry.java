package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.lib.registry.Registry;

public class ReportContentRegistry {

  private final Registry<Class, IReportContentFactory> registry = new Registry<Class, IReportContentFactory>();

  public <C extends ISubContent> void addFactory(Class<C> contentClass, IReportContentFactory<C> factory) {
    registry.register(contentClass, factory);
  }

  public <C extends ISubContent> IReportContentFactory<C> getFactory(Class<C> contentClass) {
    return registry.get(contentClass);
  }
}
