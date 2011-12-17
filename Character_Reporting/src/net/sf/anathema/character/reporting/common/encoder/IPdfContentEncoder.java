package net.sf.anathema.character.reporting.common.encoder;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.common.Bounds;

public interface IPdfContentEncoder {

  public float encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds);
}
