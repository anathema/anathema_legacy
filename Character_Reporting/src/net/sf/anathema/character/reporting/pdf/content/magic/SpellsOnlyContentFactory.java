package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = SpellsOnlyContent.class)
public class SpellsOnlyContentFactory implements ReportContentFactory<SpellsOnlyContent> {

  private Resources resources;

  public SpellsOnlyContentFactory(Resources resources) {
      this.resources = resources;
    }

  @Override
  public SpellsOnlyContent create(ReportSession session, IGenericCharacter character, Hero hero) {
    return new SpellsOnlyContent(character, session, resources);
  }
}
