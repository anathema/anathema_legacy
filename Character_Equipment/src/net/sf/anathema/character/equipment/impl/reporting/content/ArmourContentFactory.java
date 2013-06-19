package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = ArmourContent.class)
public class ArmourContentFactory implements ReportContentFactory<ArmourContent> {

  private Resources resources;

  public ArmourContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public ArmourContent create(ReportSession session, IGenericCharacter character, Hero hero) {
    return new ArmourContent(resources, character);
  }
}
