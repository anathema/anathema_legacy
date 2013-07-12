package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.character.main.CharacterUI;
import net.sf.anathema.character.main.library.overview.OverviewCategory;
import net.sf.anathema.character.main.library.removableentry.RemovableEntryListener;
import net.sf.anathema.character.main.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.main.presenter.ExtensibleTraitView;
import net.sf.anathema.character.main.view.labelledvalue.LabelledAllotmentView;
import net.sf.anathema.character.main.view.labelledvalue.IValueView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.experience.ExperienceChange;
import net.sf.anathema.hero.intimacies.model.IntimaciesModel;
import net.sf.anathema.hero.intimacies.model.Intimacy;
import net.sf.anathema.hero.intimacies.points.IntimaciesBonusPointCalculator;
import net.sf.anathema.hero.points.HeroBonusPointCalculator;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.control.legality.LegalityFontProvider;
import net.sf.anathema.lib.control.legality.ValueLegalityState;
import net.sf.anathema.lib.resources.Resources;

import java.util.HashMap;
import java.util.Map;

import static net.sf.anathema.lib.gui.AgnosticUIConfiguration.NO_ICON;

public class IntimaciesPresenter {

  private final IntimaciesView view;
  private final Resources resources;
  private final IntimaciesModel model;

  private final Map<Intimacy, ExtensibleTraitView> viewsByEntry = new HashMap<>();

  public IntimaciesPresenter(IntimaciesModel model, IntimaciesView view, Resources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    String labelText = resources.getString("Intimacies.SelectionLabel");
    StringEntryView selectionView = view.addSelectionView(labelText);
    Tool tool = initSelectionViewListening(selectionView);
    initOverviewView();
    initModelListening(selectionView, tool);
    for (Intimacy intimacy : model.getEntries()) {
      addSubView(intimacy);
    }
    reset(selectionView);
  }

