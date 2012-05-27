package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.IIntegerDescription;
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
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.ui.IObjectUi;
import net.sf.anathema.lib.gui.ui.ObjectUiListCellRenderer;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
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
    boolean casteRow = initCastePresentation();
    IMotivation motivation = character.getCharacterConcept().getWillpowerRegainingConcept();
    initMotivationPresentation(motivation, casteRow);
    initAgePresentation();
    initGui();
  }

  @Override
  public ContentView getTabContent() {
    String conceptHeader = resources.getString("CardView.CharacterConcept.Title"); //$NON-NLS-1$
    return new SimpleViewContentView(new ContentProperties(conceptHeader), view);
  }

  private void initAgePresentation() {
    final IIntegerDescription age = character.getCharacterConcept().getAge();
    IntegerSpinner ageSpinner = new IntegerSpinner(age.getValue());
    ageSpinner.setPreferredWidth(48);
    ageSpinner.setStepSize(5);
    view.addSpinner(resources.getString("Label.Age"), ageSpinner);
    ageSpinner.addChangeListener(new IIntValueChangedListener() {
      @Override
      public void valueChanged(int newValue) {
        age.setValue(newValue);
      }
    });
  }

  private void initMotivationPresentation(final IMotivation motivation, boolean casteRow) {
    final ITextView textView = initTextualDescriptionPresentation(motivation.getEditableDescription(),
            "Label.Motivation"); //$NON-NLS-1$
    final SmartAction beginEditAction = new SmartAction(new BasicUi(resources).getEditIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        motivation.beginEdit();
      }
    };
    CharacterUI characterUI = new CharacterUI(resources);
    beginEditAction.setToolTipText(resources.getString("CharacterConcept.Motivation.BeginEdit.Tooltip")); //$NON-NLS-1$
    final SmartAction cancelEditAction = new SmartAction(characterUI.getCancelComboEditIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        motivation.cancelEdit();
      }
    };
    cancelEditAction.setToolTipText(
            resources.getString("CharacterConcept.Motivation.CancelEdit.Tooltip")); //$NON-NLS-1$
    final SmartAction endEditAction = new SmartAction(characterUI.getFinalizeIcon()) {
      @Override
      protected void execute(Component parentComponent) {
        motivation.endEdit();
      }
    };
    endEditAction.setToolTipText(resources.getString("CharacterConcept.Motivation.EndEdit.Tooltip")); //$NON-NLS-1$
    final SmartAction endEditXPAction = new SmartAction(characterUI.getFinalizeXPIcon()) {

      @Override
      protected void execute(Component parentComponent) {
        motivation.endEditXPSpending(resources.getString("CharacterConcept.Motivation.XPSpent")); //$NON-NLS-1$
      }
    };
    endEditXPAction.setToolTipText(resources.getString("CharacterConcept.Motivation.EndEditXP.Tooltip")); //$NON-NLS-1$
    int row = casteRow ? 1 : 0;
    final AbstractButton beginButton = view.addAction(beginEditAction, row);
    view.addAction(endEditAction, row);
    view.addAction(endEditXPAction, row);
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
        return resources.getString("CardView.CharacterConcept.Concept"); //$NON-NLS-1$
      }

      @Override
      public String getRulesTitle() {
        return resources.getString("CardView.CharacterConcept.Rules");
      } //$NON-NLS-1$
    });
  }

  private void initRulesPresentation() {
    view.addRulesLabel(resources.getString("Rules.CharacterType.Message", resources.getString(
            character.getCharacterTemplate().getPresentationProperties().getNewActionResource()))); //$NON-NLS-1$
  }

  private boolean initCastePresentation() {
    ICharacterTemplate template = character.getCharacterTemplate();
    if (template.getCasteCollection().getAllCasteTypes(
            character.getCharacterTemplate().getTemplateType()).length <= 0) {
      return false;
    }
    String casteLabelResourceKey = template.getPresentationProperties().getCasteLabelResource();
    IObjectUi<Object> casteUi = new CasteSelectObjectUi(resources, template.getPresentationProperties());
    ICasteType[] allCasteTypes = template.getCasteCollection().getAllCasteTypes(
            character.getCharacterTemplate().getTemplateType());
    final IObjectSelectionView<ICasteType> casteView = view.addObjectSelectionView(
            resources.getString(casteLabelResourceKey), allCasteTypes, new ObjectUiListCellRenderer(casteUi), false);
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