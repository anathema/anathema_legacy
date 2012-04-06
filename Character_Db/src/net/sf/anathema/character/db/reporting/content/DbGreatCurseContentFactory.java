package net.sf.anathema.character.db.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = DbGreatCurseContent.class)
public class DbGreatCurseContentFactory implements ReportContentFactory<DbGreatCurseContent> {

  private IResources resources;

  public DbGreatCurseContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public DbGreatCurseContent create(ReportSession session, IGenericCharacter character,
          IGenericDescription description) {
    return new DbGreatCurseContent(resources, character);
  }
}
