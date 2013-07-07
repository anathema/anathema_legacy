package net.sf.anathema.herotype.solar.sheet.curse;

import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = VirtueFlawContent.class)
public class VirtueFlawContentFactory implements ReportContentFactory<VirtueFlawContent> {

  private Resources resources;

  public VirtueFlawContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public VirtueFlawContent create(ReportSession session) {
    return new VirtueFlawContent(session.getHero(), resources);
  }
}
