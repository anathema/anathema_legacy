package net.sf.anathema.hero.sheet.pdf.page.layout.field;

public interface HeightWithoutParent {

  ColumnSpan withPreferredHeight();

  ColumnSpan withHeight(float layoutHeight);

  ColumnSpan fillToBottomOfPage();

  ColumnSpan alignBottomTo(LayoutField field);
}
