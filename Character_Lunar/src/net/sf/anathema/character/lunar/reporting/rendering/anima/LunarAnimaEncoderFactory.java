package net.sf.anathema.character.lunar.reporting.rendering.anima;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.anima.AbstractAnimaEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class LunarAnimaEncoderFactory extends AbstractAnimaEncoderFactory {

  public LunarAnimaEncoderFactory(IResources resources) {
    super(resources);
  }

  @Override
  protected ITableEncoder getAnimaTableEncoder() {
    return new LunarAnimaTableEncoder(getResources(), getFontSize());
  }
}
