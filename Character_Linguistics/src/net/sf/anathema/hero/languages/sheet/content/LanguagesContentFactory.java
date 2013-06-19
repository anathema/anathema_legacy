package net.sf.anathema.hero.languages.sheet.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = LanguagesContent.class)
public class LanguagesContentFactory implements ReportContentFactory<LanguagesContent> {

  private Resources resources;

  public LanguagesContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public LanguagesContent create(ReportSession session, IGenericCharacter character, Hero hero) {
    return new LanguagesContent(hero, resources);
  }
}
