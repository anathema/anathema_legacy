package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class ComboEncoderFactory extends AbstractEncoderFactory {

  public ComboEncoderFactory() {
    super(EncoderIds.COMBOS);
    setPreferredHeight(new PreferredComboHeight());
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new ComboEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isEssenceUser();
  }
}
