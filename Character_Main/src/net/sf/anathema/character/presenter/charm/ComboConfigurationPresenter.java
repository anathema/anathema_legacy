package net.sf.anathema.character.presenter.charm;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.disy.commons.core.util.StringUtilities;
import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.charm.CharmLearnAdapter;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICharmLearnListener;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.charm.IComboConfigurationListener;
import net.sf.anathema.character.model.charm.IComboModelListener;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;
import net.sf.anathema.character.presenter.TabContent;
import net.sf.anathema.character.view.magic.IComboConfigurationView;
import net.sf.anathema.character.view.magic.IComboView;
import net.sf.anathema.character.view.magic.IComboViewListener;
import net.sf.anathema.character.view.magic.IMagicViewFactory;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class ComboConfigurationPresenter implements IMagicSubPresenter {

  private final ICharmConfiguration charmConfiguration;
  private final IComboConfiguration comboConfiguration;
  private final Map<ICombo, IComboView> viewsByCombo = new HashMap<ICombo, IComboView>();
  private final ICharacterStatistics statistics;
  private final IResources resources;

  public ComboConfigurationPresenter(IResources resources, ICharacterStatistics statistics) {
    this.resources = resources;
    this.statistics = statistics;
    this.charmConfiguration = statistics.getCharms();
    this.comboConfiguration = statistics.getCombos();
  }

  public TabContent init(IMagicViewFactory charmView) {
    final IComboConfigurationView comboView = charmView.createCharmComboView();
    comboView.initGui(new ComboViewProperties(resources, comboConfiguration));
    initCharmLearnListening(comboView);
    ITextView nameView = comboView.addComboNameView(resources.getString("CardView.CharmConfiguration.ComboCreation.NameLabel")); //$NON-NLS-1$);
    ICombo editCombo = comboConfiguration.getEditCombo();
    TextualPresentation.initView(nameView, editCombo.getName());
    ITextView descriptionView = comboView.addComboDescriptionView(resources.getString("CardView.CharmConfiguration.ComboCreation.DescriptionLabel")); //$NON-NLS-1$);
    TextualPresentation.initView(descriptionView, editCombo.getDescription());
    updateCharmListsInView(comboView);
    initViewListening(comboView);
    initComboModelListening(comboView);
    initComboConfigurationListening(comboView);
    statistics.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        updateComboButtons();
      }

      @Override
      public void casteChanged() {
        enableCrossPrerequisiteTypeCombos();
      }
    });
    enableCrossPrerequisiteTypeCombos();
    updateComboButtons();
    String header = resources.getString("CardView.CharmConfiguration.ComboCreation.Title"); //$NON-NLS-1$
    return new TabContent(header, comboView);
  }

  private void enableCrossPrerequisiteTypeCombos() {
    ICasteType caste = statistics.getCharacterConcept().getCaste().getType();
    boolean alienCharms = statistics.getCharacterTemplate().getMagicTemplate().getCharmTemplate().isAllowedAlienCharms(
        caste);
    comboConfiguration.setCrossPrerequisiteTypeComboAllowed(alienCharms);
  }

  private void initComboConfigurationListening(final IComboConfigurationView comboView) {
    comboConfiguration.addComboConfigurationListener(new IComboConfigurationListener() {
      public void comboAdded(ICombo combo) {
        addComboToView(comboView, combo);
      }

      public void comboChanged(ICombo combo) {
        IComboView view = viewsByCombo.get(combo);
        view.updateCombo(createComboNameString(combo), convertToHtml(combo));
      }

      public void comboDeleted(ICombo combo) {
        IComboView view = viewsByCombo.get(combo);
        comboView.deleteView(view);
      }

      public void editBegun(ICombo combo) {
        setViewsToNotEditing();
        setViewToEditing(combo);
        comboView.setEditState(true);
      }

      public void editEnded() {
        setViewsToNotEditing();
        comboView.setEditState(false);
      }

    });
    for (ICombo combo : comboConfiguration.getCurrentCombos()) {
      addComboToView(comboView, combo);
    }
  }

  private String createComboNameString(ICombo combo) {
    String comboName = combo.getName().getText();
    if (StringUtilities.isNullOrEmpty(comboName)) {
      comboName = resources.getString("CardView.CharmConfiguration.ComboCreation.UnnamedCombo"); //$NON-NLS-1$
    }
    return comboName;
  }

  private void initCharmLearnListening(final IComboConfigurationView comboView) {
    ICharmLearnListener charmLearnListener = new CharmLearnAdapter() {
      @Override
      public void charmLearned(ICharm charm) {
        updateCharmListsInView(comboView);
      }

      @Override
      public void charmForgotten(ICharm charm) {
        updateCharmListsInView(comboView);
      }
    };
    for (ILearningCharmGroup group : charmConfiguration.getAllGroups()) {
      group.addCharmLearnListener(charmLearnListener);
    }
  }

  private void addComboToView(final IComboConfigurationView comboConfigurationView, final ICombo combo) {
    SmartAction deleteAction = new SmartAction(
        resources.getString("CardView.CharmConfiguration.ComboCreation.DeleteLabel"), resources.getImageIcon("tools/RedX20.png")) { //$NON-NLS-1$//$NON-NLS-2$
      @Override
      protected void execute(Component parentComponent) {
        comboConfiguration.deleteCombo(combo);
      }
    };
    SmartAction editAction = new SmartAction(
        resources.getString("CardView.CharmConfiguration.ComboCreation.EditLabel"), resources.getImageIcon("Recycle20.png")) {//$NON-NLS-1$//$NON-NLS-2$
      @Override
      protected void execute(Component parentComponent) {
        comboConfiguration.beginComboEdit(combo);
      }
    };
    final IComboView comboView = comboConfigurationView.addComboView(
        createComboNameString(combo),
        convertToHtml(combo),
        deleteAction,
        editAction);
    viewsByCombo.put(combo, comboView);
  }

  private String convertToHtml(ICombo combo) {
    return convertToHtml(combo.getDescription().getText(), combo.getCharms());
  }

  private String convertToHtml(String description, ICharm[] charms) {
    String charmList = "<b>"; //$NON-NLS-1$
    Iterator<ICharm> charmIterator = Arrays.asList(charms).iterator();
    if (charmIterator.hasNext()) {
      charmList = charmList.concat(resources.getString(charmIterator.next().getId()));
    }
    while (charmIterator.hasNext()) {
      charmList = charmList.concat(", " + resources.getString(charmIterator.next().getId())); //$NON-NLS-1$
    }
    charmList += "</b>"; //$NON-NLS-1$
    if (StringUtilities.isNullOrEmpty(description)) {
      return wrapHtml(charmList);
    }
    String converted = description.replace("\n", "<br>"); //$NON-NLS-1$ //$NON-NLS-2$
    return wrapHtml(charmList + " - <i>" + converted + "</i>"); //$NON-NLS-1$//$NON-NLS-2$
  }

  private String wrapHtml(String text) {
    return "<html><body>" + text + "</body></html>"; //$NON-NLS-1$//$NON-NLS-2$
  }

  private void updateCharmListsInView(final IComboConfigurationView comboView) {
    comboView.setComboCharms(comboConfiguration.getEditCombo().getCharms());
    ICharm[] learnedCharms = charmConfiguration.getLearnedCharms(statistics.isExperienced());
    comboView.setAllCharms(learnedCharms);
  }

  private void initComboModelListening(final IComboConfigurationView comboView) {
    comboConfiguration.addComboModelListener(new IComboModelListener() {
      public void comboChanged() {
        updateCharmListsInView(comboView);
      }
    });
  }

  private void initViewListening(final IComboConfigurationView comboView) {
    comboView.addComboViewListener(new IComboViewListener() {
      public void charmAdded(Object addedCharm) {
        comboConfiguration.addCharmToCombo((ICharm) addedCharm);
      }

      public void charmRemoved(Object[] removedCharms) {
        List<ICharm> removedCharmList = new ArrayList<ICharm>();
        for (Object charmObject : removedCharms) {
          removedCharmList.add((ICharm) charmObject);
        }
        comboConfiguration.removeCharmsFromCombo(removedCharmList.toArray(new ICharm[0]));
      }

      public void comboFinalized() {
        comboConfiguration.finalizeCombo();
      }

      public void comboCleared() {
        comboConfiguration.clearCombo();
      }
    });
  }

  private void setViewToEditing(ICombo combo) {
    IComboView view = viewsByCombo.get(combo);
    view.setEditText(resources.getString("CardView.CharmConfiguration.ComboCreation.RestartEditLabel")); //$NON-NLS-1$
    createComboNameString(combo);
    view.updateCombo(createComboNameString(combo) + " (" //$NON-NLS-1$
        + resources.getString("CardView.CharmConfiguration.ComboCreation.EditingLabel") //$NON-NLS-1$
        + ")", convertToHtml(combo)); //$NON-NLS-1$
  }

  private void setViewsToNotEditing() {
    for (ICombo currentCombo : viewsByCombo.keySet()) {
      IComboView view = viewsByCombo.get(currentCombo);
      view.setEditText(resources.getString("CardView.CharmConfiguration.ComboCreation.EditLabel")); //$NON-NLS-1$
      view.updateCombo(createComboNameString(currentCombo), convertToHtml(currentCombo));
    }
  }

  private void updateComboButtons() {
    for (ICombo combo : comboConfiguration.getCurrentCombos()) {
      IComboView view = viewsByCombo.get(combo);
      boolean disabled = comboConfiguration.isLearnedOnCreation(combo) && statistics.isExperienced();
      view.setEditButtonsVisible(!disabled);
    }
  }
}