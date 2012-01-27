package net.sf.anathema.character.abyssal.reporting.rendering;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.stats.anima.AnimaTableStealthProvider;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.anima.AbstractAnimaEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.anima.AnimaTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class Anima2ndEditionEncoderFactory extends AbstractAnimaEncoderFactory {

  @Override
  protected ITableEncoder getAnimaTableEncoder(IResources resources) {
    return new AnimaTableEncoder(resources, getFontSize(), new AnimaTableStealthProvider(resources));
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isSecondEdition() && content.isOfType(CharacterType.ABYSSAL);
  }
}
