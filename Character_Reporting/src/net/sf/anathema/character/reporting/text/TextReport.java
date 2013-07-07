package net.sf.anathema.character.reporting.text;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.MultiColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.character.main.Character;
import net.sf.anathema.character.reporting.text.description.HeroDescriptionTextEncoder;
import net.sf.anathema.character.reporting.text.description.TextDescriptionContentImpl;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.hero.description.HeroDescriptionFetcher;
import net.sf.anathema.hero.model.Hero;
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
  public void performPrint(Item item, Document document, PdfWriter writer) throws ReportException {
    MultiColumnText columnText = new MultiColumnText(document.top() - document.bottom() - 15);
    columnText.addRegularColumns(document.left(), document.right(), 20, 2);
    Hero hero = (Hero) item.getItemData();
    try {
      new HeroDescriptionTextEncoder(utils, resources).createParagraphs(columnText, hero);
      new ConceptTextEncoder(utils, resources).createParagraphs(columnText, hero);
      new AttributeTextEncoder(utils, resources).createParagraphs(columnText, hero);
      new VirtueTextEncoder(utils, resources).createParagraphs(columnText, hero);
      new AbilityTextEncoder(utils, resources).createParagraphs(columnText, hero);
      writeColumnText(document, columnText);
    } catch (DocumentException e) {
      e.printStackTrace();
    }
  }

  private void writeColumnText(Document document, MultiColumnText columnText) throws DocumentException {
    do {
      document.add(columnText);
      columnText.nextColumn();
    } while (columnText.isOverflow());
  }

  @Override
  public boolean supports(Item item) {
    return item != null && item.getItemData() instanceof Character;
  }
}
