package net.sf.anathema.campaign.reporting;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfPageLabels;
import com.lowagie.text.pdf.PdfWriter;
import net.sf.anathema.framework.reporting.ITextReportUtils;

import java.util.ArrayList;
import java.util.List;

public class TableOfContentsPrinter {
  private final ITextReportUtils reportUtils = new ITextReportUtils();
  private final List<ContentEntry> entryList = new ArrayList<ContentEntry>();
  private final SeriesReportUtils seriesUtils = new SeriesReportUtils();

  public void addEntry(String entry, int page) {
    entryList.add(new ContentEntry(entry, page));
  }

  public void performPrint(String seriesTitle, String tableTitle, Document document, PdfWriter writer)
          throws DocumentException {
    final int lastContentPage = writer.getPageNumber();
    writer.setPageEvent(null);
    writer.setPageEvent(new PdfPageEventHelper() {
      @Override
      public void onEndPage(PdfWriter currentWriter, Document currentDocument) {
        seriesUtils.printPageNumber(currentWriter, currentDocument, new RomanNumber(currentWriter.getPageNumber()
                - lastContentPage).getLowerCaseRoman());
      }
    });
    Paragraph titleParagraph = reportUtils.createNewParagraph(seriesTitle, Element.ALIGN_CENTER, Font.BOLD);
    titleParagraph.getFont().setSize(15);
    document.add(titleParagraph);
    Paragraph tocParagraph = reportUtils.createNewParagraph(tableTitle, Element.ALIGN_CENTER, Font.BOLD);
    tocParagraph.getFont().setSize(13);
    document.add(tocParagraph);
    float yCoordinate = document.top() - 35;
    yCoordinate -= 15;
    for (ContentEntry entry : entryList) {
      seriesUtils.textLine(writer.getDirectContent(), yCoordinate, document.left(), document.right(), ".", //$NON-NLS-1$
              reportUtils.createDefaultFont(11, Font.NORMAL),
              entry.getText(),
              entry.getPageAsString(),
              PdfAction.gotoLocalPage(entry.getText(), false));
      yCoordinate -= 15;
      if (yCoordinate < document.bottom()) {
        document.newPage();
        yCoordinate = document.top() - 15;
      }
    }
    reorderPages(document, writer, lastContentPage);
  }

  private void reorderPages(Document document, PdfWriter writer, final int lastContentPage) throws DocumentException {
    document.newPage();
    int totalPages = writer.getPageNumber() - 1;
    int tocPageLength = totalPages - lastContentPage;
    int reorder[] = new int[totalPages];
    for (int index = 0; index < tocPageLength; ++index) {
      reorder[index] = lastContentPage + index + 1;
    }
    for (int index = 0; index < lastContentPage; ++index) {
      reorder[tocPageLength + index] = index + 1;
    }
    writer.reorderPages(reorder);
    PdfPageLabels pageLabels = new PdfPageLabels();
    pageLabels.addPageLabel(1, PdfPageLabels.LOWERCASE_ROMAN_NUMERALS);
    pageLabels.addPageLabel(totalPages - lastContentPage + 1, PdfPageLabels.DECIMAL_ARABIC_NUMERALS);
    writer.setPageLabels(pageLabels);
  }

  public void reset() {
    entryList.clear();
  }
}
