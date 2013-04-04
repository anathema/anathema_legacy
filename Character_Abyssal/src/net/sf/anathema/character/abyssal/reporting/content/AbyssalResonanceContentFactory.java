package net.sf.anathema.character.abyssal.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = AbyssalResonanceContent.class)
public class AbyssalResonanceContentFactory implements ReportContentFactory<AbyssalResonanceContent> {

  private Resources resources;

  public AbyssalResonanceContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public AbyssalResonanceContent create(ReportSession session, IGenericCharacter character) {
    return new AbyssalResonanceContent(resources, character);
  }
}
