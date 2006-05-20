package net.sf.anathema.character.reporting.sheet.common;

import java.io.IOException;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.character.IGenericCharacter;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public interface IPdfContentEncoder {

  public void encode(PdfContentByte directContent, IGenericCharacter character, SmartRectangle contentBounds)
      throws DocumentException, IOException;
}