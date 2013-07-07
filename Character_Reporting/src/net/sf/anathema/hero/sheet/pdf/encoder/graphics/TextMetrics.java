package net.sf.anathema.hero.sheet.pdf.encoder.graphics;

public interface TextMetrics {

  float getDefaultTextWidth(String text);

  float getCommentTextWidth(String text);

  float getTableTextWidth(String text);
}
