package net.sf.anathema.hero.magic.sheet.content;

import net.sf.anathema.hero.sheet.pdf.content.RegisteredReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = CharmsOnlyContent.class)
public class CharmsOnlyContentFactory implements ReportContentFactory<CharmsOnlyContent> {

  private Resources resources;

  public CharmsOnlyContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public CharmsOnlyContent create(ReportSession session) {
    return new CharmsOnlyContent (session, resources);
  }
}
