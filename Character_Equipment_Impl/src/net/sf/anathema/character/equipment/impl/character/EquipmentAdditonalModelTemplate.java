package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.generic.rules.IEditionVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.lib.util.Identificate;

public class EquipmentAdditonalModelTemplate extends Identificate implements IGlobalAdditionalTemplate {

  public static final String ID = "Equipment"; //$NON-NLS-1$

  public EquipmentAdditonalModelTemplate() {
    super(ID);
  }

  public boolean supportsEdition(IExaltedEdition edition) {
    final boolean[] supported = new boolean[1];
    edition.accept(new IEditionVisitor() {
      public void visitFirstEdition(IExaltedEdition visitedEdition) {
        supported[0] = false;
      }

      public void visitSecondEdition(IExaltedEdition visitedEdition) {
        supported[0] = true;
      }
    });
    return supported[0];
  }
}