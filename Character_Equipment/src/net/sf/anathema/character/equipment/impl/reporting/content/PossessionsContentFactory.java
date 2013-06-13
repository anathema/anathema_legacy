package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = PossessionsContent.class)
public class PossessionsContentFactory implements ReportContentFactory<PossessionsContent> {

  private Resources resources;

  public PossessionsContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public PossessionsContent create(ReportSession session, IGenericCharacter character) {
    return new PossessionsContent(resources, character);
  }
}
