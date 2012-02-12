package net.sf.anathema.character.reporting.pdf.content.essence;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;
@RegisteredReportContent(produces = ExtendedEssenceContent.class)
public class ExtendedEssenceContentFactory implements ReportContentFactory<ExtendedEssenceContent> {

  private IResources resources;

  public ExtendedEssenceContentFactory(IResources resources)  {
    this.resources = resources;
  }

  @Override
  public ExtendedEssenceContent create(IGenericCharacter character, IGenericDescription description) {
    return new ExtendedEssenceContent(resources, character);
  }
}
