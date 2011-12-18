package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;

public interface IPdfContentEncoder {

  public float encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds);
}
