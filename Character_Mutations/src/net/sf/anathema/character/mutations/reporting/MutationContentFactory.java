package net.sf.anathema.character.mutations.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = MutationContent.class)
public class MutationContentFactory implements ReportContentFactory<MutationContent> {
  private IResources resources;

  public MutationContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override

  public MutationContent create(IGenericCharacter character, IGenericDescription description) {
    return new MutationContent(resources, character);
  }
}
