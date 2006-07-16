package net.sf.anathema.character.reporting.sheet.common;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.util.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public interface IPdfContentBoxEncoder {
  
  public String getHeaderKey();

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException;
}