  private void initOverviewView() {
    final OverviewCategory creationOverview = view.addOverview(
            resources.getString("Intimacies.Overview.BorderLabel"));
    final LabelledAllotmentView freeIntimaciesView = creationOverview.addAlotmentView(
            resources.getString("Intimacies.Overview.Free"), 2);
    final LabelledAllotmentView totalIntimaciesView = creationOverview.addAlotmentView(
            resources.getString("Intimacies.Overview.Maximum"), 2);
    final IValueView<Integer> bonusPointsView = creationOverview.addIntegerValueView(
            resources.getString("Intimacies.Overview.BonusPoints"), 2);
    final OverviewCategory experienceOverview = view.addOverview(
            resources.getString("Intimacies.Overview.BorderLabel"));
    final LabelledAllotmentView experienceMaximumView = experienceOverview.addAlotmentView(
            resources.getString("Intimacies.Overview.Maximum"), 2);
    model.addModelChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        recalculateOverview(freeIntimaciesView, totalIntimaciesView, bonusPointsView, experienceMaximumView);
      }
    });
    model.addModelChangeListener(new RemovableEntryListener<Intimacy>() {
      @Override
      public void entryAdded(Intimacy entry) {
        recalculateOverview(freeIntimaciesView, totalIntimaciesView, bonusPointsView, experienceMaximumView);
      }

      @Override
      public void entryAllowed(boolean complete) {
        // Nothing to do
      }

      @Override
      public void entryRemoved(Intimacy entry) {
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

  private void setOverview(boolean experienced, OverviewCategory experienceOverview,
                           OverviewCategory creationOverview) {
    if (experienced) {
      view.setOverview(experienceOverview);
    } else {
      view.setOverview(creationOverview);
    }
  }

  private void recalculateOverview(LabelledAllotmentView freeIntimaciesView, LabelledAllotmentView totalIntimaciesView,
                                   IValueView<Integer> bonusPointsView, LabelledAllotmentView experienceMaximumView) {
    adjustBonusPointsOverview(freeIntimaciesView, model.getEntries().size(), model.getFreeIntimacies());
    adjustTotalOverview(totalIntimaciesView, model.getEntries().size(), model.getIntimaciesLimit());
    adjustTotalOverview(experienceMaximumView, model.getEntries().size(), model.getIntimaciesLimit());
    adjustOverview(bonusPointsView);
  }

  private void adjustOverview(IValueView<Integer> valueView) {
    HeroBonusPointCalculator bonusPointCalculator = new IntimaciesBonusPointCalculator(model);
    bonusPointCalculator.recalculate();
    valueView.setValue(bonusPointCalculator.getBonusPointCost());
  }

  private void adjustTotalOverview(LabelledAllotmentView alotmentView, int currentValue, int maxValue) {
    alotmentView.setValue(currentValue);
    alotmentView.setAllotment(maxValue);
    ValueLegalityState state = currentValue > maxValue ? ValueLegalityState.High : ValueLegalityState.Okay;
    alotmentView.setTextColor(new LegalityColorProvider().getTextColor(state));
  }

  private void adjustBonusPointsOverview(LabelledAllotmentView alotmentView, int currentValue, int maxValue) {
    alotmentView.setValue(Math.min(currentValue, maxValue));
    alotmentView.setAllotment(maxValue);
    ValueLegalityState state = currentValue > maxValue ? ValueLegalityState.Increased : ValueLegalityState.Okay;
    alotmentView.setFontStyle(new LegalityFontProvider().getFontStyle(state));
  }

  protected final void addSubView(Intimacy v) {
    ExtensibleTraitView subView = createSubView(v);
    viewsByEntry.put(v, subView);
  }

  protected ExtensibleTraitView createSubView(final Intimacy intimacy) {
    int maximalValue = model.getCompletionValue();
    int currentValue = intimacy.getTrait().getCurrentValue();
    String name = intimacy.getName();
    ExtensibleTraitView intimacyView = view.addIntimacy(name, currentValue, maximalValue);
    new TraitPresenter(intimacy.getTrait(), intimacyView.getIntValueView()).initPresentation();
    addLinkToggle(intimacyView, intimacy);
    addDeleteTool(intimacyView, intimacy);
    return intimacyView;
  }

  private void addDeleteTool(ExtensibleTraitView extensibleTraitView, final Intimacy intimacy) {
    Tool tool = extensibleTraitView.addToolBehind();
    tool.setIcon(new BasicUi().getRemoveIconPath());
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        model.removeEntry(intimacy);
      }
    });
  }

  private void addLinkToggle(ExtensibleTraitView extensibleTraitView, final Intimacy intimacy) {
    final ToggleTool toggleTool = extensibleTraitView.addToggleBehind();
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
      toggleTool.setIcon(new CharacterUI().getLinkIconPath());
    } else {
      toggleTool.deselect();
      toggleTool.setIcon(NO_ICON);
    }
  }

  protected void initModelListening(final StringEntryView selectionView, final Tool tool) {
    model.addModelChangeListener(new RemovableEntryListener<Intimacy>() {
      @Override
      public void entryAdded(Intimacy v) {
        addSubView(v);
        reset(selectionView);
      }

      @Override
      public void entryRemoved(Intimacy v) {
        ExtensibleTraitView traitView = viewsByEntry.get(v);
        traitView.remove();
      }

      @Override
      public void entryAllowed(boolean complete) {
        if(complete){
          tool.enable();
        }
        else {
          tool.disable();
        }
      }
    });
  }

  protected final Tool initSelectionViewListening(StringEntryView selectionView) {
    selectionView.addTextChangeListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        model.setCurrentName(newValue);
      }
    });
    Tool tool = selectionView.addTool();
    tool.setIcon(new BasicUi().getAddIconPath());
    tool.setCommand(new Command() {
      @Override
      public void execute() {
        model.commitSelection();
      }
    });
    return tool;
  }

  protected final void reset(StringEntryView selectionView) {
    selectionView.clear();
    model.setCurrentName(null);
  }
}