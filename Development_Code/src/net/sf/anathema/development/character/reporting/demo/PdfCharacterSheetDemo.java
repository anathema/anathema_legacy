package net.sf.anathema.development.character.reporting.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.development.character.reporting.PdfFirstPageEncoder;
import net.sf.anathema.development.character.reporting.SecondEditionPartEncoder;
import net.sf.anathema.framework.repository.tree.demo.DemoResources;
import net.sf.anathema.lib.control.BrowserControl;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class PdfCharacterSheetDemo {

  public static void main(String[] args) {
    Document document = new Document(PageSize.A4, 40, 40, 15, 15);
    try {
      System.err.println(document.getPageSize());
      File outputStream = new File("iText.pdf");
      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputStream));
      document.open();
      PdfContentByte directContent = writer.getDirectContent();
      IGenericCharacter character = new DemoGenericCharacter();
      new PdfFirstPageEncoder(new SecondEditionPartEncoder(new DemoResources(), 7)).encode(directContent, character);
      BrowserControl.displayUrl(outputStream.toURL());
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