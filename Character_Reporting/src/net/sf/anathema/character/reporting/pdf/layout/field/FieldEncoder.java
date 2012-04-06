package net.sf.anathema.character.reporting.pdf.layout.field;

public interface FieldEncoder {

  boolean isActive();

  LayoutField encode(LayoutField field);

  float getPreferredHeight(float contentWidth);
}
