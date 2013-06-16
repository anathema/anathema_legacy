package net.sf.anathema.character.linguistics.presenter;

import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

import java.util.HashMap;
import java.util.Map;

public class LinguisticsPresenter {

  private final ILinguisticsModel model;
  private final ILinguisticsView view;
  private final Resources resources;
  private final Map<Identifier, IRemovableEntryView> viewsByEntry = new HashMap<>();
  private final Map<String, Identifier> languagesByDisplayName = new HashMap<>();

  public LinguisticsPresenter(ILinguisticsModel model, ILinguisticsView view, Resources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    initEntryPresentation();
    initPointPresentation();
  }

  private void initPointPresentation() {
    IOverviewCategory overview = view.addOverview(resources.getString("Linguistics.Overview.Border"));
    final IValueView<Integer> familyView = overview.addIntegerValueView(
            resources.getString("Linguistics.Overview.Families"), 1);
    final IValueView<Integer> barbarianView = overview.addIntegerValueView(
            resources.getString("Linguistics.Overview.Barbarian"), 2);
    final ILabelledAlotmentView totalView = overview.addAlotmentView(
            resources.getString("Linguistics.Overview.Total"), 2);
    model.addModelChangeListener(new IRemovableEntryListener<Identifier>() {
      @Override
      public void entryAdded(Identifier entry) {
        updateOverview(familyView, totalView, barbarianView);
      }

      @Override
      public void entryAllowed(boolean complete) {
        //nothing to do;
      }

      @Override
      public void entryRemoved(Identifier entry) {
        updateOverview(familyView, totalView, barbarianView);
      }
    });
    model.addCharacterChangedListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        updateOverview(familyView, totalView, barbarianView);
      }
    });
    updateOverview(familyView, totalView, barbarianView);
  }

  private void updateOverview(
          IValueView<Integer> familyView,
          ILabelledAlotmentView totalView,
          IValueView<Integer> barbarianView) {
    familyView.setValue(model.getPredefinedLanguageCount());
    barbarianView.setValue(model.getBarbarianLanguageCount());
    int pointsSpent = model.getLanguagePointsSpent();
    totalView.setValue(pointsSpent);
    int pointsAllowed = model.getLanguagePointsAllowed();
    totalView.setAlotment(pointsAllowed);
    if (pointsSpent > pointsAllowed) {
      totalView.setTextColor(LegalityColorProvider.COLOR_HIGH);
    } else {
      totalView.setTextColor(LegalityColorProvider.COLOR_OKAY);
    }
  }

  @SuppressWarnings("serial")
  private void initEntryPresentation() {
    String labelText = resources.getString("Linguistics.SelectionView.Label");
    RelativePath addIcon = new BasicUi().getAddIconPath();
    AgnosticUIConfiguration uiConfiguration = new LanguageUiConfiguration();
    final IButtonControlledObjectSelectionView<Object> selectionView = view.addSelectionView(
            labelText,
            uiConfiguration,
            addIcon);
    selectionView.setObjects(model.getPredefinedLanguages());
    selectionView.addObjectSelectionChangedListener(new ObjectValueListener<Object>() {
      @Override
      public void valueChanged(Object newValue) {
        if (newValue == null) {
          return;
        }
        Identifier definedLanguage = getLanguage(newValue);
        if (definedLanguage == null) {
          model.selectBarbarianLanguage(newValue.toString());
        } else {
          model.selectLanguage(definedLanguage);
        }
      }
    });
    selectionView.addButtonListener(new ObjectValueListener<Object>() {
      @Override
      public void valueChanged(Object newValue) {
        if (!model.isEntryAllowed()) {
          return;
        }
        model.commitSelection();
      }
    });
    model.addModelChangeListener(new IRemovableEntryListener<Identifier>() {
      @Override
      public void entryAdded(Identifier entry) {
        addEntry(entry);
        selectionView.setSelectedObject(null);
      }

      @Override
      public void entryAllowed(boolean complete) {
        selectionView.setButtonEnabled(complete);
      }

      @Override
      public void entryRemoved(Identifier entry) {
        IRemovableEntryView entryView = viewsByEntry.remove(entry);
        view.removeEntryView(entryView);
      }
    });
    for (Identifier language : model.getPredefinedLanguages()) {
      languagesByDisplayName.put(getDisplayString(language), language);
    }
    for (Identifier language : model.getEntries()) {
      addEntry(language);
    }
  }

  private void addEntry(final Identifier language) {
    RelativePath removeIcon = new BasicUi().getRemoveIconPath();
    IRemovableEntryView entryView = view.addEntryView(removeIcon, null, getDisplayString(language));
    viewsByEntry.put(language, entryView);
    entryView.addButtonListener(new Command() {
      @Override
      public void execute() {
        model.removeEntry(language);
      }
    });
  }

  private Identifier getLanguage(Object anObject) {
    if (anObject instanceof Identifier) {
      return (Identifier) anObject;
    }
    String displayName = anObject.toString();
    Identifier language = languagesByDisplayName.get(displayName);
    if (language != null) {
      return language;
    }
    return model.getPredefinedLanguageById(displayName);
  }

  private String getDisplayString(Object object) {
    if (object == null) {
      return null;
    }
    if (model.isPredefinedLanguage(object)) {
      return resources.getString("Language." + ((Identifier) object).getId());
    }
    return object.toString();
  }

  private class LanguageUiConfiguration extends AbstractUIConfiguration {
    @Override
    public String getLabel(Object value) {
      return getDisplayString(value);
    }
  }
}
