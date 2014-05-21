package net.sf.anathema.framework.reporting.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.character.framework.reporting.Report;
import net.sf.anathema.character.framework.reporting.ReportException;
import net.sf.anathema.hero.model.Hero;

public interface PdfReport extends Report {

  void performPrint(Hero hero, Document document, PdfWriter writer) throws ReportException;
}