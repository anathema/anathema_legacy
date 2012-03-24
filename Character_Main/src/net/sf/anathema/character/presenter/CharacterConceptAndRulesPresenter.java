package net.sf.anathema.character.presenter;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.ui.IObjectUi;
import net.disy.commons.swing.ui.ObjectUiListCellRenderer;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.concept.IEditMotivationListener;
import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.character.presenter.magic.IContentPresenter;
import net.sf.anathema.character.view.ICharacterConceptAndRulesViewFactory;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesViewProperties;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IViewContent;
import net.sf.anathema.framework.presenter.view.SimpleViewContent;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

import javax.swing.AbstractButton;
import java.awt.Component;

public class CharacterConceptAndRulesPresenter implements IContentPresenter {

  private final ICharacterConceptAndRulesView view;
  private final ICharacterStatistics statistics;
  private final IResources resources;

  public CharacterConceptAndRulesPresenter(ICharacterStatistics statistics,
                                           ICharacterConceptAndRulesViewFactory viewFactory, IResources resources) {
    this.statistics = statistics;
    this.view = viewFactory.createCharacterConceptView();
    this.resources = resources;
  }

  @Override
  public void initPresentation() {
    initRulesPresentation();
    final boolean casteRow = initCastePresentation();
    IMotivation motivation = statistics.getCharacterConcept().getWillpowerRegainingConcept();
    initMotivationPresentation(motivation, casteRow);
    initAgePresentation();
    initGui();
  }

  @Override
  public IViewContent getTabContent() {
    String conceptHeader = resources.getString("CardView.CharacterConcept.Title"); //$NON-NLS-1$
    return new SimpleViewContent(new ContentProperties(conceptHeader), view);
  }

  private void initAgePresentation() {
    final IIntegerDescription age = statistics.getCharacterConcept().getAge();

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
      private static final long serialVersionUID = -1054675766697466937L;

      @Override
      protected void execute(Component parentComponent) {
        motivation.beginEdit();
      }
    };
    CharacterUI characterUI = new CharacterUI(resources);
    beginEditAction.setToolTipText(resources.getString("CharacterConcept.Motivation.BeginEdit.Tooltip")); //$NON-NLS-1$
    final SmartAction cancelEditAction = new SmartAction(characterUI.getCancelComboEditIcon()) {
      private static final long serialVersionUID = 1455192549297256400L;

      @Override
      protected void execute(Component parentComponent) {
        motivation.cancelEdit();
      }
    };
    cancelEditAction.setToolTipText(
            resources.getString("CharacterConcept.Motivation.CancelEdit.Tooltip")); //$NON-NLS-1$
    final SmartAction endEditAction = new SmartAction(characterUI.getFinalizeIcon()) {
      private static final long serialVersionUID = 1191861661014239378L;

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

    beginEditAction.setEnabled(statistics.isExperienced());
    endEditAction.setEnabled(false);
    endEditXPAction.setEnabled(false);
    statistics.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        beginEditAction.setEnabled(experienced);
        endEditAction.setEnabled(false);
        endEditXPAction.setEnabled(false);
      }
    });
  }

  private ITextView initTextualDescriptionPresentation(final ITextualDescription description, String resourceKey) {
    final ITextView textView = view.addLabelTextView(resources.getString(resourceKey));
    new TextualPresentation().initView(textView, description);
    statistics.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        textView.setEnabled(!experienced);
      }
    });
    textView.setEnabled(!statistics.isExperienced());
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
            statistics.getCharacterTemplate().getPresentationProperties().getNewActionResource()))); //$NON-NLS-1$
  }

  private boolean initCastePresentation() {
    final ICharacterTemplate template = statistics.getCharacterTemplate();
    IExaltedEdition edition = statistics.getCharacterContext().getBasicCharacterContext().getEdition();
    if (template.getCasteCollection().getAllCasteTypes(edition,
            statistics.getCharacterTemplate().getTemplateType()).length <= 0) {
      return false;
    }
    String casteLabelResourceKey = template.getPresentationProperties().getCasteLabelResource();
    IObjectUi<Object> casteUi = new CasteSelectObjectUi(resources, template.getPresentationProperties(),
            template.getEdition());
    ICasteType[] allCasteTypes = template.getCasteCollection().getAllCasteTypes(edition,
            statistics.getCharacterTemplate().getTemplateType());
    final IObjectSelectionView<ICasteType> casteView = view.addObjectSelectionView(
            resources.getString(casteLabelResourceKey), allCasteTypes, new ObjectUiListCellRenderer(casteUi), false);
    final ITypedDescription<ICasteType> caste = statistics.getCharacterConcept().getCaste();
    casteView.setSelectedObject(caste.getType());
    casteView.addObjectSelectionChangedListener(new IObjectValueChangedListener<ICasteType>() {
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
    statistics.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        casteView.setEnabled(!experienced);
      }
    });
    casteView.setEnabled(!statistics.isExperienced());
    return true;
  }
}
