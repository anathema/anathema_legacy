package net.sf.anathema.character.reporting.pdf.layout.field;

public interface HeightWithoutParent {

  ColumnSpan withPreferredHeight();

  ColumnSpan withHeight(float layoutHeight);

  ColumnSpan fillToBottomOfPage();

  ColumnSpan alignBottomTo(LayoutField field);
}
