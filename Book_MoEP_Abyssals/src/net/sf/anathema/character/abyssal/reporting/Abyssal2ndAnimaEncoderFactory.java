package net.sf.anathema.character.abyssal.reporting;

import com.lowagie.text.pdf.BaseFont;

import net.sf.anathema.character.reporting.common.boxes.anima.AbstractAnimaEncoderFactory;
import net.sf.anathema.character.reporting.common.boxes.anima.AnimaTableEncoder;
import net.sf.anathema.character.reporting.common.stats.anima.AnimaTableStealthProvider;
import net.sf.anathema.character.reporting.common.encoder.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Abyssal2ndAnimaEncoderFactory extends AbstractAnimaEncoderFactory {

  public Abyssal2ndAnimaEncoderFactory(IResources resources, BaseFont basefont, BaseFont symbolBaseFont) {
    super(resources, basefont, symbolBaseFont);
  }

  @Override
  protected IPdfTableEncoder getAnimaTableEncoder() {
    return new AnimaTableEncoder(getResources(), getBaseFont(), getFontSize(), new AnimaTableStealthProvider(getResources()));
  }
}
