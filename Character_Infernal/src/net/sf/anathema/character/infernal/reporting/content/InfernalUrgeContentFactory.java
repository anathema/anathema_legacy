package net.sf.anathema.character.infernal.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = InfernalUrgeContent.class)
public class InfernalUrgeContentFactory implements ReportContentFactory<InfernalUrgeContent> {
  private Resources resources;

  public InfernalUrgeContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public InfernalUrgeContent create(ReportSession session, IGenericCharacter character) {
    return new InfernalUrgeContent(resources, character);
  }
}