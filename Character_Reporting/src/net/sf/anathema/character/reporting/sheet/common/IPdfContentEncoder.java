package net.sf.anathema.character.reporting.sheet.common;

import java.io.IOException;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.util.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public interface IPdfContentEncoder {

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds)
      throws DocumentException,
      IOException;
}