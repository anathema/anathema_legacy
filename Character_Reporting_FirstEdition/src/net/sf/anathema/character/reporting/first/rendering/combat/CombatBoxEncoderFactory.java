package net.sf.anathema.character.reporting.first.rendering.combat;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.CombatStatsContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class CombatBoxEncoderFactory extends AbstractEncoderFactory {

  public CombatBoxEncoderFactory() {
    super(EncoderIds.COMBAT);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    IContentEncoder valueEncoder = new CombatValueEncoder();
    ITableEncoder rulesEncoder = new CombatRulesTableEncoder();
    return new CombatStatsContentBoxEncoder(resources, rulesEncoder, valueEncoder);
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isFirstEdition();
  }
}
