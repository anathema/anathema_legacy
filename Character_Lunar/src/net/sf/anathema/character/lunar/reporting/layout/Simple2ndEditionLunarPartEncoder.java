package net.sf.anathema.character.lunar.reporting.layout;

import net.sf.anathema.character.lunar.reporting.rendering.anima.LunarAnimaEncoderFactory;
import net.sf.anathema.character.lunar.reporting.rendering.greatcurse.SecondEditionLunarGreatCurseEncoder;
import net.sf.anathema.character.reporting.pdf.layout.simple.AbstractSimplePartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Simple2ndEditionLunarPartEncoder extends AbstractSimplePartEncoder {

  private EncoderRegistry encoderRegistry;

  public Simple2ndEditionLunarPartEncoder(EncoderRegistry encoderRegistry, IResources resources, int essenceMax) {
    super(resources);
    this.encoderRegistry = encoderRegistry;
  }

  public ContentEncoder getGreatCurseEncoder() {
    return new SecondEditionLunarGreatCurseEncoder();
  }

  @Override
  public ContentEncoder getAnimaEncoder() {
    return new LunarAnimaEncoderFactory(getResources()).createAnimaEncoder();
  }
}
