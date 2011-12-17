package net.sf.anathema.character.reporting.sheet.common;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.common.Position;

public interface INamedTraitEncoder {

  public int encode(PdfContentByte directContent, IGenericCharacter character, Position position, float width);
}
