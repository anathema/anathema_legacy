package net.sf.anathema.character.impl.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.impl.generic.GenericDescription;
import net.sf.anathema.character.impl.util.GenericCharacterUtilities;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.reporting.text.AbilityTextEncoder;
import net.sf.anathema.character.reporting.text.AttributeTextEncoder;
import net.sf.anathema.character.reporting.text.CharacterDescriptionTextEncoder;
import net.sf.anathema.character.reporting.text.ConceptTextEncoder;
import net.sf.anathema.character.reporting.text.VirtueTextEncoder;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.itext.IITextReport;
import net.sf.anathema.framework.reporting.itext.ITextReportUtils;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.MultiColumnText;
import com.lowagie.text.pdf.PdfWriter;

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
      new CharacterDescriptionTextEncoder(utils, resources).createParagraphs(columnText, new GenericDescription(
          character.getDescription()));
      if (character.hasStatistics()) {
        IGenericCharacter genericCharacter = GenericCharacterUtilities.createGenericCharacter(character.getStatistics());
        new ConceptTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
        new AttributeTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
        new VirtueTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
        new AbilityTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
        //        new BackgroundsTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
        //        new CharmTextEncoder(utils, resources).createParagraphs(columnText, genericCharacter);
      }
      writeColumnText(document, columnText);
    }
    catch (DocumentException e) {
      e.printStackTrace();
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