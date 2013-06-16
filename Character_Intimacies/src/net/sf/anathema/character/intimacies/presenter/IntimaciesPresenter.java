package net.sf.anathema.character.intimacies.presenter;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.selection.IStringSelectionView;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.control.legality.LegalityFontProvider;
import net.sf.anathema.lib.control.legality.ValueLegalityState;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

import java.util.HashMap;
import java.util.Map;

public class IntimaciesPresenter implements Presenter {

  private final IIntimaciesView view;
  private final Resources resources;
  private final IAdditionalModel additionalModel;
  private final IIntimaciesModel model;

  private final Map<IIntimacy, IRemovableTraitView<?>> viewsByEntry = new HashMap<>();

  public IntimaciesPresenter(IIntimaciesModel model, IAdditionalModel additionalModel, IIntimaciesView view,
                             Resources resources) {
    this.model = model;
    this.additionalModel = additionalModel;
    this.view = view;
    this.resources = resources;
  }

  @Override
  public void initPresentation() {
    String labelText = resources.getString("Intimacies.SelectionLabel");
    BasicUi basicUi = new BasicUi();
    IStringSelectionView selectionView = view.addSelectionView(labelText, basicUi.getAddIconPath());
    initSelectionViewListening(selectionView);
    initOverviewView();
    initModelListening(basicUi, selectionView);
    for (IIntimacy intimacy : model.getEntries()) {
      addSubView(basicUi, intimacy);
    }
    reset(selectionView);
  }

