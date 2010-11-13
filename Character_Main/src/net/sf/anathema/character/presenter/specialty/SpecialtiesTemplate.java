package net.sf.anathema.character.presenter.specialty;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.lib.util.Identificate;

public class SpecialtiesTemplate extends Identificate implements IGlobalAdditionalTemplate {

  public static final String ID = "Specialties"; //$NON-NLS-1$

  public SpecialtiesTemplate() {
    super(ID);
  }

  public boolean supportsEdition(IExaltedEdition edition) {
    return true;
  }
}
