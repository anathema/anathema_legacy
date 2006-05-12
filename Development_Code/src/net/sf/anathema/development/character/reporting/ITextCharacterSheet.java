package net.sf.anathema.development.character.reporting;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class ITextCharacterSheet {

  public static void main(String[] args) {
    Document document = new Document(PageSize.A4, 40, 40, 15, 15);
    try {
      System.err.println(document.getPageSize());
      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("iText.pdf"));
      document.open();
      PdfContentByte directContent = writer.getDirectContent();
      new PdfFirstPageEncoder().encode(directContent);
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
}