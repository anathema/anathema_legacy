package net.sf.anathema.character.meritsflaws.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = MeritsAndFlawsContent.class)
public class MeritsAndFlawsContentFactory implements ReportContentFactory<MeritsAndFlawsContent> {
  private IResources resources;

  public MeritsAndFlawsContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public MeritsAndFlawsContent create(IGenericCharacter character, IGenericDescription description) {
    return new MeritsAndFlawsContent(resources, character);
  }
}