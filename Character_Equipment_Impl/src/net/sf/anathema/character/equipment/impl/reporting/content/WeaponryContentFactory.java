package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = WeaponryContent.class)
public class WeaponryContentFactory implements ReportContentFactory<WeaponryContent> {

  private Resources resources;

  public WeaponryContentFactory(Resources resources){
    this.resources = resources;
  }

  @Override
  public WeaponryContent create(ReportSession session, IGenericCharacter character) {
    return new WeaponryContent(resources, character);
  }
}
