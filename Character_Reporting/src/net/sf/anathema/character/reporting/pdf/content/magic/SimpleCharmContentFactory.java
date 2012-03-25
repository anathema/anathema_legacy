package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = SimpleCharmContent.class)
public class SimpleCharmContentFactory implements ReportContentFactory<SimpleCharmContent> {

  private IResources resources;

  public SimpleCharmContentFactory(IResources resources) {
      this.resources = resources;
    }

  @Override
  public SimpleCharmContent create(ReportSession session, IGenericCharacter character,
          IGenericDescription description) {
    return new SimpleCharmContent(character, session, resources);
  }
}
