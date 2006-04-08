package net.sf.anathema.character.intimacies.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.intimacies.view.IIntimaciesSelectionView;
import net.sf.anathema.character.intimacies.view.IOverviewView;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.trait.presenter.AbstractTraitPresenter;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.legality.LegalityFontProvider;
import net.sf.anathema.lib.control.legality.ValueLegalityState;
import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledValueView;

public class IntimaciesPresenter extends AbstractTraitPresenter {

  private final IIntimaciesModel model;
  private final IIntimaciesView view;
  private final IResources resources;
  private final Map<IIntimacy, IRemovableTraitView> viewsByIntimacy = new HashMap<IIntimacy, IRemovableTraitView>();
  private final IAdditionalModel additionalModel;

  public IntimaciesPresenter(
      IIntimaciesModel model,
      IAdditionalModel additionalModel,
      IIntimaciesView view,
      IResources resources) {
    this.model = model;
    this.additionalModel = additionalModel;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    String labelText = resources.getString("Intimacies.SelectionLabel"); //$NON-NLS-1$
    BasicUi basicUi = new BasicUi(resources);
    IIntimaciesSelectionView selectionView = view.addSelectionView(labelText, basicUi.getMediumAddIcon());
    initSelectionViewListening(selectionView);
    initOverviewView();
    initModelListening(basicUi, selectionView);
    for (IIntimacy intimacy : model.getEntries()) {
      addIntimacyView(basicUi, intimacy);
    }
    reset(selectionView);
  }

  private void initOverviewView() {
    IOverviewView overviewView = view.addOverview(resources.getString("Intimacies.Overview.BorderLabel")); //$NON-NLS-1$
    final ILabelledAlotmentView freeIntimaciesView = overviewView.addAlotmentView(
        resources.getString("Intimacies.Overview.Free"), 2); //$NON-NLS-1$
    final ILabelledAlotmentView totalIntimaciesView = overviewView.addAlotmentView(
        resources.getString("Intimacies.Overview.Maximum"), 2); //$NON-NLS-1$    
    final ILabelledValueView<Integer> bonusPointsView = overviewView.addValueView(
        resources.getString("Intimacies.Overview.BonusPoints"), 2); //$NON-NLS-1$
    model.addModelChangeListener(new IChangeListener() {
      public void changeOccured() {
        recalculateOverview(freeIntimaciesView, totalIntimaciesView, bonusPointsView);
      }
    });
    model.addModelChangeListener(new IRemovableEntryListener<IIntimacy>() {
      public void entryAdded(IIntimacy entry) {
        recalculateOverview(freeIntimaciesView, totalIntimaciesView, bonusPointsView);
      }

      public void entryComplete(boolean complete) {
        // Nothing to do
      }

      public void entryRemoved(IIntimacy entry) {
        recalculateOverview(freeIntimaciesView, totalIntimaciesView, bonusPointsView);
      }
    });
    recalculateOverview(freeIntimaciesView, totalIntimaciesView, bonusPointsView);
  }

  private void recalculateOverview(
      final ILabelledAlotmentView freeIntimaciesView,
      final ILabelledAlotmentView totalIntimaciesView,
      final ILabelledValueView<Integer> bonusPointsView) {
    adjustOverview(freeIntimaciesView, model.getEntries().size(), model.getFreeIntimacies());
    adjustOverview(totalIntimaciesView, model.getEntries().size(), model.getIntimaciesLimit());
    adjustOverview(bonusPointsView);
  }

  private void adjustOverview(final ILabelledValueView<Integer> valueView) {
    IAdditionalModelBonusPointCalculator bonusPointCalculator = additionalModel.getBonusPointCalculator();
    bonusPointCalculator.recalculate();
    valueView.setValue(bonusPointCalculator.getBonusPointCost());
  }

  private void adjustOverview(final ILabelledAlotmentView alotmentView, int currentValue, int maxValue) {
    alotmentView.setValue(Math.min(currentValue, maxValue));
    alotmentView.setAlotment(maxValue);
    ValueLegalityState fontStyleState = currentValue > maxValue
        ? ValueLegalityState.Increased
        : ValueLegalityState.Okay;
    alotmentView.setFontStyle(new LegalityFontProvider().getFontStyle(fontStyleState));
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
    intimacyView.setMaximum(model.getCompletionValue());
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
    intimacyView.getInnerView().setButtonState(intimacy.isComplete(), true);
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