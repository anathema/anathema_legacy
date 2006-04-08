package net.sf.anathema.character.lunar.heartsblood.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.lunar.heartsblood.view.HeartsBloodView;
import net.sf.anathema.character.lunar.heartsblood.view.IAnimalFormSelectionView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;
import net.sf.anathema.lib.resources.IResources;

public class HeartsBloodPresenter {

  private final IHeartsBloodModel model;
  private final HeartsBloodView view;
  private final IResources resources;
  private final Map<IAnimalForm, IRemovableEntryView> viewsByForm = new HashMap<IAnimalForm, IRemovableEntryView>();

  public HeartsBloodPresenter(IHeartsBloodModel model, HeartsBloodView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    String animalFormString = resources.getString("Lunar.HeartsBlood.AnimalForm"); //$NON-NLS-1$
    String animalStaminaString = resources.getString("Lunar.HeartsBlood.AnimalStamina"); //$NON-NLS-1$
    String animalStrengthString = resources.getString("Lunar.HeartsBlood.AnimalStrength"); //$NON-NLS-1$
    final BasicUi basicUi = new BasicUi(resources);
    IAnimalFormSelectionView selectionView = view.createAnimalFormSelectionView(
        basicUi.getMediumAddIcon(),
        animalFormString,
        animalStrengthString,
        animalStaminaString);
    initSelectionViewListening(selectionView);
    initModelListening(basicUi, selectionView);
    for (IAnimalForm form : model.getEntries()) {
      addAnimalFormView(basicUi, form);
    }
    reset(selectionView);
  }

  private void initSelectionViewListening(IAnimalFormSelectionView selectionView) {
    selectionView.addNameListener(new IStringValueChangedListener() {
      public void valueChanged(String newValue) {
        model.setCurrentName(newValue);
      }
    });
    selectionView.addStrengthListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        model.setCurrentStrength(newValue);
      }
    });
    selectionView.addStaminaListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        model.setCurrentStamina(newValue);
      }
    });
    selectionView.addAddButtonListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        model.commitSelection();
      }
    });
  }

  private void addAnimalFormView(final BasicUi basicUi, final IAnimalForm form) {
    IRemovableEntryView formView = view.addEntryView(basicUi.getMediumRemoveIcon(), form.getName()
        + " (" + form.getStrength() + "/" + form.getStamina() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    viewsByForm.put(form, formView);
    formView.addButtonListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        model.removeEntry(form);
      }
    });
  }

  private void initModelListening(final BasicUi basicUi, final IAnimalFormSelectionView selectionView) {
    model.addModelChangeListener(new IRemovableEntryListener<IAnimalForm>() {
      public void entryAdded(final IAnimalForm form) {
        addAnimalFormView(basicUi, form);
        reset(selectionView);
      }

      public void entryRemoved(IAnimalForm form) {
        IRemovableEntryView removableView = viewsByForm.get(form);
        view.removeEntryView(removableView);
      }

      public void entryAllowed(boolean complete) {
        selectionView.setAddButtonEnabled(complete);
      }
    });
    model.addCharacterChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        for (IAnimalForm form : viewsByForm.keySet()) {
          if (form.isCreationLearned()) {
            IRemovableEntryView formView = viewsByForm.get(form);
            formView.setButtonEnabled(!experienced);
          }
        }
      }
    });
  }

  private void reset(final IAnimalFormSelectionView selectionView) {
    selectionView.setName(null);
    selectionView.setStrength(1);
    selectionView.setStamina(1);
    model.setCurrentName(null);
    model.setCurrentStamina(1);
    model.setCurrentStrength(1);
  }
}
