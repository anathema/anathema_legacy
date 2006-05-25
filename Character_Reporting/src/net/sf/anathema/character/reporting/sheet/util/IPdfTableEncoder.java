package net.sf.anathema.character.reporting.sheet.util;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.util.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public interface IPdfTableEncoder {

  public void encodeTable(PdfContentByte directContent, IGenericCharacter character, Bounds bounds)
      throws DocumentException;

}