package net.sf.anathema.development.character.reporting;

import java.io.IOException;

import net.sf.anathema.development.character.reporting.page.PdfPageConfiguration;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfFirstPageEncoder {

  private final PdfPageConfiguration pageConfiguration = new PdfPageConfiguration();
  private final PdfBoxEncoder boxEncoder;

  public PdfFirstPageEncoder() throws DocumentException, IOException {
    this.boxEncoder = new PdfBoxEncoder();
  }

  public void encode(PdfContentByte directContent) {
    boxEncoder.encodeBox(directContent, pageConfiguration.getFirstColumnRectangle(0, 51, 2), "Personal Information");
    boxEncoder.encodeBox(directContent, pageConfiguration.getFirstColumnRectangle(61, 128, 1), "Attributes");
    boxEncoder.encodeBox(directContent, pageConfiguration.getFirstColumnRectangle(199, 393, 1), "Attributes");
    boxEncoder.encodeBox(directContent, pageConfiguration.getFirstColumnRectangle(602, 153, 1), "Combat");
    boxEncoder.encodeBox(directContent, pageConfiguration.getSecondColumnRectangle(61, 282, 1), "Middle Column");
    boxEncoder.encodeBox(directContent, pageConfiguration.getThirdColumnRectangle(0, 343), "Essence");
    boxEncoder.encodeBox(directContent, pageConfiguration.getSecondColumnRectangle(353, 170, 2), "Weaponry");
    boxEncoder.encodeBox(directContent, pageConfiguration.getSecondColumnRectangle(353, 170, 2), "Weaponry");
    boxEncoder.encodeBox(directContent, pageConfiguration.getSecondColumnRectangle(533, 51, 2), "Armour");
    boxEncoder.encodeBox(directContent, pageConfiguration.getSecondColumnRectangle(594, 119, 2), "Health");
    boxEncoder.encodeBox(directContent, pageConfiguration.getSecondColumnRectangle(721, 34, 2), "Combat Sequence");
  }
}