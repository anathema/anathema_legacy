package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.concept.IEditMotivationListener;
import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesViewProperties;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.gui.ui.ObjectUiListCellRenderer;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class CharacterConceptAndRulesPresenter {

  private final ICharacterConceptAndRulesView view;
  private final ICharacter character;
  private final Resources resources;

  public CharacterConceptAndRulesPresenter(ICharacter character, ICharacterConceptAndRulesView view,
                                           Resources resources) {
    this.character = character;
    this.view = view;
    this.resources = resources;
  }

  public void initPresentation() {
    initRulesPresentation();
    initCastePresentation();
    IMotivation motivation = character.getCharacterConcept().getWillpowerRegainingConcept();
    initMotivationPresentation(motivation);
    initGui();
  }

  private void initMotivationPresentation(final IMotivation motivation) {
    final ITextView textView = initTextualDescriptionPresentation(motivation.getEditableDescription(),
            "Label.Motivation");
    final Tool edit = addEditTool(motivation);
    final Tool cancel = addCancelTool(motivation);
    final Tool confirm = addConfirmTool(motivation);
    final Tool confirmAndSpendXp = addConfirmAndSpendXp(motivation);
    motivation.addEditingListener(new IEditMotivationListener() {
      @Override
      public void editBegun() {
        textView.setEnabled(true);
        textView.setText(motivation.getEditableDescription().getText());
        edit.disable();
        cancel.enable();
        confirm.enable();
        confirmAndSpendXp.enable();
      }

      @Override
      public void editEnded() {
        textView.setEnabled(false);
        textView.setText(motivation.getDescription().getText());
        controlEditEnabled(edit);
        cancel.disable();
        confirm.disable();
        confirmAndSpendXp.disable();
      }
    });
    controlEditEnabled(edit);
    cancel.disable();
    confirm.disable();
    confirmAndSpendXp.disable();
    character.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        controlEditEnabled(edit);
        cancel.disable();
        confirm.disable();
        confirmAndSpendXp.disable();
      }
    });
  }

  private Tool addConfirmAndSpendXp(final IMotivation motivation) {
    final Tool endXpButton = view.addTool();
    endXpButton.setIcon(new CharacterUI().getFinalizeXpIconPath());
    endXpButton.setTooltip(resources.getString("CharacterConcept.Motivation.EndEditXP.Tooltip"));
    endXpButton.setCommand(new Command() {
      @Override
      public void execute() {
        motivation.endEditXPSpending(resources.getString("CharacterConcept.Motivation.XPSpent"));
      }
    });
    return endXpButton;
  }

  private Tool addConfirmTool(final IMotivation motivation) {
    final Tool endButton = view.addTool();
    endButton.setIcon(new CharacterUI().getFinalizeIconPath());
    endButton.setTooltip(resources.getString("CharacterConcept.Motivation.EndEdit.Tooltip"));
    endButton.setCommand(new Command() {
      @Override
      public void execute() {
        motivation.endEdit();
      }
    });
    return endButton;
  }

  private Tool addCancelTool(final IMotivation motivation) {
    final Tool cancelButton = view.addTool();
    cancelButton.setIcon(new CharacterUI().getCancelComboEditIconPath());
    cancelButton.setTooltip(resources.getString("CharacterConcept.Motivation.CancelEdit.Tooltip"));
    cancelButton.setCommand(new Command() {
      @Override
      public void execute() {
        motivation.cancelEdit();
      }
    });
    return cancelButton;
  }

  private Tool addEditTool(final IMotivation motivation) {
    final Tool beginButton = view.addTool();
    beginButton.setIcon(new BasicUi().getEditIconPath());
    beginButton.setTooltip(resources.getString("CharacterConcept.Motivation.BeginEdit.Tooltip"));
    beginButton.setCommand(new Command() {
      @Override
      public void execute() {
        motivation.beginEdit();
      }
    });
    return beginButton;
  }

  private void controlEditEnabled(Tool beginButton) {
    if (character.isExperienced()) {
      beginButton.enable();
    } else {
      beginButton.disable();
    }
  }

  private ITextView initTextualDescriptionPresentation(ITextualDescription description, String resourceKey) {
    final ITextView textView = view.addLabelTextView(resources.getString(resourceKey));
    new TextualPresentation().initView(textView, description);
    character.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        textView.setEnabled(!experienced);
      }
    });
    textView.setEnabled(!character.isExperienced());
    return textView;
  }

  private void initGui() {
    view.initGui(new ICharacterConceptAndRulesViewProperties() {
      @Override
      public String getConceptTitle() {
        return resources.getString("CardView.CharacterConcept.Concept");
      }

      @Override
      public String getRulesTitle() {
        return resources.getString("CardView.CharacterConcept.Rules");
      }
    });
  }

  private void initRulesPresentation() {
    view.addRulesLabel(resources.getString("Rules.CharacterType.Message",
            resources.getString(character.getCharacterTemplate().getPresentationProperties().getNewActionResource())));
  }

  private boolean initCastePresentation() {
    ICharacterTemplate template = character.getCharacterTemplate();
    if (template.getCasteCollection().isEmpty()) {
      return false;
    }
    String casteLabelResourceKey = template.getPresentationProperties().getCasteLabelResource();
    ObjectUi<Object> casteUi = new CasteSelectObjectUi(resources, template.getPresentationProperties());
    ICasteType[] casteTypes = template.getCasteCollection().getAllCasteTypes(
            character.getCharacterTemplate().getTemplateType());
    final IObjectSelectionView<ICasteType> casteView = view.addObjectSelectionView(
            resources.getString(casteLabelResourceKey), casteTypes, new ObjectUiListCellRenderer(casteUi), false);
    final ITypedDescription<ICasteType> caste = character.getCharacterConcept().getCaste();
    casteView.setSelectedObject(caste.getType());
    casteView.addObjectSelectionChangedListener(new ObjectValueListener<ICasteType>() {
      @Override
      public void valueChanged(ICasteType newValue) {
        caste.setType(newValue);
      }
    });
    caste.addChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        casteView.setSelectedObject(caste.getType());
      }
    });
    character.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        casteView.setEnabled(!experienced);
      }
    });
    casteView.setEnabled(!character.isExperienced());
    return true;
  }
}