package net.sf.anathema.character.db.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = DbGreatCurseContent.class)
public class DbGreatCurseContentFactory implements ReportContentFactory<DbGreatCurseContent> {

  private Resources resources;

  public DbGreatCurseContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public DbGreatCurseContent create(ReportSession session, IGenericCharacter character) {
    return new DbGreatCurseContent(resources, character);
  }
}
