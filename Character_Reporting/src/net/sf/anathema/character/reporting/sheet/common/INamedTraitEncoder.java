package net.sf.anathema.character.reporting.sheet.common;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.util.Position;

import com.lowagie.text.pdf.PdfContentByte;

public interface INamedTraitEncoder {

  public float encode(PdfContentByte directContent, IGenericCharacter character, Position position, float width, float height);
}