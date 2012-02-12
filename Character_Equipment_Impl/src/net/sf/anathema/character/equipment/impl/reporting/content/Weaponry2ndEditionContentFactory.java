package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = Weaponry2ndEditionContent.class)
public class Weaponry2ndEditionContentFactory implements ReportContentFactory<Weaponry2ndEditionContent> {

  private IResources resources;

  public Weaponry2ndEditionContentFactory(IResources resources){
    this.resources = resources;
  }

  @Override
  public Weaponry2ndEditionContent create(IGenericCharacter character, IGenericDescription description) {
    return new Weaponry2ndEditionContent(resources, character);
  }
}
