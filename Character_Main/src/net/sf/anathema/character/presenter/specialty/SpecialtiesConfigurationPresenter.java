package net.sf.anathema.character.presenter.specialty;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.framework.resources.TraitInternationalizer;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.library.trait.specialties.ITraitReferencesChangeListener;
import net.sf.anathema.character.library.trait.specialties.SpecialtiesModel;
import net.sf.anathema.character.library.trait.specialties.Specialty;
import net.sf.anathema.character.library.trait.subtrait.ISpecialtyListener;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.main.model.experience.ExperienceChange;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.framework.view.AbstractSelectCellRenderer;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.collection.IdentityMapping;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

import java.util.Arrays;
import java.util.Comparator;

public class SpecialtiesConfigurationPresenter implements Presenter {

  private final IdentityMapping<Specialty, ExtensibleTraitView> viewsBySpecialty = new IdentityMapping<>();
  private final IdentityMapping<Specialty, Tool> deleteToolsBySpecialty = new IdentityMapping<>();
  private final TraitInternationalizer i18ner;
  private final Comparator<ITraitReference> comparator;

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

  private final Resources resources;
  private final ISpecialtiesConfigurationView configurationView;
  private Hero hero;
  private final SpecialtiesModel specialtyManagement;

  public SpecialtiesConfigurationPresenter(Hero hero, SpecialtiesModel specialtyManagement,
                                           ISpecialtiesConfigurationView configurationView, Resources resources) {
    this.hero = hero;
    this.specialtyManagement = specialtyManagement;
    this.configurationView = configurationView;
    this.resources = resources;
    this.i18ner = new TraitInternationalizer(resources);
    this.comparator = new TraitReferenceByNameComparator(i18ner);
  }

  @Override
  public void initPresentation() {
    initTraitListening();
    RelativePath addIcon = new BasicUi().getAddIconPath();
    final IButtonControlledComboEditView<ITraitReference> specialtySelectionView = configurationView.addSpecialtySelectionView(
            resources.getString("SpecialtyConfigurationView.SelectionCombo.Label"),
            new AbstractSelectCellRenderer<ITraitReference>(resources) {

              @Override
              protected String getCustomizedDisplayValue(ITraitReference value) {
                return i18ner.getScreenName(value);
              }
            }, addIcon);
    setObjects(specialtySelectionView);
    specialtySelectionView.addSelectionChangedListener(new ObjectValueListener<ITraitReference>() {
      @Override
      public void valueChanged(ITraitReference newValue) {
        specialtyManagement.setCurrentTrait(newValue);
      }
    });
    specialtySelectionView.addEditChangedListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newSpecialtyName) {
        specialtyManagement.setCurrentSpecialtyName(newSpecialtyName);
      }
    });
    specialtySelectionView.whenAddButtonIsClicked(new Command() {
      @Override
      public void execute() {
        specialtyManagement.commitSelection();
        reset(specialtySelectionView);
      }
    });
    specialtyManagement.addSelectionChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        specialtySelectionView.setButtonEnabled(specialtyManagement.isEntryComplete());
      }
    });
    specialtyManagement.addTraitListChangeListener(new ITraitReferencesChangeListener() {
      @Override
      public void referenceAdded(ITraitReference reference) {
        setObjects(specialtySelectionView);
        getSpecialtyContainer(reference).addSubTraitListener(specialtyListener);
      }

      @Override
      public void referenceRemoved(ITraitReference reference) {
        specialtySelectionView.setObjects(specialtyManagement.getAllEligibleTraits());
      }
    });
    reset(specialtySelectionView);
    for (ITraitReference reference : getAllTraits()) {
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
        setObjects(specialtySelectionView);
      }
    });
    updateSpecialtyViewButtons();
  }

  private void setObjects(IButtonControlledComboEditView<ITraitReference> specialtySelectionView) {
    ITraitReference[] allTraits = getAllEligibleTraits();
    Arrays.sort(allTraits, comparator);
    specialtySelectionView.setObjects(allTraits);
  }

  private void initTraitListening() {
    for (ITraitReference reference : getAllTraits()) {
      getSpecialtyContainer(reference).addSubTraitListener(specialtyListener);
    }
  }

  private ISubTraitContainer getSpecialtyContainer(ITraitReference reference) {
    return specialtyManagement.getSpecialtiesContainer(reference);
  }

  private void reset(IButtonControlledComboEditView<?> specialtySelectionView) {
    specialtyManagement.clear();
    specialtySelectionView.clear();
  }

  private ITraitReference[] getAllTraits() {
    return specialtyManagement.getAllTraits();
  }

  private ITraitReference[] getAllEligibleTraits() {
    return specialtyManagement.getAllEligibleTraits();
  }

  private void updateSpecialtyViewButtons() {
    for (ITraitReference trait : getAllTraits()) {
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
    final ITraitReference traitReference = specialty.getTraitReference();
    String traitName = i18ner.getScreenName(traitReference);
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
        getSpecialtyContainer(traitReference).removeSubTrait(specialty);
      }
    });
    viewsBySpecialty.put(specialty, specialtyView);
    deleteToolsBySpecialty.put(specialty, deleteTool);
  }
}