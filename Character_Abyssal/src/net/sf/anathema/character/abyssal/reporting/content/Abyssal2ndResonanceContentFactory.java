package net.sf.anathema.character.abyssal.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = Abyssal2ndResonanceContent.class)
public class Abyssal2ndResonanceContentFactory implements ReportContentFactory<Abyssal2ndResonanceContent> {

  private IResources resources;

  public Abyssal2ndResonanceContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public Abyssal2ndResonanceContent create(IGenericCharacter character, IGenericDescription description) {
    return new Abyssal2ndResonanceContent(resources, character);
  }
}
