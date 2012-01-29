package net.sf.anathema.character.solar.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = VirtueFlawContent.class)
public class VirtueFlawContentFactory implements ReportContentFactory<VirtueFlawContent> {

  private IResources resources;

  public VirtueFlawContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public VirtueFlawContent create(IGenericCharacter character, IGenericDescription description) {
    return new VirtueFlawContent(resources, character);
  }
}
