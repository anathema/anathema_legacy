package net.sf.anathema.character.lunar.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = GiftContent.class)
public class GiftContentFactory implements ReportContentFactory<GiftContent> {

  private Resources resources;

  public GiftContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public GiftContent create(ReportSession session, IGenericCharacter character) {
    return new GiftContent(resources, character);
  }
}
