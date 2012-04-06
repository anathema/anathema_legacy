package net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.GlobalEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class ExtendedSpecialtiesEncoderFactory extends GlobalEncoderFactory {

  public ExtendedSpecialtiesEncoderFactory() {
    super(EncoderIds.SPECIALTIES);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new ExtendedSpecialtiesEncoder(resources);
  }
}
