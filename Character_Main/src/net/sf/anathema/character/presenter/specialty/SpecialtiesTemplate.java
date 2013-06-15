package net.sf.anathema.character.presenter.specialty;

import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class SpecialtiesTemplate extends SimpleIdentifier implements IGlobalAdditionalTemplate {

  public static final String ID = "Specialties";

  public SpecialtiesTemplate() {
    super(ID);
  }
}
