package net.sf.anathema.hero.spells.sheet.content;

import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

@Produces(SpellsOnlyContent.class)
public class SpellsOnlyContentFactory implements ReportContentFactory<SpellsOnlyContent> {

  private Resources resources;

  public SpellsOnlyContentFactory(Resources resources) {
      this.resources = resources;
    }

  @Override
  public SpellsOnlyContent create(ReportSession session) {
    return new SpellsOnlyContent(session, resources);
  }
}
