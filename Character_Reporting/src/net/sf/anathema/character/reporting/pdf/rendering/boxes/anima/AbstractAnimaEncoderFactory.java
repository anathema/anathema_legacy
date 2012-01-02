package net.sf.anathema.character.reporting.pdf.rendering.boxes.anima;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractAnimaEncoderFactory implements IAnimaEncoderFactory {

  private final IResources resources;
  private final BaseFont basefont;

  public AbstractAnimaEncoderFactory(IResources resources, BaseFont basefont) {
    this.resources = resources;
    this.basefont = basefont;
  }

  @Override
  public final IBoxContentEncoder createAnimaEncoder() {
    return new PdfAnimaEncoder(resources, basefont, IVoidStateFormatConstants.SMALLER_FONT_SIZE, getAnimaTableEncoder());
  }

  protected abstract ITableEncoder getAnimaTableEncoder();

  protected final float getFontSize() {
    return IVoidStateFormatConstants.SMALLER_FONT_SIZE;
  }

  protected final BaseFont getBaseFont() {
    return basefont;
  }

  protected final IResources getResources() {
    return resources;
  }
}
