package net.sf.anathema.character.reporting.pdf.rendering.general.traits;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Position;

public interface INamedTraitEncoder {

  public float encode(PdfContentByte directContent, IGenericCharacter character, Position position, float width, float height);
}
