package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public interface IPdfPageEncoder {

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description)
      throws DocumentException;

}