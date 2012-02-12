package net.sf.anathema.campaign.reporting;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.MultiColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.framework.itemdata.model.IBasicItemData;
import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.styledtext.model.ITextPart;

public class NoteReport extends AbstractPdfReport {

  private final PdfReportUtils reportUtils = new PdfReportUtils();

  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException {
    if (!supports(item)) {
      throw new IllegalArgumentException("Item not supported: " + item.getDisplayName()); //$NON-NLS-1$
    }
    IItemDescription noteDescription = ((IBasicItemData) item.getItemData()).getDescription();
    ITextPart[] text = noteDescription.getContent().getTextParts();
    try {
      document.add(reportUtils.createNewParagraph(noteDescription.getName().getText(), Element.ALIGN_CENTER, Font.BOLD));
      MultiColumnText columnText = new MultiColumnText();
      columnText.addRegularColumns(document.left(), document.right(), 10f, 2);
      Paragraph paragraph = new Paragraph();
      paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
      for (ITextPart textpart : text) {
        Font font = reportUtils.createDefaultFont(8, reportUtils.getStyle(textpart.getFormat()));
        paragraph.add(new Chunk(textpart.getText(), font));
      }
      columnText.addElement(paragraph);
      document.add(columnText);
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  public boolean supports(IItem item) {
    if (item == null) {
      return false;
    }
    return item.getItemData() instanceof IBasicItemData;
  }
}
