package net.sf.anathema.campaign.reporting;

import net.sf.anathema.campaign.concrete.plot.PlotModel;
import net.sf.anathema.campaign.model.ISeries;
import net.sf.anathema.campaign.model.plot.IPlotElement;
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
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfWriter;

public class MultiColumnSeriesReport implements IITextReport {

  private final ITextReportUtils reportUtils = new ITextReportUtils();

  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException {
    if (!supports(item)) {
      throw new IllegalArgumentException("Item not supported: " + item.getDisplayName()); //$NON-NLS-1$
    }
    writer.setSpaceCharRatio(PdfWriter.NO_SPACE_CHAR_RATIO);
    writer.setViewerPreferences(PdfWriter.PageModeUseOutlines);
    PdfOutline rootOutline = writer.getDirectContent().getRootOutline();
    IPlotElement rootElement = ((ISeries) item.getItemData()).getPlot().getRootElement();
    try {
      String seriesTitle = rootElement.getDescription().getName().getText();
      Paragraph headerParagraph = reportUtils.createNewParagraph(seriesTitle, Element.ALIGN_CENTER, Font.BOLD);
      new PdfOutline(rootOutline, new PdfAction(PdfAction.FIRSTPAGE), seriesTitle);
      headerParagraph.font().setSize(15);
      document.add(headerParagraph);
      Paragraph paragraph = new Paragraph("Synopsis:", reportUtils.createDefaultFont(8, Font.BOLD)); //$NON-NLS-1$
      paragraph.setSpacingAfter(12);
      document.add(paragraph);
      document.add(createContentParagraph(rootElement.getDescription()));
      int storyNumber = 1;
      for (IPlotElement story : rootElement.getChildren()) {
        createNewPage(document);
        String storyTitle = createSectionTitle(story.getDescription(), new int[] { storyNumber++ });
        Paragraph storyTitleParagraph = createTitleParagraph(storyTitle, 13);
        document.add(storyTitleParagraph);
        PdfOutline storyOutline = addOutline(rootOutline, storyTitle);
        MultiColumnText columnText = new MultiColumnText(/* document.top() - document.bottom() - 15 */);
        columnText.addRegularColumns(document.left(), document.right(), 20, 2);
        addTextAndChildren(columnText, story, storyOutline, new int[] { storyNumber });
        document.add(columnText);
      }
    }
    catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  private void addTextAndChildren(
      MultiColumnText columnText,
      IPlotElement element,
      PdfOutline elementOutline,
      int[] elementTitleNumbers) throws DocumentException {
    columnText.addElement(createContentParagraph(element.getDescription()));
    int episodeNumber = 1;
    for (IPlotElement episode : element.getChildren()) {
      addSubElement(columnText, episode, elementOutline, elementTitleNumbers, episodeNumber++);
    }
  }

  private void addSubElement(
      MultiColumnText columnText,
      IPlotElement element,
      PdfOutline parentOutline,
      int[] superElementNumbers,
      int thisElementNumber) throws DocumentException {
    int[] titleNumbers = extendArray(superElementNumbers, thisElementNumber);
    String elementTitle = createSectionTitle(element.getDescription(), titleNumbers);
    Paragraph elementTitleParagraph = createTitleParagraph(elementTitle, 9);
    PdfOutline outline = addOutline(parentOutline, elementTitle);
    columnText.addElement(elementTitleParagraph);
    addTextAndChildren(columnText, element, outline, titleNumbers);
  }

  private int[] extendArray(int[] array, int extension) {
    int[] newArray = new int[array.length + 1];
    System.arraycopy(array, 0, newArray, 0, array.length);
    newArray[array.length] = extension;
    return newArray;
  }

  private PdfOutline addOutline(PdfOutline parentOutline, String outlineTitle) {
    PdfAction episodeAction = PdfAction.gotoLocalPage(outlineTitle, false);
    return new PdfOutline(parentOutline, episodeAction, outlineTitle);
  }

  private void createNewPage(Document document) throws DocumentException {
    document.newPage();
  }

  private void createTableOfContents(IPlotElement rootElement, List tableOfContents) {
    for (IPlotElement element : rootElement.getChildren()) {
      Font tableOfContentsFont = tableOfContents.symbol().font();
      String title = element.getDescription().getName().getText();
      Chunk contentChunk = new Chunk(title, tableOfContentsFont);
      contentChunk.setLocalGoto(title);
      tableOfContents.add(new ListItem(contentChunk));
      if (element.getTimeUnit().getId().equals(PlotModel.ID_STORY)) {
        List subContents = new List(true, true, 15);
        subContents.setListSymbol(new Chunk("", tableOfContentsFont)); //$NON-NLS-1$
        createTableOfContents(element, subContents);
        tableOfContents.add(subContents);
      }
    }
  }

  private Paragraph createTitleParagraph(String titleString, int headerSize) {
    Font font = reportUtils.createDefaultFont(headerSize, Font.BOLD);
    Chunk title = new Chunk(titleString, font);
    title.setLocalDestination(titleString);
    Paragraph paragraph = new Paragraph(title);
    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
    paragraph.setLeading(font.size() * 1.2f);
    return paragraph;
  }

  private String createSectionTitle(IItemDescription description, int[] sectionMarking) {
    String prepend = new String();
    for (int mark : sectionMarking) {
      prepend = prepend.concat(mark + "."); //$NON-NLS-1$
    }
    String finalTitle = prepend + " " + description.getName().getText(); //$NON-NLS-1$
    return finalTitle;
  }

  private TextElementArray createContentParagraph(IItemDescription description) {
    ITextPart[] content = description.getContent().getText();
    Paragraph contentParagraph = new Paragraph();
    contentParagraph.setLeading(0, 1.4f);
    contentParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
    for (ITextPart textpart : content) {
      Font font = reportUtils.createDefaultFont(8, reportUtils.getStyle(textpart.getFormat()));
      contentParagraph.add(new Chunk(textpart.getText(), font));
    }
    if (content.length > 0) {
      contentParagraph.setSpacingAfter(12);
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

// List tableOfContents = new List(true, false, 15);
// tableOfContents.setListSymbol(new Chunk("", reportUtils.createDefaultFont(8, Font.NORMAL))); //$NON-NLS-1$
// createTableOfContents(rootElement, tableOfContents);
// document.add(tableOfContents);

// For some reason, this breaks printing (See: http: //
// itextdocs.lowagie.com/tutorial/objects/columns/index.html)
// do {
// document.add(columnText);
// columnText.nextColumn();
// createNewPage(document);
// }
