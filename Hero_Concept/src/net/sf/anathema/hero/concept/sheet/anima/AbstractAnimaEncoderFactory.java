package net.sf.anathema.hero.concept.sheet.anima;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.general.box.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.Resources;

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
