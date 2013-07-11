package net.sf.anathema.hero.combos.display.presenter;

import com.google.common.base.Strings;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmLearnAdapter;
import net.sf.anathema.character.main.magic.model.charm.ICharmLearnListener;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;
import net.sf.anathema.character.main.magic.model.charmtree.builder.MagicDisplayLabeler;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.combos.model.ComboConfigurationModel;
import net.sf.anathema.hero.concept.ConceptChange;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.compare.I18nedIdentificateComparator;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ComboConfigurationPresenter {

  private final CharmsModel charmConfiguration;
  private final CombosModel comboConfiguration;
  private final Map<Combo, ComboView> viewsByCombo = new HashMap<>();
  private final Map<Combo, Tool> toolsByCombo = new HashMap<>();
  private final ComboConfigurationModel comboModel;
  private Hero hero;
  private final Resources resources;
  private final ComboConfigurationView view;
  private final MagicDisplayLabeler labeler;

  public ComboConfigurationPresenter(Hero hero, Resources resources, ComboConfigurationModel comboModel, ComboConfigurationView view) {
    this.hero = hero;
    this.resources = resources;
    this.comboModel = comboModel;
    this.charmConfiguration = comboModel.getCharmConfiguration();
    this.comboConfiguration = comboModel.getCombos();
    this.labeler = new MagicDisplayLabeler(resources);
    this.view = view;
  }

  public void initPresentation() {
    view.initGui(new CombinedComboViewAndMagicProperties(resources, comboConfiguration, comboModel.getMagicDescriptionProvider()));
    initCharmLearnListening(view);
    ITextView nameView = view.addComboNameView(resources.getString("CardView.CharmConfiguration.ComboCreation.NameLabel"));
    Combo editCombo = comboConfiguration.getEditCombo();
    TextualPresentation textualPresentation = new TextualPresentation();
    textualPresentation.initView(nameView, editCombo.getName());
    ITextView descriptionView = view.addComboDescriptionView(resources.getString("CardView.CharmConfiguration.ComboCreation.DescriptionLabel"));
    textualPresentation.initView(descriptionView, editCombo.getDescription());
    updateCharmListsInView(view);
    initViewListening(view);
    initComboModelListening(view);
    initComboConfigurationListening(view);
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (flavor == ConceptChange.FLAVOR_CASTE) {
          enableCrossPrerequisiteTypeCombos();
        }
      }
    });
    enableCrossPrerequisiteTypeCombos();
  }

  private void enableCrossPrerequisiteTypeCombos() {
    boolean alienCharms = comboModel.isAlienCharmsAllowed();
    comboConfiguration.setCrossPrerequisiteTypeComboAllowed(alienCharms);
  }

  private void initComboConfigurationListening(final ComboConfigurationView comboView) {
    comboConfiguration.addComboConfigurationListener(new ComboConfigurationListener() {
      @Override
      public void comboAdded(Combo combo) {
        addComboToView(comboView, combo);
      }

      @Override
      public void comboChanged(Combo combo) {
        viewsByCombo.get(combo).updateCombo(createComboNameString(combo), convertToHtml(combo));
      }

      @Override
      public void comboDeleted(Combo combo) {
        view.deleteView(viewsByCombo.get(combo));
      }

      @Override
      public void editBegun(Combo combo) {
        setViewsToNotEditing();
        setViewToEditing(combo);
        comboView.setEditState(true);
      }

      @Override
      public void editEnded() {
        setViewsToNotEditing();
        comboView.setEditState(false);
      }

    });
    for (Combo combo : comboConfiguration.getAllCombos()) {
      addComboToView(comboView, combo);
    }
  }

  private String createComboNameString(Combo combo) {
    String comboName = combo.getName().getText();
    if (Strings.isNullOrEmpty(comboName)) {
      comboName = resources.getString("CardView.CharmConfiguration.ComboCreation.UnnamedCombo");
    }
    return comboName;
  }

  private void initCharmLearnListening(final ComboConfigurationView comboView) {
    ICharmLearnListener charmLearnListener = new CharmLearnAdapter() {
      @Override
      public void charmLearned(Charm charm) {
        updateCharmListsInView(comboView);
      }

      @Override
      public void charmForgotten(Charm charm) {
        updateCharmListsInView(comboView);
      }
    };
    for (ILearningCharmGroup group : charmConfiguration.getAllGroups()) {
      group.addCharmLearnListener(charmLearnListener);
    }
  }

  private void addComboToView(ComboConfigurationView comboConfigurationView, final Combo combo) {
    ComboView comboView = comboConfigurationView.addComboView(createComboNameString(combo), convertToHtml(combo));
    Tool editTool = comboView.addTool();
    editTool.setIcon(new BasicUi().getEditIconPath());
    editTool.setText(resources.getString("CardView.CharmConfiguration.ComboCreation.EditLabel"));
    editTool.setCommand(new Command() {
      @Override
      public void execute() {
        comboConfiguration.beginComboEdit(combo);
      }
    });
    Tool deleteTool = comboView.addTool();
    deleteTool.setIcon(new BasicUi().getClearIconPath());
    deleteTool.setText(resources.getString("CardView.CharmConfiguration.ComboCreation.DeleteLabel"));
    deleteTool.setCommand(new Command() {
      @Override
      public void execute() {
        comboConfiguration.deleteCombo(combo);
      }
    });
    viewsByCombo.put(combo, comboView);
    toolsByCombo.put(combo, editTool);
  }

  private String convertToHtml(Combo combo) {
    String text = combo.getDescription().getText();
    Charm[] charms = combo.getCharms();
    String charmList = "<b>";
    Iterator<Charm> charmIterator = Arrays.asList(charms).iterator();
    if (charmIterator.hasNext()) {
      charmList = charmList.concat(labeler.getLabelForMagic(charmIterator.next()));
    }
    while (charmIterator.hasNext()) {
      charmList = charmList.concat(", " + labeler.getLabelForMagic(charmIterator.next()));
    }
    charmList += "</b>";
    if (Strings.isNullOrEmpty(text)) {
      return wrapHtml(charmList);
    }
    String converted = text.replace("\n", "<br>");
    return wrapHtml(charmList + " - <i>" + converted + "</i>");
  }

  private String wrapHtml(String text) {
    return "<html><body>" + text + "</body></html>";
  }

  private void updateCharmListsInView(ComboConfigurationView comboView) {
    comboView.setComboCharms(comboConfiguration.getEditCombo().getCharms());
    Charm[] learnedCharms = comboModel.getLearnedCharms();
    Arrays.sort(learnedCharms, new I18nedIdentificateComparator(resources));
    comboView.setAllCharms(learnedCharms);
  }

  private void initComboModelListening(final ComboConfigurationView comboView) {
    comboConfiguration.addComboModelListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        updateCharmListsInView(comboView);
      }
    });
  }

  private void initViewListening(ComboConfigurationView comboView) {
    comboView.addComboViewListener(new ComboViewListener() {
      @Override
      public void charmAdded(Object addedCharm) {
        comboConfiguration.addCharmToCombo((Charm) addedCharm, comboModel.isExperienced());
      }

      @Override
      public void charmRemoved(Object[] removedCharms) {
        List<Charm> removedCharmList = new ArrayList<>();
        for (Object charmObject : removedCharms) {
          removedCharmList.add((Charm) charmObject);
        }
        comboConfiguration.removeCharmsFromCombo(removedCharmList.toArray(new Charm[removedCharmList.size()]));
      }

      @Override
      public void comboFinalized() {
        comboConfiguration.finalizeCombo();
      }

      @Override
      public void comboCleared() {
        comboConfiguration.clearCombo();
      }
    });
  }

  private void setViewToEditing(Combo combo) {
    ComboView comboView = viewsByCombo.get(combo);
    createComboNameString(combo);
    comboView.updateCombo(createComboNameString(combo) + " (" + resources.getString("CardView.CharmConfiguration.ComboCreation.EditingLabel") + ")",
            convertToHtml(combo));
    toolsByCombo.get(combo).setText(resources.getString("CardView.CharmConfiguration.ComboCreation.RestartEditLabel"));
  }

  private void setViewsToNotEditing() {
    for (Combo currentCombo : viewsByCombo.keySet()) {
      ComboView comboView = viewsByCombo.get(currentCombo);
      comboView.updateCombo(createComboNameString(currentCombo), convertToHtml(currentCombo));
      toolsByCombo.get(currentCombo).setText(resources.getString("CardView.CharmConfiguration.ComboCreation.EditLabel"));
    }
  }
}