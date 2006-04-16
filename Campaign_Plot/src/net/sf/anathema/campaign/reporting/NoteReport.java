package net.sf.anathema.campaign.reporting;

import net.sf.anathema.framework.itemdata.IBasicItemData;
import net.sf.anathema.framework.itemdata.IItemDescription;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.itext.IITextReport;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.styledtext.model.ITextPart;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.MultiColumnText;

public class NoteReport implements IITextReport {

  ITextReportUtils reportUtils = new ITextReportUtils();

  public void performPrint(IItem item, Document document) throws ReportException {
    if (!supports(item)) {
      throw new IllegalArgumentException("Item not supported: " + item.getDisplayName()); //$NON-NLS-1$
    }
    IItemDescription noteDescription = ((IBasicItemData) item.getItemData()).getDescription();
    ITextPart[] text = noteDescription.getContent().getText();
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
    }
    catch (DocumentException e) {
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