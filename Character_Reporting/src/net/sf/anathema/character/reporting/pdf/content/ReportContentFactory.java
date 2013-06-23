package net.sf.anathema.character.reporting.pdf.content;

public interface ReportContentFactory<C extends SubContent> {

  C create(ReportSession session);
}
