package net.sf.anathema.hero.sheet.pdf.content;

import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.util.ObjectFactoryMap;

public class ReportContentRegistry {

  private final ObjectFactoryMap<ReportContentFactory> map;

  public ReportContentRegistry(ObjectFactory objectFactory, Resources resources) {
    this.map = new ObjectFactoryMap<>(objectFactory, ReportContentFactory.class, resources);
  }

  @SuppressWarnings("unchecked")
  public <C extends SubContent> ReportContentFactory<C> getFactory(Class<C> contentClass) {
    return map.get(contentClass);
  }
}