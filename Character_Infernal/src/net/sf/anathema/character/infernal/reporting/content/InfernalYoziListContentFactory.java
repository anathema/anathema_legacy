package net.sf.anathema.character.infernal.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = InfernalYoziListContent.class)
public class InfernalYoziListContentFactory implements ReportContentFactory<InfernalYoziListContent>{

  private Resources resources;

  public InfernalYoziListContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public InfernalYoziListContent create(ReportSession session, IGenericCharacter character) {
    return new InfernalYoziListContent(character, resources);
  }
}