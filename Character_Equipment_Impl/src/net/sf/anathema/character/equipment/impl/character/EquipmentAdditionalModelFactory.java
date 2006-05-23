package net.sf.anathema.character.equipment.impl.character;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.lib.control.change.IChangeListener;

public class EquipmentAdditionalModelFactory implements IAdditionalModelFactory {

  public IAdditionalModel createModel(
      IAdditionalTemplate additionalTemplate,
      ICharacterModelContext context,
      IChangeListener[] listeners) {
    if (context.getBasicCharacterContext().getRuleSet().getEdition() != ExaltedEdition.SecondEdition) {
      return null;
    }
    return new EquipmentAdditionalModel(context);
  }
}