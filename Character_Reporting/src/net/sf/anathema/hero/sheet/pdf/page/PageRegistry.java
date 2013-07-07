package net.sf.anathema.hero.sheet.pdf.page;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.EncoderRegistry;
import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.Resources;

import java.util.Collection;
import java.util.Collections;

public class PageRegistry {
  private static final Logger logger = Logger.getLogger(PageRegistry.class);
  private ObjectFactory objectFactory;

  public PageRegistry(ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
  }

  public PageEncoder[] createEncoders(PageSize pageSize, EncoderRegistry encoderRegistry, Resources resources,
          ReportSession session) {
    return findFactory(session).create(encoderRegistry, resources, pageSize);
  }

  private PageFactory findFactory(ReportSession session) {
    for (PageFactory factory : createPageFactories()) {
      BasicContent basicContent = createBasicContent(session);
      if (factory.supports(basicContent)) {
        return factory;
      }
    }
    return new NullPageFactory();
  }

  private Collection<PageFactory> createPageFactories() {
    try {
      return objectFactory.instantiateAll(RegisteredAdditionalPage.class);
    } catch (InitializationException e) {
      logger.error("Error instantiating additional pages.", e);
      return Collections.emptyList();
    }
  }

  private BasicContent createBasicContent(ReportSession session) {
    return session.createContent(BasicContent.class);
  }
}
