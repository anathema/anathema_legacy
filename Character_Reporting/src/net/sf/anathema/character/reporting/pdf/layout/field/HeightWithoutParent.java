package net.sf.anathema.character.reporting.pdf.layout.field;

public interface HeightWithoutParent {

  ColumnSpan withHeight(float height);

  ColumnSpan fillToBottomOfPage();

  ColumnSpan alignBottomTo(LayoutField parent);
}
