package net.sf.anathema.character.reporting.pdf.content.essence;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = RegainEssenceContent.class)
public class RegainEssenceContentFactory implements ReportContentFactory<RegainEssenceContent> {

  private IResources resources;

  public RegainEssenceContentFactory(IResources resources)  {
    this.resources = resources;
  }

  @Override
  public RegainEssenceContent create(ReportSession session, IGenericCharacter character,
          IGenericDescription description) {
    return new RegainEssenceContent(resources, character);
  }
}
