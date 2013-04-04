package net.sf.anathema.character.ghost.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = GhostFetterContent.class)
public class GhostFetterContentFactory implements ReportContentFactory<GhostFetterContent> {
  private Resources resources;

  public GhostFetterContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override

  public GhostFetterContent create(ReportSession session, IGenericCharacter character) {
    return new GhostFetterContent(resources, character);
  }
}