  private void initOverviewView() {
    final IOverviewCategory creationOverview = view.createOverview(
            resources.getString("Intimacies.Overview.BorderLabel"));
    final ILabelledAlotmentView freeIntimaciesView = creationOverview.addAlotmentView(
            resources.getString("Intimacies.Overview.Free"), 2);
    final ILabelledAlotmentView totalIntimaciesView = creationOverview.addAlotmentView(
            resources.getString("Intimacies.Overview.Maximum"), 2);
    final IValueView<Integer> bonusPointsView = creationOverview.addIntegerValueView(
            resources.getString("Intimacies.Overview.BonusPoints"), 2);
    final IOverviewCategory experienceOverview = view.createOverview(
            resources.getString("Intimacies.Overview.BorderLabel"));
    final ILabelledAlotmentView experienceMaximumView = experienceOverview.addAlotmentView(
            resources.getString("Intimacies.Overview.Maximum"), 2);
    model.addModelChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        recalculateOverview(freeIntimaciesView, totalIntimaciesView, bonusPointsView, experienceMaximumView);
      }
    });
    model.addModelChangeListener(new IRemovableEntryListener<IIntimacy>() {
      @Override
      public void entryAdded(IIntimacy entry) {
        recalculateOverview(freeIntimaciesView, totalIntimaciesView, bonusPointsView, experienceMaximumView);
      }

      @Override
      public void entryAllowed(boolean complete) {
        // Nothing to do
      }

      @Override
      public void entryRemoved(IIntimacy entry) {
        recalculateOverview(freeIntimaciesView, totalIntimaciesView, bonusPointsView, experienceMaximumView);
      }
    });
    model.addCharacterChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        setOverview(experienced, experienceOverview, creationOverview);
      }
    });
    setOverview(model.isCharacterExperienced(), experienceOverview, creationOverview);
    recalculateOverview(freeIntimaciesView, totalIntimaciesView, bonusPointsView, experienceMaximumView);
  }

  private void setOverview(boolean experienced, IOverviewCategory experienceOverview,
                           IOverviewCategory creationOverview) {
    if (experienced) {
      view.setOverview(experienceOverview);
    } else {
      view.setOverview(creationOverview);
    }
  }

  private void recalculateOverview(ILabelledAlotmentView freeIntimaciesView, ILabelledAlotmentView totalIntimaciesView,
                                   IValueView<Integer> bonusPointsView, ILabelledAlotmentView experienceMaximumView) {
    adjustBonusPointsOverview(freeIntimaciesView, model.getEntries().size(), model.getFreeIntimacies());
    adjustTotalOverview(totalIntimaciesView, model.getEntries().size(), model.getIntimaciesLimit());
    adjustTotalOverview(experienceMaximumView, model.getEntries().size(), model.getIntimaciesLimit());
    adjustOverview(bonusPointsView);
  }

  private void adjustOverview(IValueView<Integer> valueView) {
    IAdditionalModelBonusPointCalculator bonusPointCalculator = additionalModel.getBonusPointCalculator();
    bonusPointCalculator.recalculate();
    valueView.setValue(bonusPointCalculator.getBonusPointCost());
  }

  private void adjustTotalOverview(ILabelledAlotmentView alotmentView, int currentValue, int maxValue) {
    alotmentView.setValue(currentValue);
    alotmentView.setAlotment(maxValue);
    ValueLegalityState state = currentValue > maxValue ? ValueLegalityState.High : ValueLegalityState.Okay;
    alotmentView.setTextColor(new LegalityColorProvider().getTextColor(state));
  }

  private void adjustBonusPointsOverview(ILabelledAlotmentView alotmentView, int currentValue, int maxValue) {
    alotmentView.setValue(Math.min(currentValue, maxValue));
    alotmentView.setAlotment(maxValue);
    ValueLegalityState state = currentValue > maxValue ? ValueLegalityState.Increased : ValueLegalityState.Okay;
    alotmentView.setFontStyle(new LegalityFontProvider().getFontStyle(state));
  }

  protected IRemovableTraitView<?> createSubView(BasicUi basicUi, final IIntimacy intimacy) {
    final IRemovableTraitView<IToggleButtonTraitView<?>> intimacyView = view.addEntryView(basicUi.getRemoveIconPath(), null,
            intimacy.getName());
    intimacyView.setMaximum(model.getCompletionValue());
    intimacyView.setValue(intimacy.getTrait().getCurrentValue());
    new TraitPresenter(intimacy.getTrait(), intimacyView).initPresentation();
    intimacyView.addButtonListener(new Command() {
      @Override
      public void execute() {
        model.removeEntry(intimacy);
      }
    });
    intimacyView.getInnerView().addButtonSelectedListener(new IBooleanValueChangedListener() {
      @Override
      public void valueChanged(boolean newValue) {
        intimacy.setComplete(newValue);
      }
    });
    intimacy.addCompletionListener(new IBooleanValueChangedListener() {
      @Override
      public void valueChanged(boolean newValue) {
        intimacyView.getInnerView().setButtonState(newValue, true);
      }
    });
    intimacyView.getInnerView().setButtonState(intimacy.isComplete(), true);
    return intimacyView;
  }


  protected void initModelListening(final BasicUi basicUi, final IStringSelectionView selectionView) {
    model.addModelChangeListener(new IRemovableEntryListener<IIntimacy>() {
      @Override
      public void entryAdded(IIntimacy v) {
        addSubView(basicUi, v);
        reset(selectionView);
      }

      @Override
      public void entryRemoved(IIntimacy v) {
        IRemovableEntryView removableView = viewsByEntry.get(v);
        view.removeEntryView(removableView);
      }

      @Override
      public void entryAllowed(boolean complete) {
        selectionView.setAddButtonEnabled(complete);
      }
    });
  }

  protected final void addSubView(BasicUi basicUi, IIntimacy v) {
    IRemovableTraitView<?> subView = createSubView(basicUi, v);
    viewsByEntry.put(v, subView);
  }

  protected final void initSelectionViewListening(IStringSelectionView selectionView) {
    selectionView.addTextChangeListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        model.setCurrentName(newValue);
      }
    });
    selectionView.addAddButtonListener(new Command() {
      @Override
      public void execute() {
        model.commitSelection();
      }
    });
  }

  protected final void reset(IStringSelectionView selectionView) {
    selectionView.clear();
    model.setCurrentName(null);
  }
}