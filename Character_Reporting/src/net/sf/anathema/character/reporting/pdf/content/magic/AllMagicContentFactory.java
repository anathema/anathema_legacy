package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = AllMagicContent.class)
public class AllMagicContentFactory implements ReportContentFactory<AllMagicContent> {

  private IResources resources;

  public AllMagicContentFactory(IResources resources) {
      this.resources = resources;
    }

  @Override
  public AllMagicContent create(ReportSession session, IGenericCharacter character,
          IGenericDescription description) {
    return new AllMagicContent(character, session, resources);
  }
}
