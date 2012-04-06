package net.sf.anathema.character.reporting.pdf.rendering.boxes.abilities;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.GlobalEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class AbilitiesWithCraftEncoderFactory extends GlobalEncoderFactory {

  public AbilitiesWithCraftEncoderFactory() {
    super(EncoderIds.ABILITIES_WITH_CRAFTS);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return AbilitiesEncoder.createWithCraftsOnly(resources, -1);
  }
}
