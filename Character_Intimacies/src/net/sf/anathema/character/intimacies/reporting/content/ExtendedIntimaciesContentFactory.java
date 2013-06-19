package net.sf.anathema.character.intimacies.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = ExtendedIntimaciesContent.class)
public class ExtendedIntimaciesContentFactory implements ReportContentFactory<ExtendedIntimaciesContent> {
  private Resources resources;

  public ExtendedIntimaciesContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public ExtendedIntimaciesContent create(ReportSession session, IGenericCharacter character, Hero hero) {
    return new ExtendedIntimaciesContent(resources, character);
  }
}
