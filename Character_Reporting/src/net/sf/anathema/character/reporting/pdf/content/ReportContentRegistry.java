package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.lib.registry.Registry;

public class ReportContentRegistry {

  private final Registry<Class, ReportContentFactory> registry = new Registry<Class, ReportContentFactory>();

  public <C extends SubContent> void addFactory(Class<C> contentClass, ReportContentFactory<C> factory) {
    registry.register(contentClass, factory);
  }

  public <C extends SubContent> ReportContentFactory<C> getFactory(Class<C> contentClass) {
    return registry.get(contentClass);
  }
}
