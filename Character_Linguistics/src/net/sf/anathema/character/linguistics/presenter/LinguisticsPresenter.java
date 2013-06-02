package net.sf.anathema.character.linguistics.presenter;

import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.character.library.util.ProxyComboBoxEditor;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IButtonControlledObjectSelectionView;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.control.legality.LegalityColorProvider;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.workflow.labelledvalue.ILabelledAlotmentView;
import net.sf.anathema.lib.workflow.labelledvalue.IValueView;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

public class LinguisticsPresenter implements Presenter {

  private final ILinguisticsModel model;
  private final ILinguisticsView view;
  private final Resources resources;
  private final Map<Identified, IRemovableEntryView> viewsByEntry = new HashMap<>();
  private final Map<String, Identified> languagesByDisplayName = new HashMap<>();

  public LinguisticsPresenter(ILinguisticsModel model, ILinguisticsView view, Resources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
  }

  @Override
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
    model.addModelChangeListener(new IRemovableEntryListener<Identified>() {
      @Override
      public void entryAdded(Identified entry) {
        updateOverview(familyView, totalView, barbarianView);
      }

      @Override
      public void entryAllowed(boolean complete) {
        //nothing to do;
      }

      @Override
      public void entryRemoved(Identified entry) {
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
    }
    else {
      totalView.setTextColor(LegalityColorProvider.COLOR_OKAY);
    }
  }

  @SuppressWarnings("serial")
private void initEntryPresentation() {
    String labelText = resources.getString("Linguistics.SelectionView.Label");
    final BasicUi basicUi = new BasicUi();
    Icon addIcon = basicUi.getAddIcon();
    ProxyComboBoxEditor editor = new ProxyComboBoxEditor() {
      @Override
      public void setItem(Object anObject) {
        super.setItem(getDisplayString(anObject));
      }
    };
    ListCellRenderer renderer = new DefaultListCellRenderer() {
      @Override
      public Component getListCellRendererComponent(
          JList list,
          Object value,
          int index,
          boolean isSelected,
          boolean cellHasFocus) {
        return super.getListCellRendererComponent(list, getDisplayString(value), index, isSelected, cellHasFocus);
      }
    };
    final IButtonControlledObjectSelectionView<Object> selectionView = view.addSelectionView(
        labelText,
        editor,
        renderer,
        addIcon);
    selectionView.setObjects(model.getPredefinedLanguages());
    selectionView.addObjectSelectionChangedListener(new ObjectValueListener<Object>() {
      @Override
      public void valueChanged(Object newValue) {
        if (newValue == null) {
          return;
        }
        Identified definedLanguage = getLanguage(newValue);
        if (definedLanguage == null) {
          model.selectBarbarianLanguage(newValue.toString());
        }
        else {
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
    model.addModelChangeListener(new IRemovableEntryListener<Identified>() {
      @Override
      public void entryAdded(Identified entry) {
        addEntry(basicUi, entry);
        selectionView.setSelectedObject(null);
      }

      @Override
      public void entryAllowed(boolean complete) {
        selectionView.setButtonEnabled(complete);
      }

      @Override
      public void entryRemoved(Identified entry) {
        IRemovableEntryView entryView = viewsByEntry.remove(entry);
        view.removeEntryView(entryView);
      }
    });
    for (Identified language : model.getPredefinedLanguages()) {
      languagesByDisplayName.put(getDisplayString(language), language);
    }
    for (Identified language : model.getEntries()) {
      addEntry(basicUi, language);
    }
  }

  private void addEntry(BasicUi basicUi, final Identified language) {
    RelativePath removeIcon = basicUi.getRemoveIconPath();
    IRemovableEntryView entryView = view.addEntryView(removeIcon, null, getDisplayString(language));
    viewsByEntry.put(language, entryView);
    entryView.addButtonListener(new Command() {
      @Override
      public void execute() {
        model.removeEntry(language);
      }
    });
  }

  private Identified getLanguage(Object anObject) {
    if (anObject instanceof Identified) {
      return (Identified) anObject;
    }
    String displayName = anObject.toString();
    Identified language = languagesByDisplayName.get(displayName);
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
      return resources.getString("Language." + ((Identified) object).getId());
    }
    return object.toString();
  }
}
