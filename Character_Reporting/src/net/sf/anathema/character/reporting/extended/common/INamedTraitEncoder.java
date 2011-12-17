package net.sf.anathema.character.reporting.extended.common;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.util.Position;

public interface INamedTraitEncoder {

  public float encode(PdfContentByte directContent, IGenericCharacter character, Position position, float width, float height);
}