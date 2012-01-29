package net.sf.anathema.character.reporting.pdf.rendering.pages;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

import java.util.Collection;
import java.util.Collections;

public class PageRegistry {
  private static final Logger logger = Logger.getLogger(PageRegistry.class);
  private Instantiater instantiater;

  public PageRegistry(Instantiater instantiater) {
    this.instantiater = instantiater;
  }

  public PageEncoder[] createEncoders(PageConfiguration configuration, EncoderRegistry encoderRegistry, IResources resources,
                                      ReportContent content) {
    return findFactory(content).create(encoderRegistry, resources, configuration);
  }

  private PageFactory findFactory(ReportContent content) {
    for (PageFactory factory : createPageFactories()) {
      BasicContent basicContent = createBasicContent(content);
      if (factory.supports(basicContent)) {
        return factory;
      }
    }
    return new NullPageFactory();
  }

  private Collection<PageFactory> createPageFactories() {
    try {
      return instantiater.instantiateAll(RegisteredAdditionalPage.class);
    } catch (InitializationException e) {
      logger.error("Error instantiating additional pages.", e);
      return Collections.emptyList();
    }
  }

  private BasicContent createBasicContent(ReportContent content) {
    return content.createSubContent(BasicContent.class);
  }
}
