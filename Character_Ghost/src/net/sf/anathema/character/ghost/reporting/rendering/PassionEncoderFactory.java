package net.sf.anathema.character.ghost.reporting.rendering;

import net.sf.anathema.character.ghost.passions.GhostPassionsTemplate;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class PassionEncoderFactory extends AbstractEncoderFactory {

  public PassionEncoderFactory() {
    super(EncoderIds.ANIMA);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new PassionEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.getAdditionalModel(GhostPassionsTemplate.ID) != null;
  }
}
