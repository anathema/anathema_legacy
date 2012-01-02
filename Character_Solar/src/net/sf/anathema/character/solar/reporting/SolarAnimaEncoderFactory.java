package net.sf.anathema.character.solar.reporting;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.anima.AbstractAnimaEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.anima.AnimaTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SolarAnimaEncoderFactory extends AbstractAnimaEncoderFactory {

  public SolarAnimaEncoderFactory(IResources resources, BaseFont basefont) {
    super(resources, basefont);
  }

  @Override
  protected ITableEncoder getAnimaTableEncoder() {
    return new AnimaTableEncoder(getResources(), getBaseFont(), getFontSize());
  }
}
