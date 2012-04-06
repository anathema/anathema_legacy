package net.sf.anathema.character.reporting.pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;

import static com.itextpdf.text.Font.BOLD;
import static com.itextpdf.text.Font.NORMAL;

public final class MagicPartFactory {

  private static final int STANDARD_FONT_SIZE = 10;
  private static final int TITLE_FONT_SIZE = 14;
  private static final int GROUP_FONT_SIZE = 18;
  public static final int DESCRIPTION_FIRST_LINE_INDENT = 15;
  public static final int TITLE_LEADING = 25;
  private final PdfReportUtils utils;

  public MagicPartFactory(PdfReportUtils utils) {
    this.utils = utils;
  }

  public Element createFirstGroupTitle(String groupTitle) {
    Paragraph paragraph = createGroupTitle(groupTitle);
    paragraph.setLeading(0f);
    return paragraph;
  }

  public Paragraph createGroupTitle(String groupTitle) {
    Chunk title = new Chunk(groupTitle, utils.createDefaultFont(GROUP_FONT_SIZE, BOLD));
    Paragraph paragraph = new Paragraph(TITLE_LEADING, title);
    paragraph.setAlignment(Element.ALIGN_CENTER);
    return paragraph;
  }

  public Paragraph createCharmTitle(String charmName) {
    Chunk title = new Chunk(charmName, utils.createDefaultFont(TITLE_FONT_SIZE, BOLD));
    return new Paragraph(TITLE_LEADING, title);
  }

  public Paragraph createDescriptionParagraph(String text) {
    Chunk chunk = new Chunk(text, utils.createDefaultFont(STANDARD_FONT_SIZE, NORMAL));
    Paragraph paragraph = new Paragraph(chunk);
    paragraph.setFirstLineIndent(DESCRIPTION_FIRST_LINE_INDENT);
    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
    return paragraph;
  }

  public PdfPTable createDataTable() {
    float[] columnWidths = new float[]{2f, 2f};
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    return table;
  }

  public PdfPCell createDataCell(String title, String value) {
    Phrase phrase = createDataPhrase(title, value);
    PdfPCell cell = new PdfPCell(phrase);
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
  }

  public Phrase createDataPhrase(String title, String value) {
    Phrase phrase = new Phrase();
    phrase.add(new Chunk(title, utils.createDefaultFont(STANDARD_FONT_SIZE, BOLD)));
    phrase.add(new Chunk(value, utils.createDefaultFont(STANDARD_FONT_SIZE, NORMAL)));
    return phrase;
  }

  public PdfPCell createDoubleDataCell(String title, String value) {
    PdfPCell dataCell = createDataCell(title, value);
    dataCell.setColspan(2);
    return dataCell;
  }
}
