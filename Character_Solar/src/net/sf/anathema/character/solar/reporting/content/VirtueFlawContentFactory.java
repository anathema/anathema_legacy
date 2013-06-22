package net.sf.anathema.character.solar.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = VirtueFlawContent.class)
public class VirtueFlawContentFactory implements ReportContentFactory<VirtueFlawContent> {

  private Resources resources;

  public VirtueFlawContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public VirtueFlawContent create(ReportSession session, IGenericCharacter character, Hero hero) {
    return new VirtueFlawContent(hero, resources);
  }
}
