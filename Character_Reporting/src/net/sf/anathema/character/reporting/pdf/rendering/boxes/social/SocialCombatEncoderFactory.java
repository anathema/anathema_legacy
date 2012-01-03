package net.sf.anathema.character.reporting.pdf.rendering.boxes.social;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SocialCombatEncoderFactory extends AbstractEncoderFactory {

  public SocialCombatEncoderFactory() {
    super(EncoderIds.SOCIAL_COMBAT);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new SocialCombatStatsBoxEncoder(resources);
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isSecondEdition();
  }
}
