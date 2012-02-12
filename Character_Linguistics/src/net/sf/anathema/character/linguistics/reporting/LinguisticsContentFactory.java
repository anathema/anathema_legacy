package net.sf.anathema.character.linguistics.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = LinguisticsContent.class)
public class LinguisticsContentFactory implements ReportContentFactory<LinguisticsContent> {

  private IResources resources;

  public LinguisticsContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public LinguisticsContent create(IGenericCharacter character, IGenericDescription description) {
    return new LinguisticsContent(resources, character);
  }
}