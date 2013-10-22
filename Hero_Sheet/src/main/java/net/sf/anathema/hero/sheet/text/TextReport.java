package net.sf.anathema.hero.sheet.text;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.MultiColumnText;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.hero.model.Hero;

import java.util.Collection;

public class TextReport extends AbstractPdfReport {

  private final PdfReportUtils utils;
  private final Environment environment;

  public TextReport(Environment environment) {
    this.environment = environment;
    utils = new PdfReportUtils();
  }

  @Override
  public String toString() {
    return environment.getString("CharacterModule.Reporting.Text.Name");
  }

  @Override
  public void performPrint(Hero hero, Document document, PdfWriter writer) throws ReportException {
    MultiColumnText columnText = new MultiColumnText(document.top() - document.bottom() - 15);
    columnText.addRegularColumns(document.left(), document.right(), 20, 2);
    Collection<HeroTextEncoderFactory> encoderFactories = environment.instantiateOrdered(RegisteredTextEncoderFactory.class);
    try {
      for (HeroTextEncoderFactory factory : encoderFactories) {
        factory.create(utils, environment).createParagraphs(columnText, hero);
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
  public boolean supports(Hero hero) {
    return true;
  }
}