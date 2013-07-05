package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public interface SpecialtiesConfigurationView {

  ExtensibleTraitView addSpecialtyView(String abilityName, String specialtyName, RelativePath deleteIcon, int value, int maxValue);

  SpecialtyCreationView addSpecialtySelectionView(AgnosticUIConfiguration<ITraitReference> configuration, RelativePath addIcon);
}