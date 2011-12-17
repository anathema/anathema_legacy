package net.sf.anathema.character.reporting.common.encoder;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.common.Bounds;

public interface IPdfTableEncoder {

  public float encodeTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException;

  public boolean hasContent(IGenericCharacter character);

}
