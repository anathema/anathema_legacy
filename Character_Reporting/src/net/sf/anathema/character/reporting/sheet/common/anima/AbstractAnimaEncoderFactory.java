package net.sf.anathema.character.reporting.sheet.common.anima;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfTableEncoder;
import net.sf.anathema.character.reporting.extended.common.anima.PdfAnimaEncoder;
import net.sf.anathema.character.reporting.sheet.page.ISimplePartEncoder;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractAnimaEncoderFactory implements IAnimaEncoderFactory {

  private final IResources resources;
  private final BaseFont symbolBaseFont;
  private final BaseFont basefont;

  public AbstractAnimaEncoderFactory(IResources resources, BaseFont basefont, BaseFont symbolBaseFont) {
    this.resources = resources;
    this.basefont = basefont;
    this.symbolBaseFont = symbolBaseFont;
  }

  @Override
  public final IPdfContentBoxEncoder createAnimaEncoder() {
    return new PdfAnimaEncoder(resources, basefont, symbolBaseFont, ISimplePartEncoder.FONT_SIZE, getAnimaTableEncoder());
  }

  protected abstract IPdfTableEncoder getAnimaTableEncoder();

  protected final float getFontSize() {
    return ISimplePartEncoder.FONT_SIZE;
  }

  protected final BaseFont getBaseFont() {
    return basefont;
  }

  protected final IResources getResources() {
    return resources;
  }

  protected final BaseFont getSymbolBaseFont() {
    return symbolBaseFont;
  }
}
