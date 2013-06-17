package net.sf.anathema.hero.othertraits.sheet.virtues.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = VirtueContent.class)
public class VirtueContentFactory implements ReportContentFactory<VirtueContent> {

  private final Resources resources;

  public VirtueContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public VirtueContent create(ReportSession session, IGenericCharacter character) {
    return new VirtueContent(resources, character);
  }
}
