package net.sf.anathema.character.reporting.pdf.layout.field;

public interface FieldEncoder {

  LayoutField encode(LayoutField field);

  float getPreferredHeight();
}
