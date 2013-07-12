package net.sf.anathema.hero.combos.display.presenter;

import com.google.common.base.Preconditions;
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
import net.sf.anathema.hero.magic.display.MagicLearnPresenter;
import net.sf.anathema.hero.magic.display.MagicLearnView;
import net.sf.anathema.hero.magic.display.MagicViewListener;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.compare.I18nedIdentificateComparator;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
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
  private final CombinedComboViewAndMagicProperties properties;
  private Hero hero;
  private final Resources resources;
  private final ComboConfigurationView view;
  private final MagicDisplayLabeler labeler;
  private Tool clearTool;
  private Tool finalizeTool;
  private MagicLearnView learnView;
  private boolean isDescriptionEntered;
  private boolean isNameEntered;

  public ComboConfigurationPresenter(Hero hero, Resources resources, ComboConfigurationModel comboModel, ComboConfigurationView view) {
    this.hero = hero;
    this.resources = resources;
    this.comboModel = comboModel;
    this.charmConfiguration = comboModel.getCharmConfiguration();
    this.comboConfiguration = comboModel.getCombos();
    this.labeler = new MagicDisplayLabeler(resources);
    this.view = view;
    this.properties = new CombinedComboViewAndMagicProperties(resources, comboConfiguration, comboModel.getMagicDescriptionProvider());
  }

  public void initPresentation() {
    view.initGui(properties);
    initMagicLearnView(properties);
    initCharmLearnListening();
    ITextView nameView = view.addComboNameView(resources.getString("CardView.CharmConfiguration.ComboCreation.NameLabel"));
    nameView.addTextChangedListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        isNameEntered = newValue != null && !newValue.equals("");
        enableOrDisableClearButton();
      }
    });
    Combo editCombo = comboConfiguration.getEditCombo();
    TextualPresentation textualPresentation = new TextualPresentation();
    textualPresentation.initView(nameView, editCombo.getName());
    ITextView descriptionView = view.addComboDescriptionView(resources.getString("CardView.CharmConfiguration.ComboCreation.DescriptionLabel"));
    descriptionView.addTextChangedListener(new ObjectValueListener<String>() {
      @Override
      public void valueChanged(String newValue) {
        isDescriptionEntered = newValue != null && !newValue.equals("");
        enableOrDisableClearButton();
      }
    });
    textualPresentation.initView(descriptionView, editCombo.getDescription());
    updateCharmListsInView();
    initViewListening();
    initComboModelListening();
    initComboConfigurationListening();
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

  private void initMagicLearnView(CombinedComboViewAndMagicProperties properties) {
    this.learnView = view.addMagicLearnView(properties);
    MagicLearnPresenter magicLearnPresenter = new MagicLearnPresenter(learnView);
    magicLearnPresenter.initPresentation(properties);
    magicLearnPresenter.addChangeListener(new MagicViewListener() {
      @Override
      public void removeMagicRequested(Object[] removedMagic) {
        List<Charm> charms = new ArrayList<>();
        for (Object charmObject : removedMagic) {
          charms.add((Charm) charmObject);
        }
        comboConfiguration.removeCharmsFromCombo(charms.toArray(new Charm[charms.size()]));
      }

      @Override
      public void addMagicRequested(Object[] addedMagic) {
        Preconditions.checkArgument(addedMagic.length == 1, "Only one charm may be added.");
        comboConfiguration.addCharmToCombo((Charm) addedMagic[0], comboModel.isExperienced());
      }
    });
    this.finalizeTool = createFinalizeComboButton(properties, learnView);
    this.clearTool = createClearTool(properties, learnView);
    learnView.addLearnedListListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        enableOrDisableFinalizeButton(learnView, finalizeTool);
        enableOrDisableClearButton();
      }
    });
  }

  private void enableOrDisableClearButton() {
    boolean canEnableClearButton = isDescriptionEntered || isNameEntered || learnView.hasAnyElementLearned();
    if (canEnableClearButton) {
      clearTool.enable();
    } else {
      clearTool.disable();
    }
  }

  private Tool createClearTool(ComboViewProperties viewProperties, MagicLearnView learnView) {
    Command command = new Command() {
      @Override
      public void execute() {
        comboConfiguration.clearCombo();
      }
    };
    Tool tool = learnView.addAdditionalTool();
    tool.disable();
    tool.setCommand(command);
    tool.setIcon(viewProperties.getClearButtonIcon());
    tool.setTooltip(viewProperties.getClearButtonToolTip());
    return tool;
  }


  private Tool createFinalizeComboButton(ComboViewProperties viewProperties, MagicLearnView learnView) {
    Command command = new Command() {
      @Override
      public void execute() {
        comboConfiguration.finalizeCombo();
      }
    };
    Tool tool = learnView.addAdditionalTool();
    tool.disable();
    tool.setCommand(command);
    tool.setIcon(viewProperties.getFinalizeButtonIcon());
    tool.setTooltip(viewProperties.getFinalizeButtonToolTip());
    return tool;
  }

  private void enableOrDisableFinalizeButton(MagicLearnView learnView, Tool finalizeTool) {
    if (learnView.hasMoreThanOneElementLearned()) {
      finalizeTool.enable();
    } else {
      finalizeTool.disable();
    }
  }

  private void enableCrossPrerequisiteTypeCombos() {
    boolean alienCharms = comboModel.isAlienCharmsAllowed();
    comboConfiguration.setCrossPrerequisiteTypeComboAllowed(alienCharms);
  }

  private void initComboConfigurationListening() {
    final ComboContainer container = view.addComboContainer();
    comboConfiguration.addComboConfigurationListener(new ComboConfigurationListener() {
      @Override
      public void comboAdded(Combo combo) {
        addComboToView(container, combo);
      }

      @Override
      public void comboChanged(Combo combo) {
        viewsByCombo.get(combo).updateCombo(createComboNameString(combo), convertToHtml(combo));
      }

      @Override
      public void comboDeleted(Combo combo) {
        viewsByCombo.get(combo).remove();
      }

      @Override
      public void editBegun(Combo combo) {
        setViewsToNotEditing();
        setViewToEditing(combo);
        setEditState(true);
      }

      @Override
      public void editEnded() {
        setViewsToNotEditing();
        setEditState(false);
      }
    });
    for (Combo combo : comboConfiguration.getAllCombos()) {
      addComboToView(container, combo);
    }
  }

  private void setEditState(boolean editing) {
    clearTool.setIcon(editing ? properties.getCancelEditButtonIcon() : properties.getClearButtonIcon());
    clearTool.setTooltip(editing ? properties.getCancelButtonEditToolTip() : properties.getClearButtonToolTip());
    finalizeTool.setTooltip(
            editing ? properties.getFinalizeButtonEditToolTip() : properties.getFinalizeButtonToolTip());
  }


  private String createComboNameString(Combo combo) {
    String comboName = combo.getName().getText();
    if (Strings.isNullOrEmpty(comboName)) {
      comboName = resources.getString("CardView.CharmConfiguration.ComboCreation.UnnamedCombo");
    }
    return comboName;
  }

  private void initCharmLearnListening() {
    ICharmLearnListener charmLearnListener = new CharmLearnAdapter() {
      @Override
      public void charmLearned(Charm charm) {
        updateCharmListsInView();
      }

      @Override
      public void charmForgotten(Charm charm) {
        updateCharmListsInView();
      }
    };
    for (ILearningCharmGroup group : charmConfiguration.getAllGroups()) {
      group.addCharmLearnListener(charmLearnListener);
    }
  }

  private void addComboToView(ComboContainer container, final Combo combo) {
    ComboView comboView = container.addView(createComboNameString(combo), convertToHtml(combo));
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

  private void updateCharmListsInView() {
    learnView.setLearnedMagic(Arrays.asList(comboConfiguration.getEditCombo().getCharms()));
    Charm[] learnedCharms = comboModel.getLearnedCharms();
    Arrays.sort(learnedCharms, new I18nedIdentificateComparator(resources));
    learnView.setAvailableMagic(Arrays.asList(learnedCharms));
  }

  private void initComboModelListening() {
    comboConfiguration.addComboModelListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        updateCharmListsInView();
      }
    });
  }

  private void initViewListening() {
    view.addComboViewListener(new ComboViewListener() {
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