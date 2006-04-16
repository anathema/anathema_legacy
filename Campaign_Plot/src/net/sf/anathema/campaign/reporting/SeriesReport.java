package net.sf.anathema.campaign.reporting;

import net.sf.anathema.campaign.model.ISeries;
import net.sf.anathema.campaign.model.plot.IPlotElement;
import net.sf.anathema.campaign.model.plot.IPlotModel;
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
import com.lowagie.text.TextElementArray;
import com.lowagie.text.pdf.MultiColumnText;

public class SeriesReport implements IITextReport {
  ITextReportUtils reportUtils = new ITextReportUtils();

  public void performPrint(IItem item, Document document) throws ReportException {
    if (!supports(item)) {
      throw new IllegalArgumentException("Item not supported: " + item.getDisplayName()); //$NON-NLS-1$
    }
    IPlotModel plotModel = ((ISeries) item.getItemData()).getPlot();
    IPlotElement rootElement = plotModel.getRootElement();
    try {
      MultiColumnText columnText = new MultiColumnText();
      columnText.addRegularColumns(document.left(), document.right(), 10f, 2);
      Paragraph subParagraph = new Paragraph();
      addToReport(rootElement, 12, subParagraph);
      columnText.addElement(subParagraph);
      document.add(columnText);
    }
    catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  private void addToReport(IPlotElement plotElement, int headerSize, TextElementArray textElement) {
    IItemDescription description = plotElement.getDescription();
    String title = description.getName().getText();
    ITextPart[] content = description.getContent().getText();
    Paragraph paragraph = reportUtils.createNewParagraph(title, Element.ALIGN_CENTER, Font.BOLD);
    paragraph.font().setSize(headerSize);
    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
    Paragraph contentParagraph = new Paragraph();
    for (ITextPart textpart : content) {
      Font font = reportUtils.createDefaultFont(8, reportUtils.getStyle(textpart.getFormat()));
      contentParagraph.add(new Chunk(textpart.getText(), font));
    }
    textElement.add(paragraph);
    textElement.add(contentParagraph);
    for (IPlotElement childElement : plotElement.getChildren()) {
      Paragraph subParagraph = new Paragraph();
      addToReport(childElement, headerSize - 1, subParagraph);
      textElement.add(subParagraph);
    }
  }

  public boolean supports(IItem item) {
    if (item == null) {
      return false;
    }
    return item.getItemData() instanceof ISeries;
  }
}