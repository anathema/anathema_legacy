package net.sf.anathema.character.lunar.reporting.extended;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.extended.common.anima.AbstractAnimaEncoderFactory;
import net.sf.anathema.character.reporting.extended.util.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class LunarAnimaEncoderFactory extends AbstractAnimaEncoderFactory {

  public LunarAnimaEncoderFactory(IResources resources, BaseFont basefont, BaseFont symbolBaseFont) {
    super(resources, basefont, symbolBaseFont);
  }

  @Override
  protected IPdfTableEncoder getAnimaTableEncoder() {
    return new LunarAnimaTableEncoder(getResources(), getBaseFont(), getSymbolBaseFont(), getFontSize());
  }
}