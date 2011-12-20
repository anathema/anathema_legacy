package net.sf.anathema.character.reporting.pdf.rendering.general.traits;

import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Position;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public interface INamedTraitEncoder {

  public float encode(SheetGraphics graphics, ReportContent content, Position position, float width, float height);
}
