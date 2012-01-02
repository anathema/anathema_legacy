package net.sf.anathema.character.reporting.first.rendering.combat;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractBoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.CombatStatsContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class CombatBoxEncoderFactory extends AbstractBoxContentEncoderFactory {

  public CombatBoxEncoderFactory() {
    super(CombatStatsContentBoxEncoder.ID);
  }

  @Override
  public IBoxContentEncoder create(IResources resources, BasicContent content) {
    IContentEncoder valueEncoder = new CombatValueEncoder();
    ITableEncoder rulesEncoder = new CombatRulesTableEncoder();
    return new CombatStatsContentBoxEncoder(rulesEncoder, valueEncoder);
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isFirstEdition();
  }
}
