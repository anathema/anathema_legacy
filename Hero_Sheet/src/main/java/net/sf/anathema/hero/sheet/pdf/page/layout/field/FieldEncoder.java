package net.sf.anathema.hero.sheet.pdf.page.layout.field;

public interface FieldEncoder {

  boolean isActive();

  LayoutField encode(LayoutField field);

  float getPreferredHeight(float contentWidth);
}
