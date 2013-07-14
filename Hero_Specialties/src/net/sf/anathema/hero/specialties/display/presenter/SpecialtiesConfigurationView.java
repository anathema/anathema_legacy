package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.display.ExtensibleTraitView;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public interface SpecialtiesConfigurationView {

  ExtensibleTraitView addSpecialtyView(String abilityName, String specialtyName, RelativePath deleteIcon, int value, int maxValue);

  SpecialtyCreationView addSpecialtyCreationView(AgnosticUIConfiguration<TraitType> configuration, RelativePath addIcon);
}