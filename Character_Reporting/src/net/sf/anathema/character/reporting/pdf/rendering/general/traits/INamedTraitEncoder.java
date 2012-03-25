package net.sf.anathema.character.reporting.pdf.rendering.general.traits;

import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public interface INamedTraitEncoder {

  public float encode(SheetGraphics graphics, ReportSession session, Position position, float width, float height);
}
