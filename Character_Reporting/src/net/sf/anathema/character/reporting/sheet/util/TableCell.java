package net.sf.anathema.character.reporting.sheet.util;

import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class TableCell extends PdfPCell {


  public TableCell(PdfPTable table, int border) {
    super(table);
    setBorder(border);
  }

  public TableCell(Phrase phrase, int border) {
    super(phrase);
    setBorder(border);
  }
}