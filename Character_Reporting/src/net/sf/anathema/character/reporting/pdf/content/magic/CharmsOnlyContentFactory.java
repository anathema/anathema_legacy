package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = CharmsOnlyContent.class)
public class CharmsOnlyContentFactory implements ReportContentFactory<CharmsOnlyContent> {

  private IResources resources;

  public CharmsOnlyContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public CharmsOnlyContent create(ReportSession session, IGenericCharacter character, IGenericDescription description) {
    return new CharmsOnlyContent (character, session, resources);
  }
}
