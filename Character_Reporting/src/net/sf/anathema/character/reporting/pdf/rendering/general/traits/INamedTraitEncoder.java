package net.sf.anathema.character.reporting.pdf.rendering.general.traits;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;

public interface INamedTraitEncoder {

  public float encode(SheetGraphics graphics, ReportContent content, Position position, float width, float height);
}
