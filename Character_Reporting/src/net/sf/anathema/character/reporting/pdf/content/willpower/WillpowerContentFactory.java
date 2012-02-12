package net.sf.anathema.character.reporting.pdf.content.willpower;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = WillpowerContent.class)
public class WillpowerContentFactory implements ReportContentFactory<WillpowerContent> {

  private final IResources resources;

  public WillpowerContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public WillpowerContent create(IGenericCharacter character, IGenericDescription description) {
    return new WillpowerContent(resources, character);
  }
}
