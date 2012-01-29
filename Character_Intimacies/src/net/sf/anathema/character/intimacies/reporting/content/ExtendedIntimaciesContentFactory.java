package net.sf.anathema.character.intimacies.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = ExtendedIntimaciesContent.class)
public class ExtendedIntimaciesContentFactory implements ReportContentFactory<ExtendedIntimaciesContent> {
  private IResources resources;

  public ExtendedIntimaciesContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override

  public ExtendedIntimaciesContent create(IGenericCharacter character, IGenericDescription description) {
    return new ExtendedIntimaciesContent(resources, character);
  }
}
