package net.sf.anathema.hero.sheet.pdf.content;

import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

public interface ReportContentFactory<C extends SubContent> {

  C create(ReportSession session);
}
