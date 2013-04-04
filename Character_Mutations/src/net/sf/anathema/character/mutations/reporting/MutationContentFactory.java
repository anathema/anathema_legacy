package net.sf.anathema.character.mutations.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = MutationContent.class)
public class MutationContentFactory implements ReportContentFactory<MutationContent> {
  private Resources resources;

  public MutationContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override

  public MutationContent create(ReportSession session, IGenericCharacter character) {
    return new MutationContent(resources, character);
  }
}
