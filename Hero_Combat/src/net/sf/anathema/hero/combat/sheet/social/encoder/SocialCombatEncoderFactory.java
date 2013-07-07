package net.sf.anathema.hero.combat.sheet.social.encoder;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class SocialCombatEncoderFactory extends AbstractEncoderFactory {

  public SocialCombatEncoderFactory() {
    super(EncoderIds.SOCIAL_COMBAT);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new SocialCombatStatsBoxEncoder(resources);
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}
