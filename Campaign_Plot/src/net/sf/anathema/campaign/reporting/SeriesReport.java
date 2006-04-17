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
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
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
      Paragraph headerParagraph = reportUtils.createNewParagraph(
          rootElement.getDescription().getName().getText(),
          Element.ALIGN_CENTER,
          Font.BOLD);
      headerParagraph.font().setSize(15);
      document.add(headerParagraph);
      MultiColumnText columnText = new MultiColumnText();
      columnText.addRegularColumns(document.left(), document.right(), 10f, 2);
      TextElementArray rootContentArray = createContentParagraph(rootElement.getDescription());
      columnText.addElement(rootContentArray);
      List list = new List(true, 10);
      list.setListSymbol(new Chunk("", reportUtils.createDefaultFont(12, Font.BOLD))); //$NON-NLS-1$
      createChildrenParagraphs(rootElement, 12, list);
      columnText.addElement(list);
      document.add(columnText);
    }
    catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  private void createChildrenParagraphs(IPlotElement plotElement, int headerSize, List list) {
    for (IPlotElement childElement : plotElement.getChildren()) {
      Paragraph childParagraph = addToReport(childElement, headerSize);
      list.add(new ListItem(childParagraph));
    }
  }

  private Paragraph addToReport(IPlotElement plotElement, int headerSize) {
    IItemDescription description = plotElement.getDescription();
    Paragraph container = new Paragraph();
    Paragraph titleParagraph = createTitleParagraph(description, headerSize);
    container.add(titleParagraph);
    if ((!description.getContent().isEmpty())) {
      TextElementArray contentParagraph = createContentParagraph(description);
      contentParagraph.add("\n"); //$NON-NLS-1$
      container.add(contentParagraph);
    }
    else {
      titleParagraph.setSpacingAfter(0);
    }
    List list = new List(false, true, 10);
    list.setListSymbol(new Chunk("", reportUtils.createDefaultFont(headerSize - 2, Font.BOLD))); //$NON-NLS-1$
    createChildrenParagraphs(plotElement, headerSize - 2, list);
    container.add(list);
    return container;
  }

  private Paragraph createTitleParagraph(IItemDescription description, int headerSize) {
    String title = description.getName().getText();
    Paragraph paragraph = reportUtils.createNewParagraph(title, Element.ALIGN_CENTER, Font.BOLD);
    paragraph.setLeading(paragraph.font().size() * 1.2f);
    paragraph.font().setSize(headerSize);
    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
    return paragraph;
  }

  private TextElementArray createContentParagraph(IItemDescription description) {
    ITextPart[] content = description.getContent().getText();
    Paragraph contentParagraph = new Paragraph();
    for (ITextPart textpart : content) {
      Font font = reportUtils.createDefaultFont(8, reportUtils.getStyle(textpart.getFormat()));
      contentParagraph.add(new Chunk(textpart.getText(), font));
    }
    return contentParagraph;
  }

  public boolean supports(IItem item) {
    if (item == null) {
      return false;
    }
    return item.getItemData() instanceof ISeries;
  }
}