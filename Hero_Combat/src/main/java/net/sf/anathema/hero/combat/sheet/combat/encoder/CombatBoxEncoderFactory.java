package net.sf.anathema.hero.combat.sheet.combat.encoder;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.IContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.table.ITableEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class CombatBoxEncoderFactory extends AbstractEncoderFactory {

  public CombatBoxEncoderFactory() {
    super(EncoderIds.COMBAT);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    IContentEncoder valueEncoder = new CombatValueEncoder();
    ITableEncoder rulesEncoder = new CombatRulesTableEncoder();
    return new CombatStatsContentBoxEncoder(resources, rulesEncoder, valueEncoder);
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}
