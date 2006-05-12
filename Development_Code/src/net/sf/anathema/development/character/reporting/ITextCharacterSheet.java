package net.sf.anathema.development.character.reporting;

import java.io.FileOutputStream;
import java.io.IOException;

import net.disy.commons.core.geometry.SmartRectangle;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class ITextCharacterSheet {

  /**
   * Draws some concentric circles.
   * @param args no arguments needed
   */
  public static void main(String[] args) {
    Document document = new Document();
    try {
      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("iText.pdf"));
      document.open();
      PdfContentByte directContent = writer.getDirectContent();
      drawBox(directContent, new SmartRectangle(100, 700, 200, 50), "Amber");
    }
    catch (DocumentException de) {
      System.err.println(de.getMessage());
    }
    catch (IOException ioe) {
      System.err.println(ioe.getMessage());
    }
    finally {
      document.close();
    }
  }

  private static void drawBox(PdfContentByte directContent, SmartRectangle boxBounds, String title)
      throws DocumentException,
      IOException {
    new BoxEncoder().encodeBox(directContent, boxBounds, title);
  }
}