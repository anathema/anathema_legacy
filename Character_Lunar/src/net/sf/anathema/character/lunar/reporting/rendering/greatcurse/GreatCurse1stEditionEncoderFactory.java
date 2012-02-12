package net.sf.anathema.character.lunar.reporting.rendering.greatcurse;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class GreatCurse1stEditionEncoderFactory extends AbstractEncoderFactory {

  public GreatCurse1stEditionEncoderFactory() {
    super(EncoderIds.GREAT_CURSE);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new GreatCurse1stEditionEncoder(resources);
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isFirstEdition() && content.isOfType(CharacterType.LUNAR);
  }
}
