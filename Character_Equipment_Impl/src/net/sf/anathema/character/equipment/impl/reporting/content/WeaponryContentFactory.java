package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = WeaponryContent.class)
public class WeaponryContentFactory implements ReportContentFactory<WeaponryContent>{

  private IResources resources;

  public WeaponryContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public WeaponryContent create(IGenericCharacter character, IGenericDescription description) {
    return new WeaponryContent(resources, character);
  }
}
