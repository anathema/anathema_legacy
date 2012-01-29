package net.sf.anathema.character.reporting.pdf.content.virtues;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = VirtueContent.class)
public class VirtueContentFactory implements ReportContentFactory<VirtueContent> {

  private final IResources resources;

  public VirtueContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public VirtueContent create(IGenericCharacter character, IGenericDescription description) {
    return new VirtueContent(resources, character);
  }
}
