package net.sf.anathema.character.presenter.specialty;

import javax.swing.Icon;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.library.trait.IFavorableTrait;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.presenter.AbstractTraitPresenter;
import net.sf.anathema.character.library.trait.specialty.ISpecialty;
import net.sf.anathema.character.library.trait.specialty.ISpecialtyListener;
import net.sf.anathema.character.view.ISpecialtyView;
import net.sf.anathema.character.view.basic.IButtonControlledComboEditView;
import net.sf.anathema.framework.view.AbstractSelectCellRenderer;
import net.sf.anathema.lib.collection.IdentityMapping;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.ITwoObjectsValueChangedListener;
import net.sf.anathema.lib.resources.IResources;

public class SpecialtyConfigurationPresenter extends AbstractTraitPresenter {

  private final IdentityMapping<ISpecialty, ISpecialtyView> viewsBySpecialty = new IdentityMapping<ISpecialty, ISpecialtyView>();

  private final ISpecialtyListener specialtyListener = new ISpecialtyListener() {

    public void specialtyAdded(ISpecialty specialty) {
      addSpecialtyView(specialty);
    }

    public void specialtyRemoved(ISpecialty specialty) {
      removeSpecialtyView(specialty);
    }

    public void specialtyValueChanged() {
      // Nothing to do
    }
  };

  private final ITrait[] allTraits;
  private final IResources resources;
  private final ISpecialtyConfigurationView configurationView;
  private final IBasicCharacterData basicCharacterData;
  private final ICharacterListening characterListening;

  public SpecialtyConfigurationPresenter(
      ITrait[] allTraits,
      IBasicCharacterData basicCharacterData,
      ICharacterListening characterListening,
      ISpecialtyConfigurationView configurationView,
      IResources resources) {
    this.allTraits = allTraits;
    this.basicCharacterData = basicCharacterData;
    this.characterListening = characterListening;
    this.configurationView = configurationView;
    this.resources = resources;
  }

  private void initTraitListening() {
    for (ITrait trait : getAllTraits()) {
      trait.getSpecialtiesContainer().addSpecialtyListener(specialtyListener);
    }
  }

  private void resetSpecialtyView(final IButtonControlledComboEditView specialtySelectionView) {
    specialtySelectionView.setText(""); //$NON-NLS-1$
    specialtySelectionView.setSelectedObject(null);
  }

  public void initPresentation() {
    initTraitListening();
    Icon addIcon = resources.getImageIcon("Green+20.png"); //$NON-NLS-1$
    final IButtonControlledComboEditView specialtySelectionView = configurationView.addSpecialtySelectionView(
        resources.getString("SpecialtyConfigurationView.SelectionCombo.Label"), //$NON-NLS-1$
        getAllTraits(),
        new AbstractSelectCellRenderer(resources) {
          @Override
          protected Object getCustomizedDisplayValue(Object value) {
            return resources.getString(((IFavorableTrait) value).getType().getId());
          }
        },
        addIcon);
    specialtySelectionView.addObjectSelectionChangedListener(new ITwoObjectsValueChangedListener() {
      public void valueChanged(Object oldValue1, Object oldValue2, Object newValue1, Object newValue2) {
        if (newValue1 instanceof IFavorableTrait) {
          IFavorableTrait ability = (IFavorableTrait) newValue1;
          String specialtyName = newValue2.toString();
          if (!specialtyName.equals("")) { //$NON-NLS-1$
            ability.getSpecialtiesContainer().addSpecialty(specialtyName);
            resetSpecialtyView(specialtySelectionView);
          }
        }
      }
    });
    resetSpecialtyView(specialtySelectionView);
    for (ITrait trait : getAllTraits()) {
      for (ISpecialty specialty : trait.getSpecialtiesContainer().getSpecialties()) {
        addSpecialtyView(specialty);
      }
    }
    initExperiencedListening();
  }

  private void initExperiencedListening() {
    characterListening.addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        updateSpecialtyViewButtons();
      }
    });
    updateSpecialtyViewButtons();
  }

  protected void removeSpecialtyView(ISpecialty specialty) {
    ISpecialtyView view = viewsBySpecialty.get(specialty);
    viewsBySpecialty.remove(specialty);
    view.delete();
  }

  private ITrait[] getAllTraits() {
    return allTraits;
  }

  private void updateSpecialtyViewButtons() {
    for (ITrait trait : getAllTraits()) {
      for (ISpecialty specialty : trait.getSpecialtiesContainer().getSpecialties()) {
        ISpecialtyView view = viewsBySpecialty.get(specialty);
        view.setDeleteButtonEnabled(specialty.getCreationValue() == 0 || !basicCharacterData.isExperienced());
      }
    }
  }

  private void addSpecialtyView(final ISpecialty specialty) {
    String traitTypeId = specialty.getBasicTrait().getType().getId();
    String traitName = resources.getString(traitTypeId);
    String specialtyName = specialty.getName();
    Icon deleteIcon = resources.getImageIcon("tools/RedX20.png"); //$NON-NLS-1$
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
        specialty.getBasicTrait().getSpecialtiesContainer().removeSpecialty(specialty);
      }
    });
    viewsBySpecialty.put(specialty, specialtyView);
  }
}
