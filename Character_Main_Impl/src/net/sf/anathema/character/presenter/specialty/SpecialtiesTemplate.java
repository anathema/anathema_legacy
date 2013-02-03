package net.sf.anathema.character.presenter.specialty;

import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.lib.util.Identifier;

public class SpecialtiesTemplate extends Identifier implements IGlobalAdditionalTemplate {

  public static final String ID = "Specialties"; //$NON-NLS-1$

  public SpecialtiesTemplate() {
    super(ID);
  }
}
