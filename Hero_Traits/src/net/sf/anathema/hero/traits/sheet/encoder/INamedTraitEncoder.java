package net.sf.anathema.hero.traits.sheet.encoder;

import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.encoder.Position;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;

public interface INamedTraitEncoder {

  float encode(SheetGraphics graphics, ReportSession session, Position position, float width, float height);
}
