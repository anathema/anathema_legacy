package net.sf.anathema.character.solar.reporting.rendering;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.solar.SolarCharacterModule;
import net.sf.anathema.hero.concept.sheet.anima.AbstractAnimaEncoderFactory;
import net.sf.anathema.hero.concept.sheet.anima.AnimaTableEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class AnimaEncoderFactory extends AbstractAnimaEncoderFactory {

  @Override
  protected ITableEncoder getAnimaTableEncoder(Resources resources) {
    return new AnimaTableEncoder(resources, getFontSize());
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isOfType(SolarCharacterModule.type);
  }
}
