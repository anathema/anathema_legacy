package net.sf.anathema.character.impl.reporting;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.MultiColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.impl.generic.GenericDescription;
import net.sf.anathema.character.impl.util.GenericCharacterUtilities;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.reporting.text.*;
import net.sf.anathema.framework.reporting.IITextReport;
import net.sf.anathema.framework.reporting.ITextReportUtils;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class TextReport implements IITextReport {

  private final IResources resources;
  private final ITextReportUtils utils;

  public TextReport(IResources resources) {
    this.resources = resources;
    utils = new ITextReportUtils();
  }

  @Override
  public String toString() {
    return resources.getString("CharacterModule.Reporting.Text.Name"); //$NON-NLS-1$
  }

  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException {
    MultiColumnText columnText = new MultiColumnText(document.top() - document.bottom() - 15);
    columnText.addRegularColumns(document.left(), document.right(), 20, 2);
    ICharacter character = (ICharacter) item.getItemData();
    try {
      GenericDescription description = new GenericDescription(character.getDescription());
      new CharacterDescriptionTextEncoder(utils, resources).createParagraphs(columnText, description);
      boolean hasStatistics = character.hasStatistics();
      if (hasStatistics) {
        IGenericCharacter genericCharacter = GenericCharacterUtilities.createGenericCharacter(character.getStatistics());
        new ConceptTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
      }
      createConceptParagraph(columnText, description);
      if (hasStatistics) {
        IGenericCharacter genericCharacter = GenericCharacterUtilities.createGenericCharacter(character.getStatistics());
        new AttributeTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
        new VirtueTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
        new AbilityTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
        new BackgroundsTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
        // new CharmTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
      }
      writeColumnText(document, columnText);
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  private void createConceptParagraph(MultiColumnText columnText, GenericDescription description) throws DocumentException {
    TextPartFactory factory = new TextPartFactory(utils);
    String conceptText = description.getConceptText();
    if (!StringUtilities.isNullOrEmpty(conceptText)) {
      Phrase conceptPhrase = factory.createTextParagraph(factory.createBoldTitle(resources.getString("Sheet.Label.Concept") + " ")); //$NON-NLS-1$ //$NON-NLS-2$
      conceptPhrase.add(factory.createTextChunk(conceptText));
      columnText.addElement(conceptPhrase);
    }
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
