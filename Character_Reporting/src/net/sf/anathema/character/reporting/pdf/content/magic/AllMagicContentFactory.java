package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = AllMagicContent.class)
public class AllMagicContentFactory implements ReportContentFactory<AllMagicContent> {

  private Resources resources;

  public AllMagicContentFactory(Resources resources) {
      this.resources = resources;
    }

  @Override
  public AllMagicContent create(ReportSession session) {
    return new AllMagicContent(session.getCharacter(), session, resources);
  }
}
