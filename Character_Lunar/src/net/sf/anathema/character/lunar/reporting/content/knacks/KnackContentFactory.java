package net.sf.anathema.character.lunar.reporting.content.knacks;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = KnackContent.class)
public class KnackContentFactory implements ReportContentFactory<KnackContent> {

  private Resources resources;

  public KnackContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public KnackContent create(ReportSession session, IGenericCharacter character) {
    return new KnackContent(resources, character);
  }
}
