package net.sf.anathema.hero.concept.sheet.anima.encoder;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.table.ITableEncoder;
import net.sf.anathema.framework.environment.Resources;

import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.SMALLER_FONT_SIZE;

public abstract class AbstractAnimaEncoderFactory extends AbstractEncoderFactory {

  public AbstractAnimaEncoderFactory() {
    super(EncoderIds.ANIMA);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new GenericAnimaEncoder(resources, getFontSize(), getAnimaTableEncoder(resources));
  }

  protected abstract ITableEncoder getAnimaTableEncoder(Resources resources);

  protected final float getFontSize() {
    return SMALLER_FONT_SIZE;
  }
}
