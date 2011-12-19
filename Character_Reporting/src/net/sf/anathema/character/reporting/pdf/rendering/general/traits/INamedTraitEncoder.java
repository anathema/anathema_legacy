package net.sf.anathema.character.reporting.pdf.rendering.general.traits;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;

public interface INamedTraitEncoder {

  public float encode(SheetGraphics graphics, IGenericCharacter character, Position position, float width, float height);
}
