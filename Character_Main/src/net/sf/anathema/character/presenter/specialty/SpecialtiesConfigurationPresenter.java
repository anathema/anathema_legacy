package net.sf.anathema.character.presenter.specialty;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.GlobalCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.resources.TraitInternationalizer;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.specialties.ISpecialty;
import net.sf.anathema.character.library.trait.specialties.ITraitReferencesChangeListener;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitListener;
import net.sf.anathema.character.view.ISpecialtyView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.framework.view.AbstractSelectCellRenderer;
import net.sf.anathema.lib.collection.IdentityMapping;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.Icon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

public class SpecialtiesConfigurationPresenter implements Presenter {

  private final IdentityMapping<ISubTrait, ISpecialtyView> viewsBySpecialty = new IdentityMapping<>();
  private final TraitInternationalizer i18ner;
  private final Comparator<ITraitReference> comparator;

  private final ISubTraitListener specialtyListener = new ISubTraitListener() {
    @Override
    public void subTraitAdded(ISubTrait specialty) {
      addSpecialtyView((ISpecialty) specialty);
    }

    @Override
    public void subTraitRemoved(ISubTrait specialty) {
      ISpecialtyView view = viewsBySpecialty.get(specialty);
      viewsBySpecialty.remove(specialty);
      view.delete();
    }

    @Override
    public void subTraitValueChanged() {
      // Nothing to do
    }
  };

  private final Resources resources;
  private final ISpecialtiesConfigurationView configurationView;
  private final ISpecialtiesConfiguration specialtyManagement;

  public SpecialtiesConfigurationPresenter(ISpecialtiesConfiguration specialtyManagement, ISpecialtiesConfigurationView configurationView,
                                           Resources resources) {
    this.specialtyManagement = specialtyManagement;
    this.configurationView = configurationView;
    this.resources = resources;
    this.i18ner = new TraitInternationalizer(resources);
    this.comparator = new TraitReferenceByNameComparator(i18ner);
  }

  @Override
  public void initPresentation() {
    initTraitListening();
    Icon addIcon = new BasicUi().getAddIcon();
    final IButtonControlledComboEditView<ITraitReference> specialtySelectionView =
            configurationView.addSpecialtySelectionView(resources.getString("SpecialtyConfigurationView.SelectionCombo.Label"), //$NON-NLS-1$
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
    specialtySelectionView.addButtonListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        specialtyManagement.commitSelection();
        reset(specialtySelectionView);
      }
    });
    specialtyManagement.addSelectionChangeListener(new IChangeListener() {
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
      for (ISubTrait specialty : getSpecialtyContainer(reference).getSubTraits()) {
        addSpecialtyView((ISpecialty) specialty);
      }
    }
    specialtyManagement.addCharacterChangeListener(new GlobalCharacterChangeAdapter() {

      @Override
      public void characterChanged() {
        setObjects(specialtySelectionView);
      }

      @Override
      public void experiencedChanged(boolean experienced) {
        updateSpecialtyViewButtons();
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
      for (ISubTrait specialty : getSpecialtyContainer(trait).getSubTraits()) {
        ISpecialtyView view = viewsBySpecialty.get(specialty);
        view.setDeleteButtonEnabled(specialty.getCreationValue() == 0 || !specialtyManagement.isExperienced());
      }
    }
  }

  private void addSpecialtyView(final ISpecialty specialty) {
    final ITraitReference traitReference = specialty.getTraitReference();
    String traitName = i18ner.getScreenName(traitReference);
    String specialtyName = specialty.getName();
    Icon deleteIcon = new BasicUi().getRemoveIcon();
    ISpecialtyView specialtyView =
            configurationView.addSpecialtyView(traitName, specialtyName, deleteIcon, specialty.getCurrentValue(), specialty.getMaximalValue());
    new TraitPresenter(specialty, specialtyView).initPresentation();
    specialtyView.addDeleteListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        getSpecialtyContainer(traitReference).removeSubTrait(specialty);
      }
    });
    viewsBySpecialty.put(specialty, specialtyView);
  }

}
