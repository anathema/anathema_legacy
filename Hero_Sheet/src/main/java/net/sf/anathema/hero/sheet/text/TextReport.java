package net.sf.anathema.hero.sheet.text;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.MultiColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.character.main.Character;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.character.main.framework.item.Item;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.framework.environment.Resources;

import java.util.Collection;

public class TextReport extends AbstractPdfReport {

  private final Resources resources;
  private HeroEnvironment environment;
  private final PdfReportUtils utils;

  public TextReport(Resources resources, HeroEnvironment environment) {
    this.resources = resources;
    this.environment = environment;
    utils = new PdfReportUtils();
  }

  @Override
  public String toString() {
    return resources.getString("CharacterModule.Reporting.Text.Name");
  }

  @Override
  public void performPrint(Hero hero, Document document, PdfWriter writer) throws ReportException {
    MultiColumnText columnText = new MultiColumnText(document.top() - document.bottom() - 15);
    columnText.addRegularColumns(document.left(), document.right(), 20, 2);
    Collection<HeroTextEncoderFactory> encoderFactories = environment.getObjectFactory().instantiateOrdered(RegisteredTextEncoderFactory.class);
    try {
      for (HeroTextEncoderFactory factory : encoderFactories) {
        factory.create(utils, resources).createParagraphs(columnText, hero);
      }
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
