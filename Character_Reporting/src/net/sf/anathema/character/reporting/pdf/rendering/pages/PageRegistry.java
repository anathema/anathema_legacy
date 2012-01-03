package net.sf.anathema.character.reporting.pdf.rendering.pages;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

public class PageRegistry {

  public final List<PageFactory> factories = new ArrayList<PageFactory>();

  public void add(PageFactory factory) {
    factories.add(factory);
  }

  public PageEncoder[] createEncoders(PdfPageConfiguration configuration, EncoderRegistry encoderRegistry, IResources resources,
    ReportContent content) {
    return findFactory(content).create(encoderRegistry, resources, configuration);
  }

  private PageFactory findFactory(ReportContent content) {
    for (PageFactory factory : factories) {
      BasicContent basicContent = createBasicContent(content);
      if (factory.supports(basicContent)) {
        return factory;
      }
    }
    return new NullPageFactory();
  }

  private BasicContent createBasicContent(ReportContent content) {
    return content.createSubContent(BasicContent.class);
  }
}
