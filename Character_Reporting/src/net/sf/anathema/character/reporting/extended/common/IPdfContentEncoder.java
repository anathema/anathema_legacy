package net.sf.anathema.character.reporting.extended.common;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.util.Bounds;

public interface IPdfContentEncoder {

  public float encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds);
}