package net.sf.anathema.character.intimacies.presenter;

import net.sf.anathema.character.generic.additionaltemplate.HeroModelBonusPointCalculator;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.intimacies.IntimaciesProperties;
import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.selection.IStringSelectionView;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.character.main.model.experience.ExperienceChange;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.control.legality.LegalityFontProvider;
import net.sf.anathema.lib.control.legality.ValueLegalityState;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

import java.util.HashMap;
import java.util.Map;

public class IntimaciesPresenter {

  private final IIntimaciesView view;
  private final Resources resources;
  private final IAdditionalModel additionalModel;
  private final IIntimaciesModel model;

  private final Map<IIntimacy, ExtensibleTraitView> viewsByEntry = new HashMap<>();

  public IntimaciesPresenter(IIntimaciesModel model, IAdditionalModel additionalModel, IIntimaciesView view,
                             Resources resources) {
    this.model = model;
    this.additionalModel = additionalModel;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    String labelText = resources.getString("Intimacies.SelectionLabel");
    IStringSelectionView selectionView = view.addSelectionView(labelText, new BasicUi().getAddIconPath());
    initSelectionViewListening(selectionView);
    initOverviewView();
    initModelListening(selectionView);
    for (IIntimacy intimacy : model.getEntries()) {
      addSubView(intimacy);
    }
    reset(selectionView);
  }

  private void initOverviewView() {
    final IOverviewCategory creationOverview = view.addOverview(
            resources.getString("Intimacies.Overview.BorderLabel"));
    final ILabelledAlotmentView freeIntimaciesView = creationOverview.addAlotmentView(
            resources.getString("Intimacies.Overview.Free"), 2);
    final ILabelledAlotmentView totalIntimaciesView = creationOverview.addAlotmentView(
            resources.getString("Intimacies.Overview.Maximum"), 2);
    final IValueView<Integer> bonusPointsView = creationOverview.addIntegerValueView(
            resources.getString("Intimacies.Overview.BonusPoints"), 2);
    final IOverviewCategory experienceOverview = view.addOverview(
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
    model.addChangeListener(new FlavoredChangeListener() {

      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
          setOverview(model.isCharacterExperienced(), experienceOverview, creationOverview);
        }
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
    HeroModelBonusPointCalculator bonusPointCalculator = additionalModel.getBonusPointCalculator();
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

  protected final void addSubView(IIntimacy v) {
    ExtensibleTraitView subView = createSubView(v);
    viewsByEntry.put(v, subView);
  }

  protected ExtensibleTraitView createSubView(final IIntimacy intimacy) {
    int maximalValue = model.getCompletionValue();
    int currentValue = intimacy.getTrait().getCurrentValue();
    String name = intimacy.getName();
    ExtensibleTraitView extensibleTraitView = view.addIntimacy(name, currentValue, maximalValue);
    new TraitPresenter(intimacy.getTrait(), extensibleTraitView.getIntValueView()).initPresentation();
    addLinkToggle(extensibleTraitView, intimacy);
    addDeleteTool(extensibleTraitView, intimacy);
    return extensibleTraitView;
  }

  private void addDeleteTool(ExtensibleTraitView extensibleTraitView, final IIntimacy intimacy) {
    Tool tool = extensibleTraitView.addToolBehind();
    tool.setIcon(new BasicUi().getRemoveIconPath());
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        model.removeEntry(intimacy);
      }
    });
  }

  private void addLinkToggle(ExtensibleTraitView extensibleTraitView, final IIntimacy intimacy) {
    IIconToggleButtonProperties properties = new IntimaciesProperties(resources);
    final ToggleTool toggleTool = extensibleTraitView.addToggleBehind(properties);
    toggleTool.setCommand(new Command() {
      @Override
      public void execute() {
        intimacy.setComplete(!intimacy.isComplete());
      }
    });
    intimacy.addCompletionListener(new IBooleanValueChangedListener() {
      @Override
      public void valueChanged(boolean isComplete) {
        setCompletionState(isComplete, toggleTool);
      }
    });
    setCompletionState(intimacy.isComplete(), toggleTool);
  }


  private void setCompletionState(boolean isComplete, ToggleTool toggleTool) {
    if (isComplete) {
      toggleTool.select();
    } else {
      toggleTool.deselect();
    }
  }

  protected void initModelListening(final IStringSelectionView selectionView) {
    model.addModelChangeListener(new IRemovableEntryListener<IIntimacy>() {
      @Override
      public void entryAdded(IIntimacy v) {
        addSubView(v);
        reset(selectionView);
      }

      @Override
      public void entryRemoved(IIntimacy v) {
        ExtensibleTraitView traitView = viewsByEntry.get(v);
        traitView.remove();
      }

      @Override
      public void entryAllowed(boolean complete) {
        selectionView.setAddButtonEnabled(complete);
      }
    });
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