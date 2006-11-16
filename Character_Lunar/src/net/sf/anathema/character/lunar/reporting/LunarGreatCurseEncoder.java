package net.sf.anathema.character.lunar.reporting;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;

public class LunarGreatCurseEncoder implements IPdfContentBoxEncoder {

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    // TODO Auto-generated method stub

  }

  public String getHeaderKey() {
    // TODO Auto-generated method stub
    return null;
  }
}