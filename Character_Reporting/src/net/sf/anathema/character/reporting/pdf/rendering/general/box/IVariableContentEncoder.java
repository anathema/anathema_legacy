package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public interface IVariableContentEncoder extends ContentEncoder {

  float getRequestedHeight(SheetGraphics graphics, ReportSession session, float width);
}
