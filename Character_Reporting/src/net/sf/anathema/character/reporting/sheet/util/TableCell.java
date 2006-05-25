package net.sf.anathema.character.reporting.sheet.util;

import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;

public class TableCell extends PdfPCell {

  public TableCell(Phrase phrase, int border) {
    super(phrase);
    setBorder(border);
  }
}