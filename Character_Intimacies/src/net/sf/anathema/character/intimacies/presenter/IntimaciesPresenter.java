package net.sf.anathema.character.intimacies.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModelBonusPointCalculator;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.intvalue.IToggleButtonTraitView;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.selection.AbstractStringEntryTraitPresenter;
import net.sf.anathema.character.library.selection.IStringSelectionView;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.control.legality.LegalityFontProvider;
import net.sf.anathema.lib.control.legality.ValueLegalityState;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

public class IntimaciesPresenter extends AbstractStringEntryTraitPresenter<IIntimacy> implements IPresenter {

  private final IIntimaciesView view;
  private final IResources resources;
  private final IAdditionalModel additionalModel;
  private final IIntimaciesModel model;

  public IntimaciesPresenter(
      IIntimaciesModel model,
      IAdditionalModel additionalModel,
      IIntimaciesView view,
      IResources resources) {
    super(model, view);
    this.model = model;
    this.additionalModel = additionalModel;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    String labelText = resources.getString("Intimacies.SelectionLabel"); //$NON-NLS-1$
    BasicUi basicUi = new BasicUi(resources);
    IStringSelectionView selectionView = view.addSelectionView(labelText, basicUi.getAddIcon());
    initSelectionViewListening(selectionView);
    initOverviewView();
    initModelListening(basicUi, selectionView);
    for (IIntimacy intimacy : model.getEntries()) {
      addSubView(basicUi, intimacy);
    }
    reset(selectionView);
  }

  private void initOverviewView() {
    final IOverviewCategory creationOverview = view.createOverview(resources.getString("Intimacies.Overview.BorderLabel")); //$NON-NLS-1$    
    final ILabelledAlotmentView freeIntimaciesView = creationOverview.addAlotmentView(
        resources.getString("Intimacies.Overview.Free"), 2); //$NON-NLS-1$
    final ILabelledAlotmentView totalIntimaciesView = creationOverview.addAlotmentView(
        resources.getString("Intimacies.Overview.Maximum"), 2); //$NON-NLS-1$    
    final IValueView<Integer> bonusPointsView = creationOverview.addIntegerValueView(
        resources.getString("Intimacies.Overview.BonusPoints"), 2); //$NON-NLS-1$
    final IOverviewCategory experienceOverview = view.createOverview(resources.getString("Intimacies.Overview.BorderLabel")); //$NON-NLS-1$    
    final ILabelledAlotmentView experienceMaximumView = experienceOverview.addAlotmentView(
        resources.getString("Intimacies.Overview.Maximum"), 2); //$NON-NLS-1$
    model.addModelChangeListener(new IChangeListener() {
      public void changeOccurred() {
        recalculateOverview(freeIntimaciesView, totalIntimaciesView, bonusPointsView, experienceMaximumView);
      }
    });
    model.addModelChangeListener(new IRemovableEntryListener<IIntimacy>() {
      public void entryAdded(IIntimacy entry) {
        recalculateOverview(freeIntimaciesView, totalIntimaciesView, bonusPointsView, experienceMaximumView);
      }

      public void entryAllowed(boolean complete) {
        // Nothing to do
      }

      public void entryRemoved(IIntimacy entry) {
        recalculateOverview(freeIntimaciesView, totalIntimaciesView, bonusPointsView, experienceMaximumView);
      }
    });
    model.addCharacterChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        if (experienced) {
          view.setOverview(experienceOverview);
        }
        else {
          view.setOverview(creationOverview);
        }
      }
    });
    view.setOverview(creationOverview);
    recalculateOverview(freeIntimaciesView, totalIntimaciesView, bonusPointsView, experienceMaximumView);
  }

  private void recalculateOverview(
      final ILabelledAlotmentView freeIntimaciesView,
      final ILabelledAlotmentView totalIntimaciesView,
      final IValueView<Integer> bonusPointsView,
      ILabelledAlotmentView experienceMaximumView) {
    adjustBonusPointsOverview(freeIntimaciesView, model.getEntries().size(), model.getFreeIntimacies());
    adjustTotalOverview(totalIntimaciesView, model.getEntries().size(), model.getIntimaciesLimit());
    adjustTotalOverview(experienceMaximumView, model.getEntries().size(), model.getIntimaciesLimit());
    adjustOverview(bonusPointsView);
  }

  private void adjustOverview(final IValueView<Integer> valueView) {
    IAdditionalModelBonusPointCalculator bonusPointCalculator = additionalModel.getBonusPointCalculator();
    bonusPointCalculator.recalculate();
    valueView.setValue(bonusPointCalculator.getBonusPointCost());
  }

  private void adjustTotalOverview(final ILabelledAlotmentView alotmentView, int currentValue, int maxValue) {
    alotmentView.setValue(currentValue);
    alotmentView.setAlotment(maxValue);
    ValueLegalityState state = currentValue > maxValue ? ValueLegalityState.High : ValueLegalityState.Okay;
    alotmentView.setTextColor(new LegalityColorProvider().getTextColor(state));
  }

  private void adjustBonusPointsOverview(final ILabelledAlotmentView alotmentView, int currentValue, int maxValue) {
    alotmentView.setValue(Math.min(currentValue, maxValue));
    alotmentView.setAlotment(maxValue);
    ValueLegalityState state = currentValue > maxValue ? ValueLegalityState.Increased : ValueLegalityState.Okay;
    alotmentView.setFontStyle(new LegalityFontProvider().getFontStyle(state));
  }

  @Override
  protected IRemovableTraitView< ? > createSubView(final BasicUi basicUi, final IIntimacy intimacy) {
    final IRemovableTraitView<IToggleButtonTraitView< ? >> intimacyView = view.addEntryView(
        basicUi.getRemoveIcon(),
        null,
        intimacy.getName());
    intimacyView.setMaximum(model.getCompletionValue());
    intimacyView.setValue(intimacy.getTrait().getCurrentValue());
    new TraitPresenter(intimacy.getTrait(), intimacyView).initPresentation();
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
    return intimacyView;
  }
}