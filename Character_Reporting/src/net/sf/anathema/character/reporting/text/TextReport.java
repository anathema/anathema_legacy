package net.sf.anathema.character.reporting.text;

import com.google.common.base.Strings;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.MultiColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.impl.generic.GenericDescription;
import net.sf.anathema.character.impl.util.GenericCharacterUtilities;
import net.sf.anathema.character.main.description.model.CharacterDescriptionFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.Resources;

public class TextReport extends AbstractPdfReport {

  private final Resources resources;
  private final PdfReportUtils utils;

  public TextReport(Resources resources) {
    this.resources = resources;
    utils = new PdfReportUtils();
  }

  @Override
  public String toString() {
    return resources.getString("CharacterModule.Reporting.Text.Name");
  }

  @Override
  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException {
    MultiColumnText columnText = new MultiColumnText(document.top() - document.bottom() - 15);
    columnText.addRegularColumns(document.left(), document.right(), 20, 2);
    ICharacter character = (ICharacter) item.getItemData();
    try {
      GenericDescription description = new GenericDescription(CharacterDescriptionFetcher.fetch(character));
      new CharacterDescriptionTextEncoder(utils, resources).createParagraphs(columnText, description);
      IGenericCharacter genericCharacter = GenericCharacterUtilities.createGenericCharacter(character);
      new ConceptTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
      createConceptParagraph(columnText, description);
      new AttributeTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
      new VirtueTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
      new AbilityTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
      new BackgroundsTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
      writeColumnText(document, columnText);
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  private void createConceptParagraph(MultiColumnText columnText, GenericDescription description) throws DocumentException {
    TextPartFactory factory = new TextPartFactory(utils);
    String conceptText = description.getConceptText();
    if (!Strings.isNullOrEmpty(conceptText)) {
      Phrase conceptPhrase = factory.createTextParagraph(factory.createBoldTitle(resources.getString("Sheet.Label.Concept") + " "));
      conceptPhrase.add(factory.createTextChunk(conceptText));
      columnText.addElement(conceptPhrase);
    }
  }

  private void writeColumnText(Document document, MultiColumnText columnText) throws DocumentException {
    do {
      document.add(columnText);
      columnText.nextColumn();
    } while (columnText.isOverflow());
  }

  @Override
  public boolean supports(IItem item) {
    return item != null && item.getItemData() instanceof ICharacter;
  }
}
