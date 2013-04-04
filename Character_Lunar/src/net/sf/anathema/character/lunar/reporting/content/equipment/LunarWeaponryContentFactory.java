package net.sf.anathema.character.lunar.reporting.content.equipment;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = LunarWeaponryContent.class)
public class LunarWeaponryContentFactory implements ReportContentFactory<LunarWeaponryContent> {

  private Resources resources;

  public LunarWeaponryContentFactory(Resources resources)  {
    this.resources = resources;
  }

  @Override
  public LunarWeaponryContent create(ReportSession session, IGenericCharacter character) {
    return new LunarWeaponryContent(resources, character);
  }
}
