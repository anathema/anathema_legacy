package net.sf.anathema.character.reporting.pdf.content.essence;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = SimpleEssenceContent.class)
public class SimpleEssenceContentFactory implements ReportContentFactory<SimpleEssenceContent> {

  private IResources resources;

  public SimpleEssenceContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public SimpleEssenceContent create(IGenericCharacter character, IGenericDescription description) {
    return new SimpleEssenceContent(resources, character);
  }
}
