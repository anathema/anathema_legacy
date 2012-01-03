package net.sf.anathema.character.reporting.pdf.rendering.boxes.social;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractBoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SocialCombatEncoderFactory extends AbstractBoxContentEncoderFactory {

  public SocialCombatEncoderFactory() {
    super(EncoderIds.SOCIAL_COMBAT);
  }

  @Override
  public IBoxContentEncoder create(IResources resources, BasicContent content) {
    return new SocialCombatStatsBoxEncoder(resources);
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isSecondEdition();
  }
}
