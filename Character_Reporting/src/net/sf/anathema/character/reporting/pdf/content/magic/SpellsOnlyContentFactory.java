package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = SpellsOnlyContent.class)
public class SpellsOnlyContentFactory implements ReportContentFactory<SpellsOnlyContent> {

  private IResources resources;

  public SpellsOnlyContentFactory(IResources resources) {
      this.resources = resources;
    }

  @Override
  public SpellsOnlyContent create(ReportSession session, IGenericCharacter character,
          IGenericDescription description) {
    return new SpellsOnlyContent(character, session, resources);
  }
}
