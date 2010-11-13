package net.sf.anathema.character.presenter.specialty;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.Icon;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.resources.TraitInternationalizer;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.library.trait.specialties.ISpecialtiesConfiguration;
import net.sf.anathema.character.library.trait.specialties.ISpecialty;
import net.sf.anathema.character.library.trait.specialties.ITraitReferencesChangeListener;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitListener;
import net.sf.anathema.character.view.ISpecialtyView;
import net.sf.anathema.character.view.basic.IButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.view.AbstractSelectCellRenderer;
import net.sf.anathema.lib.collection.IdentityMapping;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class SpecialtiesConfigurationPresenter implements IPresenter {

  private final IdentityMapping<ISubTrait, ISpecialtyView> viewsBySpecialty = new IdentityMapping<ISubTrait, ISpecialtyView>();
  private final TraitInternationalizer i18ner;
  private final Comparator<ITraitReference> comparator = new Comparator<ITraitReference>() {
    public int compare(ITraitReference o1, ITraitReference o2) {
      String name1 = i18ner.getScreenName(o1);
      String name2 = i18ner.getScreenName(o2);
      return name1.compareToIgnoreCase(name2);
    }
  };

  private final ISubTraitListener specialtyListener = new ISubTraitListener() {
    public void subTraitAdded(ISubTrait specialty) {
      addSpecialtyView((ISpecialty) specialty);
    }

    public void subTraitRemoved(ISubTrait specialty) {
      ISpecialtyView view = viewsBySpecialty.get(specialty);
      viewsBySpecialty.remove(specialty);
      view.delete();
    }

    public void subTraitValueChanged() {
      // Nothing to do
    }
  };

  private final IResources resources;
  private final ISpecialtiesConfigurationView configurationView;
  private final ISpecialtiesConfiguration specialtyManagement;

  public SpecialtiesConfigurationPresenter(
      ISpecialtiesConfiguration specialtyManagement,
      ISpecialtiesConfigurationView configurationView,
      IResources resources) {
    this.specialtyManagement = specialtyManagement;
    this.configurationView = configurationView;
    this.resources = resources;
    this.i18ner = new TraitInternationalizer(resources);
  }

  public void initPresentation() {
    initTraitListening();
    Icon addIcon = new BasicUi(resources).getAddIcon();
    final IButtonControlledComboEditView<ITraitReference> specialtySelectionView = configurationView.addSpecialtySelectionView(
        resources.getString("SpecialtyConfigurationView.SelectionCombo.Label"), //$NON-NLS-1$
        new AbstractSelectCellRenderer<ITraitReference>(resources) {
          @Override
          protected String getCustomizedDisplayValue(ITraitReference value) {
            return i18ner.getScreenName(value);
          }
        },
        addIcon);
    setObjects(specialtySelectionView);
    specialtySelectionView.addSelectionChangedListener(new IObjectValueChangedListener<ITraitReference>() {
      public void valueChanged(ITraitReference newValue) {
        specialtyManagement.setCurrentTrait(newValue);
      }
    });
    specialtySelectionView.addEditChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newSpecialtyName) {
        specialtyManagement.setCurrentSpecialtyName(newSpecialtyName);
      }
    });
    specialtySelectionView.addButtonListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        specialtyManagement.commitSelection();
        reset(specialtySelectionView);
      }
    });
    specialtyManagement.addSelectionChangeListener(new IChangeListener() {
      public void changeOccured() {
        specialtySelectionView.setButtonEnabled(specialtyManagement.isEntryComplete());
      }
    });
    specialtyManagement.addTraitListChangeListener(new ITraitReferencesChangeListener() {
      public void referenceAdded(ITraitReference reference) {
        setObjects(specialtySelectionView);
        getSpecialtyContainer(reference).addSubTraitListener(specialtyListener);
      }

      public void referenceRemoved(ITraitReference reference) {
        specialtySelectionView.setObjects(specialtyManagement.getAllTraits());
      }
    });
    reset(specialtySelectionView);
    for (ITraitReference reference : getAllTraits()) {
      for (ISubTrait specialty : getSpecialtyContainer(reference).getSubTraits()) {
        addSpecialtyView((ISpecialty) specialty);
      }
    }
    initExperiencedListening();
  }

  private void setObjects(IButtonControlledComboEditView<ITraitReference> specialtySelectionView) {
    ITraitReference[] allTraits = getAllTraits();
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

  private void reset(final IButtonControlledComboEditView< ? > specialtySelectionView) {
    specialtyManagement.clear();
    specialtySelectionView.clear();
  }

  private void initExperiencedListening() {
    specialtyManagement.addCharacterChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        updateSpecialtyViewButtons();
      }
    });
    updateSpecialtyViewButtons();
  }

  protected void removeSpecialtyView(ISubTrait specialty) {
    ISpecialtyView view = viewsBySpecialty.get(specialty);
    viewsBySpecialty.remove(specialty);
    view.delete();
  }

  private ITraitReference[] getAllTraits() {
    return specialtyManagement.getAllTraits();
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
    Icon deleteIcon = new BasicUi(resources).getRemoveIcon();
    final ISpecialtyView specialtyView = configurationView.addSpecialtyView(
        traitName,
        specialtyName,
        deleteIcon,
        specialty.getCurrentValue(),
        specialty.getMaximalValue());
    new TraitPresenter(specialty, specialtyView).initPresentation();
    specialtyView.addDeleteListener(new IChangeListener() {
      public void changeOccured() {
        getSpecialtyContainer(traitReference).removeSubTrait(specialty);
      }
    });
    viewsBySpecialty.put(specialty, specialtyView);
  }
}
