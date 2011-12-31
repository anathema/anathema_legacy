package net.sf.anathema.character.abyssal.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class Abyssal1stResonanceContentFactory implements ReportContentFactory<Abyssal1stResonanceContent>{

  private IResources resources;

  public Abyssal1stResonanceContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public Abyssal1stResonanceContent create(IGenericCharacter character, IGenericDescription description) {
    return new Abyssal1stResonanceContent(resources, character);
  }
}
