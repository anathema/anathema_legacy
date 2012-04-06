package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.magic.SpellsOnlyContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class SpellsOnlyEncoderFactory extends AbstractEncoderFactory{

  public SpellsOnlyEncoderFactory() {
    super(EncoderIds.SPELLS_ONLY);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new ExtendedMagicEncoder<SpellsOnlyContent>(resources, SpellsOnlyContent.class, false, "Magic");
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isEssenceUser();
  }
}
