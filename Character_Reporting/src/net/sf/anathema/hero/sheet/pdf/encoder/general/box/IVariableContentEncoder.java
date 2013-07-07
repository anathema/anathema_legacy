package net.sf.anathema.hero.sheet.pdf.encoder.general.box;

import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;

public interface IVariableContentEncoder extends ContentEncoder {

  float getRequestedHeight(SheetGraphics graphics, ReportSession session, float width);
}
