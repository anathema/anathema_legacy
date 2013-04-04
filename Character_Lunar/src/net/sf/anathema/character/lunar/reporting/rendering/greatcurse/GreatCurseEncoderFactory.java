package net.sf.anathema.character.lunar.reporting.rendering.greatcurse;

import net.sf.anathema.character.lunar.virtueflaw.LunarVirtueFlawTemplate;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class GreatCurseEncoderFactory extends AbstractEncoderFactory {

  public GreatCurseEncoderFactory() {
    super(EncoderIds.GREAT_CURSE);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new GreatCurseEncoder(resources);
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.getAdditionalModel(LunarVirtueFlawTemplate.TEMPLATE_ID) != null;
  }
}
