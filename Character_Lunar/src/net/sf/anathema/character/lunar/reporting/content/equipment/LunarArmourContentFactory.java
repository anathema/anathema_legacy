package net.sf.anathema.character.lunar.reporting.content.equipment;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = LunarArmourContent.class)
public class LunarArmourContentFactory implements ReportContentFactory<LunarArmourContent> {

  private Resources resources;

  public LunarArmourContentFactory(Resources resources)  {
    this.resources = resources;
  }

  @Override
  public LunarArmourContent create(ReportSession session, IGenericCharacter character) {
    return new LunarArmourContent(resources, character);
  }
}
