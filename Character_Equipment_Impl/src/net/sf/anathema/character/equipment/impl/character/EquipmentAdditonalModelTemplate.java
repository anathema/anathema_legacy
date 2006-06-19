package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.lib.util.Identificate;

public class EquipmentAdditonalModelTemplate extends Identificate implements IGlobalAdditionalTemplate {

  public static final String ID = "Equipment"; //$NON-NLS-1$

  public EquipmentAdditonalModelTemplate() {
    super(ID);
  }

  public boolean supportsEdition(IExaltedEdition edition) {
    return true;
  }
}