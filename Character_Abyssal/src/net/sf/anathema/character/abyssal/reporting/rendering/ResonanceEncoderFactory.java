package net.sf.anathema.character.abyssal.reporting.rendering;

import net.sf.anathema.character.abyssal.resonance.AbyssalResonanceTemplate;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class ResonanceEncoderFactory extends AbstractEncoderFactory {

  public ResonanceEncoderFactory() {
    super(EncoderIds.GREAT_CURSE);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new ResonanceEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.getAdditionalModel(AbyssalResonanceTemplate.ID) != null;
  }
}
