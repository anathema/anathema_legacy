package net.sf.anathema.hero.combos.display.presenter;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.CharmLearnAdapter;
import net.sf.anathema.hero.charms.model.ICharmLearnListener;
import net.sf.anathema.hero.charms.model.ILearningCharmGroup;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.combos.model.ComboConfigurationModel;
import net.sf.anathema.hero.concept.ConceptChange;
import net.sf.anathema.hero.charms.display.magic.MagicLearnPresenter;
import net.sf.anathema.hero.charms.display.magic.MagicLearnView;
import net.sf.anathema.hero.charms.display.magic.MagicViewListener;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.change.ChangeFlavor;
import net.sf.anathema.hero.model.change.FlavoredChangeListener;
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
import java.util.Collections;
import java.util.HashMap;
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
    initMagicLearnView();
    view.addComboEditor(properties);
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

  private void initMagicLearnView() {
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
        ComboView comboView = viewsByCombo.get(combo);
        comboView.displayComboName(getComboName(combo));
        updateRepresentation(comboView, combo);
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
    ComboView comboView = container.addView(getComboName(combo));
    updateRepresentation(comboView, combo);
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

  private void updateRepresentation(ComboView comboView, Combo combo) {
    comboView.displayCharmNames(getCommaSeparatedCharms(combo.getCharms()));
    comboView.displayDescription(combo.getDescription().getText());
  }

  private String getCommaSeparatedCharms(Charm[] charms) {
    List<String> charmNames = Lists.transform(Arrays.asList(charms), new Function<Charm, String>() {
      @Override
      public String apply(Charm input) {
        return labeler.getLabelForMagic(input);
      }
    });
    return Joiner.on(", ").join(charmNames);
  }

  private void updateCharmListsInView() {
    showEligibleCharms();
    showCharmsInCombo();
  }

  private void showEligibleCharms() {
    List<Charm> eligibleCharms = comboModel.getEligibleCharms();
    Collections.sort(eligibleCharms, new I18nedIdentificateComparator(resources));
    learnView.setAvailableMagic(eligibleCharms);
  }

  private void showCharmsInCombo() {
    List<Charm> charmsInCombo = Arrays.asList(comboConfiguration.getEditCombo().getCharms());
    learnView.setLearnedMagic(charmsInCombo);
  }

  private void initComboModelListening() {
    comboConfiguration.addComboModelListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        updateCharmListsInView();
      }
    });
  }

  private void setViewToEditing(Combo combo) {
    ComboView comboView = viewsByCombo.get(combo);
    comboView.displayComboName(getComboName(combo) + " (" + resources.getString("CardView.CharmConfiguration.ComboCreation.EditingLabel") + ")");
    updateRepresentation(comboView, combo);
    toolsByCombo.get(combo).setText(resources.getString("CardView.CharmConfiguration.ComboCreation.RestartEditLabel"));
  }

  private void setViewsToNotEditing() {
    for (Combo currentCombo : viewsByCombo.keySet()) {
      ComboView comboView = viewsByCombo.get(currentCombo);
      comboView.displayComboName(getComboName(currentCombo));
      updateRepresentation(comboView, currentCombo);
      toolsByCombo.get(currentCombo).setText(resources.getString("CardView.CharmConfiguration.ComboCreation.EditLabel"));
    }
  }

  private String getComboName(Combo combo) {
    String comboName = combo.getName().getText();
    if (Strings.isNullOrEmpty(comboName)) {
      comboName = resources.getString("CardView.CharmConfiguration.ComboCreation.UnnamedCombo");
    }
    return comboName;
  }
}