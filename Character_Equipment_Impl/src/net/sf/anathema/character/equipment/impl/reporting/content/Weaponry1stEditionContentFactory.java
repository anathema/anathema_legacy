package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = Weaponry1stEditionContent.class)
public class Weaponry1stEditionContentFactory implements ReportContentFactory<Weaponry1stEditionContent> {

  private IResources resources;

  public Weaponry1stEditionContentFactory(IResources resources){
    this.resources = resources;
  }

  @Override
  public Weaponry1stEditionContent create(IGenericCharacter character, IGenericDescription description) {
    return new Weaponry1stEditionContent(resources, character);
  }
}
