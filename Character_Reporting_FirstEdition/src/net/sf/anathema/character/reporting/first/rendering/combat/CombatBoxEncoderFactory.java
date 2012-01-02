package net.sf.anathema.character.reporting.first.rendering.combat;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.BoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.CombatStatsContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class CombatBoxEncoderFactory extends Identificate implements BoxContentEncoderFactory {

  public CombatBoxEncoderFactory() {
    super(CombatStatsContentBoxEncoder.ID);
  }

  @Override
  public IBoxContentEncoder create(IResources resources) {
    IContentEncoder valueEncoder = new CombatValueEncoder();
    ITableEncoder rulesEncoder = new CombatRulesTableEncoder();
    return new CombatStatsContentBoxEncoder(rulesEncoder, valueEncoder);
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isFirstEdition();
  }
}
