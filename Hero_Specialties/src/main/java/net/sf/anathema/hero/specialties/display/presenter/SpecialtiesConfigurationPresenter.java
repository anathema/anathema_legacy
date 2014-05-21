package net.sf.anathema.hero.specialties.display.presenter;

import net.sf.anathema.hero.traits.display.TraitTypeInternationalizer;
import net.sf.anathema.hero.traits.display.TraitPresenter;
import net.sf.anathema.hero.specialties.SpecialtiesModel;
import net.sf.anathema.hero.specialties.Specialty;
import net.sf.anathema.hero.specialties.ISpecialtyListener;
import net.sf.anathema.hero.specialties.ISubTraitContainer;
import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.hero.display.ExtensibleTraitView;
import net.sf.anathema.hero.experience.ExperienceChange;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeFlavor;
import net.sf.anathema.hero.model.change.FlavoredChangeListener;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.collection.IdentityMapping;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

import java.util.Arrays;
import java.util.Comparator;

public class SpecialtiesConfigurationPresenter {

  private final IdentityMapping<Specialty, ExtensibleTraitView> viewsBySpecialty = new IdentityMapping<>();
  private final IdentityMapping<Specialty, Tool> deleteToolsBySpecialty = new IdentityMapping<>();
  private final TraitTypeInternationalizer i18ner;
  private final Comparator<TraitType> comparator;

  private final ISpecialtyListener specialtyListener = new ISpecialtyListener() {
    @Override
    public void subTraitAdded(Specialty specialty) {
      addSpecialtyView(specialty);
    }

    @Override
    public void subTraitRemoved(Specialty specialty) {
      ExtensibleTraitView view = viewsBySpecialty.get(specialty);
      viewsBySpecialty.remove(specialty);
      view.remove();
    }

    @Override
    public void subTraitValueChanged() {
      // Nothing to do
    }
  };

  private final SpecialtiesConfigurationView configurationView;
  private Hero hero;
  private final SpecialtiesModel specialtyManagement;

  public SpecialtiesConfigurationPresenter(Hero hero, SpecialtiesModel specialtyManagement,
                                           SpecialtiesConfigurationView configurationView, Resources resources) {
    this.hero = hero;
    this.specialtyManagement = specialtyManagement;
    this.configurationView = configurationView;
    this.i18ner = new TraitTypeInternationalizer(resources);
    this.comparator = new TraitTypeByNameComparator(i18ner);
  }

  public void initPresentation() {
    initTraitListening();
    RelativePath addIcon = new BasicUi().getAddIconPath();
    AgnosticUIConfiguration<TraitType> configuration = new TraitTypeUiConfiguration(i18ner);
    final SpecialtyCreationView creationView = configurationView.addSpecialtyCreationView(configuration, addIcon);
    setObjects(creationView);
    creationView.addSelectionChangedListener(new ObjectValueListener<TraitType>() {
      @Override
      public void valueChanged(TraitType newValue) {
        specialtyManagement.setCurrentTrait(newValue);
      }
    });
    creationView.addEditChangedListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newSpecialtyName) {
        specialtyManagement.setCurrentSpecialtyName(newSpecialtyName);
      }
    });
    creationView.whenAddButtonIsClicked(new Command() {
      @Override
      public void execute() {
        specialtyManagement.commitSelection();
        resetAndSyncView(creationView);
      }
    });
    specialtyManagement.addSelectionChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        creationView.setButtonEnabled(specialtyManagement.isEntryComplete());
      }
    });
    for (TraitType reference : getAllTraits()) {
      for (Specialty specialty : getSpecialtyContainer(reference).getSubTraits()) {
        addSpecialtyView(specialty);
      }
    }
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
          updateSpecialtyViewButtons();
        }
        setObjects(creationView);
      }
    });
    resetAndSyncView(creationView);
    updateSpecialtyViewButtons();
  }

  private void resetAndSyncView(SpecialtyCreationView creationView) {
    reset();
    sync(creationView);
  }

  private void reset() {
    specialtyManagement.clear();
    specialtyManagement.setCurrentTrait(getSortedEligibleTraits()[0]);
  }

  private void sync(SpecialtyCreationView creationView) {
    creationView.selectTrait(specialtyManagement.getCurrentTrait());
    creationView.enterName(specialtyManagement.getCurrentName());
  }

  private void setObjects(SpecialtyCreationView specialtySelectionView) {
    TraitType[] allTraits = getSortedEligibleTraits();
    specialtySelectionView.setObjects(allTraits);
  }

  private TraitType[] getSortedEligibleTraits() {
    TraitType[] allTraits = getAllEligibleTraits();
    Arrays.sort(allTraits, comparator);
    return allTraits;
  }

  private void initTraitListening() {
    for (TraitType reference : getAllTraits()) {
      getSpecialtyContainer(reference).addSubTraitListener(specialtyListener);
    }
  }

  private TraitType[] getAllTraits() {
    return specialtyManagement.getAllParentTraits();
  }

  private TraitType[] getAllEligibleTraits() {
    return specialtyManagement.getAllEligibleParentTraits();
  }

  private void updateSpecialtyViewButtons() {
    for (TraitType trait : getAllTraits()) {
      for (Specialty specialty : getSpecialtyContainer(trait).getSubTraits()) {
        Tool tool = deleteToolsBySpecialty.get(specialty);
        if (specialty.getCreationValue() == 0 || !specialtyManagement.isExperienced()) {
          tool.enable();
        } else {
          tool.disable();
        }
      }
    }
  }

  private void addSpecialtyView(final Specialty specialty) {
    final TraitType type = specialty.getBasicTraitType();
    String traitName = i18ner.getScreenName(type);
    String specialtyName = specialty.getName();
    RelativePath deleteIcon = new BasicUi().getRemoveIconPath();
    final ExtensibleTraitView specialtyView = configurationView.addSpecialtyView(traitName, specialtyName, deleteIcon,
            specialty.getCurrentValue(), specialty.getMaximalValue());
    new TraitPresenter(specialty, specialtyView.getIntValueView()).initPresentation();
    Tool deleteTool = specialtyView.addToolBehind();
    deleteTool.setIcon(deleteIcon);
    deleteTool.setCommand(new Command() {
      @Override
      public void execute() {
        getSpecialtyContainer(type).removeSubTrait(specialty);
      }
    });
    viewsBySpecialty.put(specialty, specialtyView);
    deleteToolsBySpecialty.put(specialty, deleteTool);
  }

  private ISubTraitContainer getSpecialtyContainer(TraitType type) {
    return specialtyManagement.getSpecialtiesContainer(type);
  }

}