package net.sf.anathema.character.reporting.pdf.rendering.boxes.anima;

import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.SMALLER_FONT_SIZE;

public abstract class AbstractAnimaEncoderFactory implements IAnimaEncoderFactory {

  private final IResources resources;

  public AbstractAnimaEncoderFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public final IBoxContentEncoder createAnimaEncoder() {
    return new PdfAnimaEncoder(resources, SMALLER_FONT_SIZE, getAnimaTableEncoder());
  }

  protected abstract ITableEncoder getAnimaTableEncoder();

  protected final float getFontSize() {
    return SMALLER_FONT_SIZE;
  }

  protected final IResources getResources() {
    return resources;
  }
}
