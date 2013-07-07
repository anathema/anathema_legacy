package net.sf.anathema.hero.sheet.pdf.encoder.general.box;

import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.encoder.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;

public interface IContentEncoder {

  float encode(SheetGraphics graphics, ReportSession session, Bounds bounds);
}
