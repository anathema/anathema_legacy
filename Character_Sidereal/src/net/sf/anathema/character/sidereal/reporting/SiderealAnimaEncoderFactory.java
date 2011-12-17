package net.sf.anathema.character.sidereal.reporting;

import com.lowagie.text.pdf.BaseFont;

import net.sf.anathema.character.reporting.common.encoder.*;
import net.sf.anathema.character.reporting.sheet.common.anima.AbstractAnimaEncoderFactory;
import net.sf.anathema.character.reporting.sheet.common.anima.AnimaTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SiderealAnimaEncoderFactory extends AbstractAnimaEncoderFactory {

  public SiderealAnimaEncoderFactory(IResources resources, BaseFont basefont, BaseFont symbolBaseFont) {
    super(resources, basefont, symbolBaseFont);
  }

  @Override
  protected int getAnimaPowerCount() {
    return 3;
  }

  @Override
  protected IPdfTableEncoder getAnimaTableEncoder() {
    return new AnimaTableEncoder(getResources(), getBaseFont(), getFontSize(), new SiderealAnimaTableStealthProvider(
        getResources()));
  }
}
