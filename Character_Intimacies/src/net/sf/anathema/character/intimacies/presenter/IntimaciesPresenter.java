package net.sf.anathema.character.intimacies.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.intimacies.view.IIntimaciesSelectionView;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.trait.presenter.AbstractTraitPresenter;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;
import net.sf.anathema.lib.resources.IResources;

public class IntimaciesPresenter extends AbstractTraitPresenter {

  private final IIntimaciesModel model;
  private final IIntimaciesView view;
  private final IResources resources;
  private final Map<IIntimacy, IRemovableTraitView> viewsByIntimacy = new HashMap<IIntimacy, IRemovableTraitView>();

  public IntimaciesPresenter(IIntimaciesModel model, IIntimaciesView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    String labelText = resources.getString("Intimacies.AddIntimacy");
    BasicUi basicUi = new BasicUi(resources);
    IIntimaciesSelectionView selectionView = view.addSelectionView(labelText, basicUi.getMediumAddIcon());
    initSelectionViewListening(selectionView);
    initModelListening(basicUi, selectionView);
    for (IIntimacy intimacy : model.getEntries()) {
      addIntimacyView(basicUi, intimacy);
    }
    reset(selectionView);
  }

  private void initModelListening(final BasicUi basicUi, final IIntimaciesSelectionView selectionView) {
    model.addModelChangeListener(new IRemovableEntryListener<IIntimacy>() {
      public void entryAdded(final IIntimacy intimacy) {
        addIntimacyView(basicUi, intimacy);
        reset(selectionView);
      }

      public void entryRemoved(IIntimacy form) {
        IRemovableEntryView removableView = viewsByIntimacy.get(form);
        view.removeEntryView(removableView);
      }

      public void entryComplete(boolean complete) {
        selectionView.setAddButtonEnabled(complete);
      }
    });
  }

  private void addIntimacyView(final BasicUi basicUi, final IIntimacy intimacy) {
    final IRemovableTraitView<IToggleButtonTraitView> intimacyView = view.addEntryView(
        basicUi.getMediumRemoveIcon(),
        intimacy.getName());
    intimacyView.setMaximum(model.getMaximalConvictionValue());
    intimacyView.setValue(intimacy.getTrait().getCurrentValue());
    addModelValueListener(intimacy.getTrait(), intimacyView);
    addViewValueListener(intimacyView, intimacy.getTrait());
    viewsByIntimacy.put(intimacy, intimacyView);
    intimacyView.addButtonListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        model.removeEntry(intimacy);
      }
    });
    intimacyView.getInnerView().addButtonSelectedListener(new IBooleanValueChangedListener() {
      public void valueChanged(boolean newValue) {
        intimacy.setComplete(newValue);
      }
    });
    intimacy.addCompletionListener(new IBooleanValueChangedListener() {
      public void valueChanged(boolean newValue) {
        intimacyView.getInnerView().setButtonState(newValue, true);
      }
    });
  }

  private void initSelectionViewListening(IIntimaciesSelectionView selectionView) {
    selectionView.addTextChangeListener(new IStringValueChangedListener() {
      public void valueChanged(String newValue) {
        model.setCurrentName(newValue);
      }
    });
    selectionView.addAddButtonListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        model.commitSelection();
      }
    });
  }

  private void reset(final IIntimaciesSelectionView selectionView) {
    selectionView.setName(null);
    model.setCurrentName(null);
  }
}