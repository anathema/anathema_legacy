package net.sf.anathema.character.reporting.pdf.content.combo;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = ComboContent.class)
public class ComboContentFactory implements ReportContentFactory<ComboContent> {
  private Resources resources;

  public ComboContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public ComboContent create(ReportSession session, IGenericCharacter character) {
    return new ComboContent(character, resources);
  }
}
