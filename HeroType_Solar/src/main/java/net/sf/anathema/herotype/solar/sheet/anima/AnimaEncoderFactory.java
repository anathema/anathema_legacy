package net.sf.anathema.herotype.solar.sheet.anima;

import net.sf.anathema.hero.concept.sheet.anima.encoder.AbstractAnimaEncoderFactory;
import net.sf.anathema.hero.concept.sheet.anima.encoder.AnimaTableEncoder;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.table.ITableEncoder;
import net.sf.anathema.herotype.solar.model.SolarCharacterType;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class AnimaEncoderFactory extends AbstractAnimaEncoderFactory {

  @Override
  protected ITableEncoder getAnimaTableEncoder(Resources resources) {
    return new AnimaTableEncoder(resources, getFontSize());
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isOfType(new SolarCharacterType());
  }
}
