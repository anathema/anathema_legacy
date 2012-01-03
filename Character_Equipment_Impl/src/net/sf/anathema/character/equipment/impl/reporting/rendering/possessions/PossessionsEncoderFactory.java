package net.sf.anathema.character.equipment.impl.reporting.rendering.possessions;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.GlobalEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class PossessionsEncoderFactory extends GlobalEncoderFactory {

  public PossessionsEncoderFactory() {
    super(EncoderIds.POSSESSIONS);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new PossessionsEncoder();
  }
}
