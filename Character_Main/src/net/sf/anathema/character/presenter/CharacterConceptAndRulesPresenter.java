package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.concept.IEditMotivationListener;
import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.character.presenter.magic.IContentPresenter;
import net.sf.anathema.character.view.IConceptAndRulesViewFactory;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesViewProperties;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.ContentView;
import net.sf.anathema.framework.presenter.view.SimpleViewContentView;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.ui.ObjectUi;
import net.sf.anathema.lib.gui.ui.ObjectUiListCellRenderer;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

import javax.swing.AbstractButton;
import java.awt.Component;

public class CharacterConceptAndRulesPresenter implements IContentPresenter {

  private final ICharacterConceptAndRulesView view;
  private final ICharacter character;
  private final IResources resources;

  public CharacterConceptAndRulesPresenter(ICharacter character, IConceptAndRulesViewFactory viewFactory,
                                           IResources resources) {
    this.character = character;
    this.view = viewFactory.createCharacterConceptView();
    this.resources = resources;
  }

  @Override
  public void initPresentation() {
    initRulesPresentation();
    initCastePresentation();
    IMotivation motivation = character.getCharacterConcept().getWillpowerRegainingConcept();
    initMotivationPresentation(motivation);
    initGui();
  }

  @Override
  public ContentView getTabContent() {
    String conceptHeader = resources.getString("CardView.CharacterConcept.Title"); 
    return new SimpleViewContentView(new ContentProperties(conceptHeader), view);
  }

  private void initMotivationPresentation(final IMotivation motivation) {
    final ITextView textView = initTextualDescriptionPresentation(motivation.getEditableDescription(),
            "Label.Motivation"); 
    final SmartAction beginEditAction = new SmartAction(new BasicUi().getEditIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        motivation.beginEdit();
      }
    };
    CharacterUI characterUI = new CharacterUI();
    beginEditAction.setToolTipText(resources.getString("CharacterConcept.Motivation.BeginEdit.Tooltip")); 
    final SmartAction cancelEditAction = new SmartAction(characterUI.getCancelComboEditIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        motivation.cancelEdit();
      }
    };
    cancelEditAction.setToolTipText(
            resources.getString("CharacterConcept.Motivation.CancelEdit.Tooltip")); 
    final SmartAction endEditAction = new SmartAction(characterUI.getFinalizeIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        motivation.endEdit();
      }
    };
    endEditAction.setToolTipText(resources.getString("CharacterConcept.Motivation.EndEdit.Tooltip")); 
    final SmartAction endEditXPAction = new SmartAction(characterUI.getFinalizeXPIcon()) {

      @Override
      protected void execute(Component parentComponent) {
        motivation.endEditXPSpending(resources.getString("CharacterConcept.Motivation.XPSpent")); 
      }
    };
    endEditXPAction.setToolTipText(resources.getString("CharacterConcept.Motivation.EndEditXP.Tooltip")); 
    final AbstractButton beginButton = view.addAction(beginEditAction);
    view.addAction(endEditAction);
    view.addAction(endEditXPAction);
    motivation.addEditingListener(new IEditMotivationListener() {
      @Override
      public void editBegun() {
        textView.setEnabled(true);
        textView.setText(motivation.getEditableDescription().getText());
        beginButton.setAction(cancelEditAction);
        endEditAction.setEnabled(true);
        endEditXPAction.setEnabled(true);
      }

      @Override
      public void editEnded() {
        textView.setEnabled(false);
        textView.setText(motivation.getDescription().getText());
        beginButton.setAction(beginEditAction);
        endEditAction.setEnabled(false);
        endEditXPAction.setEnabled(false);
      }
    });

    beginEditAction.setEnabled(character.isExperienced());
    endEditAction.setEnabled(false);
    endEditXPAction.setEnabled(false);
    character.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        beginEditAction.setEnabled(experienced);
        endEditAction.setEnabled(false);
        endEditXPAction.setEnabled(false);
      }
    });
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
    view.addRulesLabel(resources.getString("Rules.CharacterType.Message", resources.getString(
            character.getCharacterTemplate().getPresentationProperties().getNewActionResource()))); 
  }

  private boolean initCastePresentation() {
    ICharacterTemplate template = character.getCharacterTemplate();
    if (template.getCasteCollection().isEmpty()) {
      return false;
    }
    String casteLabelResourceKey = template.getPresentationProperties().getCasteLabelResource();
    ObjectUi<Object> casteUi = new CasteSelectObjectUi(resources, template.getPresentationProperties());
    ICasteType[] casteTypes = template.getCasteCollection().getAllCasteTypes(character.getCharacterTemplate().getTemplateType());
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