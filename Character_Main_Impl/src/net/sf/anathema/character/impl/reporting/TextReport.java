package net.sf.anathema.character.impl.reporting;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterDescription;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.itext.IITextReport;
import net.sf.anathema.framework.reporting.itext.ITextReportUtils;
import net.sf.anathema.framework.repository.IItem;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.MultiColumnText;
import com.lowagie.text.pdf.PdfWriter;

public class TextReport implements IITextReport {

  private static final int STANDARD_FONT_SIZE = 8;
  private final ITextReportUtils utils = new ITextReportUtils();

  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException {
    MultiColumnText columnText = new MultiColumnText(document.top() - document.bottom() - 15);
    columnText.addRegularColumns(document.left(), document.right(), 20, 2);
    ICharacter character = (ICharacter) item.getItemData();
    try {
      createDescriptionParagraphs(columnText, character);
      writeColumnText(document, columnText);
    }
    catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  private void createDescriptionParagraphs(MultiColumnText columnText, ICharacter character) throws DocumentException {
    ICharacterDescription description = character.getDescription();
    columnText.addElement(createNameParagraph(description.getName().getText()));
    if (!description.getPeriphrase().isEmpty()) {
      Paragraph periphrasis = new Paragraph(description.getPeriphrase().getText(), utils.createDefaultFont(
          8,
          Font.ITALIC));
      periphrasis.setAlignment(Element.ALIGN_CENTER);
      columnText.addElement(periphrasis);
    }
    if (description.getCharacterization().isEmpty()
        && description.getPhysicalDescription().isEmpty()
        && description.getNotes().isEmpty()) {
      return;
    }
    Phrase descriptionPhrase = createTextParagraph(createBoldTitle("Description: "));
    boolean isFirst = true;
    if (!description.getCharacterization().isEmpty()) {
      descriptionPhrase.add(createTextChunk(description.getCharacterization().getText()));
      columnText.addElement(descriptionPhrase);
      isFirst = false;
    }
    if (!description.getPhysicalDescription().isEmpty()) {
      Chunk descriptionChunk = createTextChunk(description.getPhysicalDescription().getText());
      addTextualDescriptionPart(columnText, descriptionPhrase, isFirst, descriptionChunk);
      isFirst = false;
    }
    if (!description.getNotes().isEmpty()) {
      Chunk noteChunk = createTextChunk(description.getNotes().getText());
      addTextualDescriptionPart(columnText, descriptionPhrase, isFirst, noteChunk);
    }
  }

  private void addTextualDescriptionPart(
      MultiColumnText columnText,
      Phrase potentialParentPhrase,
      boolean isFirst,
      Chunk chunk) throws DocumentException {
    if (isFirst) {
      potentialParentPhrase.add(chunk);
      columnText.addElement(potentialParentPhrase);
    }
    Paragraph descriptionParagraph = createTextParagraph(chunk);
    descriptionParagraph.setFirstLineIndent(5f);
    columnText.addElement(descriptionParagraph);
  }

  private Chunk createBoldTitle(String title) {
    return new Chunk(title, utils.createDefaultFont(STANDARD_FONT_SIZE, Font.BOLD));
  }

  private Chunk createTextChunk(String text) {
    return new Chunk(text, utils.createDefaultFont(STANDARD_FONT_SIZE, Font.NORMAL));
  }

  private Paragraph createTextParagraph(Chunk chunk) {
    Paragraph paragraph = new Paragraph(chunk);
    paragraph.setLeading(STANDARD_FONT_SIZE * 1.2f);
    return paragraph;
  }

  private Paragraph createNameParagraph(String name) {
    Font font = utils.createDefaultFont(11, Font.BOLD);
    Paragraph paragraph = new Paragraph(name, font);
    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
    paragraph.setLeading(font.size() * 1.2f);
    return paragraph;
  }

  private void writeColumnText(Document document, MultiColumnText columnText) throws DocumentException {
    do {
      document.add(columnText);
      columnText.nextColumn();
    }
    while (columnText.isOverflow());
  }

  public boolean supports(IItem item) {
    if (item == null) {
      return false;
    }
    return item.getItemData() instanceof ICharacter;
  }
}