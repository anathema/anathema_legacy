package net.sf.anathema.character.reporting.pdf.rendering.boxes.combat;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.BoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class SecondEditionCombatBoxEncoderFactory extends Identificate implements BoxContentEncoderFactory {

  public static final String ID = SecondEditionCombatBoxEncoderFactory.class.getName();

  public SecondEditionCombatBoxEncoderFactory() {
    super(ID);
  }

  @Override
  public IBoxContentEncoder create(IResources resources) {
    IContentEncoder valueEncoder = new SecondEditionCombatValueEncoder();
    ITableEncoder rulesEncoder = new SecondEditionCombatRulesTableEncoder();
    return new CombatStatsContentBoxEncoder(rulesEncoder, valueEncoder);
  }
}
