package net.sf.anathema.character.linguistics.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = LinguisticsContent.class)
public class LinguisticsContentFactory implements ReportContentFactory<LinguisticsContent> {

  private Resources resources;

  public LinguisticsContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public LinguisticsContent create(ReportSession session, IGenericCharacter character, Hero hero) {
    return new LinguisticsContent(resources, character);
  }
}
