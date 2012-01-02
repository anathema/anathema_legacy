package net.sf.anathema.character.reporting.pdf.rendering.boxes.combat;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.BoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class FirstEditionCombatBoxEncoderFactory extends Identificate implements BoxContentEncoderFactory {

  public static final String ID = FirstEditionCombatBoxEncoderFactory.class.getName();

  public FirstEditionCombatBoxEncoderFactory() {
    super(ID);
  }

  @Override
  public IBoxContentEncoder create(IResources resources) {
    IContentEncoder valueEncoder = new FirstEditionCombatValueEncoder();
    ITableEncoder rulesEncoder = new FirstEditionCombatRulesTableEncoder();
    return new CombatStatsContentBoxEncoder(rulesEncoder, valueEncoder);
  }
}
