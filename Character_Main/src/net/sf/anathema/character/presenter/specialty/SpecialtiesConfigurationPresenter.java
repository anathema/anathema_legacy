package net.sf.anathema.character.presenter.specialty;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.presenter.AbstractTraitPresenter;
import net.sf.anathema.character.library.trait.specialties.ISpecialtyConfiguration;
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

public class SpecialtiesConfigurationPresenter extends AbstractTraitPresenter implements IPresenter {

  private final IdentityMapping<ISubTrait, ISpecialtyView> viewsBySpecialty = new IdentityMapping<ISubTrait, ISpecialtyView>();

  private final ISubTraitListener specialtyListener = new ISubTraitListener() {
    public void subTraitAdded(ISubTrait specialty) {
      addSpecialtyView(specialty);
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
  private final ISpecialtyConfiguration specialtyManagement;

  public SpecialtiesConfigurationPresenter(
      ISpecialtyConfiguration specialtyManagement,
      ISpecialtiesConfigurationView configurationView,
      IResources resources) {
    this.specialtyManagement = specialtyManagement;
    this.configurationView = configurationView;
    this.resources = resources;
  }

  public void initPresentation() {
    initTraitListening();
    Icon addIcon = new BasicUi(resources).getAddIcon();
    final IButtonControlledComboEditView<ITraitType> specialtySelectionView = configurationView.addSpecialtySelectionView(
        resources.getString("SpecialtyConfigurationView.SelectionCombo.Label"), //$NON-NLS-1$
        getAllTraitsTypes(),
        new AbstractSelectCellRenderer(resources) {
          @Override
          protected Object getCustomizedDisplayValue(Object value) {
            return resources.getString(((ITraitType) value).getId());
          }
        },
        addIcon);
    specialtySelectionView.addSelectionChangedListener(new IObjectValueChangedListener<ITraitType>() {
      public void valueChanged(ITraitType newValue) {
        specialtyManagement.setCurrentTraitType(newValue);
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
    specialtyManagement.addCurrentSelectionListener(new IChangeListener() {
      public void changeOccured() {
        specialtySelectionView.setButtonEnabled(specialtyManagement.isEntryComplete());
      }
    });
    reset(specialtySelectionView);
    for (ITraitType traitType : getAllTraitsTypes()) {
      for (ISubTrait specialty : getSpecialtyContainerType(traitType).getSubTraits()) {
        addSpecialtyView(specialty);
      }
    }
    initExperiencedListening();
  }

  private void initTraitListening() {
    for (ITraitType traitType : getAllTraitsTypes()) {
      getSpecialtyContainerType(traitType).addSubTraitListener(specialtyListener);
    }
  }

  private ISubTraitContainer getSpecialtyContainerType(ITraitType traitType) {
    return specialtyManagement.getSpecialtiesContainer(traitType);
  }

  private void reset(final IButtonControlledComboEditView<ITraitType> specialtySelectionView) {
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

  private ITraitType[] getAllTraitsTypes() {
    return specialtyManagement.getAllTraitTypes();
  }

  private void updateSpecialtyViewButtons() {
    for (ITraitType trait : getAllTraitsTypes()) {
      for (ISubTrait specialty : getSpecialtyContainerType(trait).getSubTraits()) {
        ISpecialtyView view = viewsBySpecialty.get(specialty);
        view.setDeleteButtonEnabled(specialty.getCreationValue() == 0 || !specialtyManagement.isExperienced());
      }
    }
  }

  private void addSpecialtyView(final ISubTrait specialty) {
    String traitTypeId = specialty.getBasicTraitType().getId();
    String traitName = resources.getString(traitTypeId);
    String specialtyName = specialty.getName();
    Icon deleteIcon = new BasicUi(resources).getRemoveIcon();
    final ISpecialtyView specialtyView = configurationView.addSpecialtyView(
        traitName,
        specialtyName,
        deleteIcon,
        specialty.getCurrentValue(),
        specialty.getMaximalValue());
    addModelValueListener(specialty, specialtyView);
    addViewValueListener(specialtyView, specialty);
    specialtyView.addDeleteListener(new IChangeListener() {
      public void changeOccured() {
        getSpecialtyContainerType(specialty.getBasicTraitType()).removeSubTrait(specialty);
      }
    });
    viewsBySpecialty.put(specialty, specialtyView);
  }
}
