package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = CharmsOnlyContent.class)
public class CharmsOnlyContentFactory implements ReportContentFactory<CharmsOnlyContent> {

  private Resources resources;

  public CharmsOnlyContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public CharmsOnlyContent create(ReportSession session, IGenericCharacter character, Hero hero) {
    return new CharmsOnlyContent (character, session, resources);
  }
}
