package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = ShieldContent.class)
public class ShieldContentFactory implements ReportContentFactory<ShieldContent> {

  private IResources resources;

  public ShieldContentFactory(IResources resources){
    this.resources = resources;
  }

  @Override
  public ShieldContent create(IGenericCharacter character, IGenericDescription description) {
    return new ShieldContent(resources, character);
  }
}
