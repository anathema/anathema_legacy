package net.sf.anathema.hero.combos.sheet.content;

import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

@Produces(ComboContent.class)
public class ComboContentFactory implements ReportContentFactory<ComboContent> {
  private Resources resources;

  public ComboContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public ComboContent create(ReportSession session) {
    return new ComboContent(session.getHero(), resources);
  }
}
