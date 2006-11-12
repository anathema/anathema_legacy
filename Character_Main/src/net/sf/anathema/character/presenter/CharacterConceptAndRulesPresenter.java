package net.sf.anathema.character.presenter;

import java.awt.Component;

import javax.swing.AbstractButton;

import net.disy.commons.swing.action.SmartAction;
import net.disy.commons.swing.ui.IObjectUi;
import net.disy.commons.swing.ui.ObjectUiListCellRenderer;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.concept.IEditMotivationListener;
import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.character.model.concept.INature;
import net.sf.anathema.character.model.concept.INatureType;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConceptVisitor;
import net.sf.anathema.character.model.concept.NatureProvider;
import net.sf.anathema.character.presenter.charm.IContentPresenter;
import net.sf.anathema.character.view.ICharacterConceptAndRulesViewFactory;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesViewProperties;
import net.sf.anathema.character.view.concept.IWillpowerConditionView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IViewContent;
import net.sf.anathema.framework.presenter.view.SimpleViewContent;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.compare.I18nedIdentificateSorter;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public class CharacterConceptAndRulesPresenter implements IContentPresenter {

  private final ICharacterConceptAndRulesView view;
  private final ICharacterStatistics statistics;
  private final IResources resources;

  public CharacterConceptAndRulesPresenter(
      ICharacterStatistics statistics,
      ICharacterConceptAndRulesViewFactory viewFactory,
      IResources resources) {
    this.statistics = statistics;
    this.view = viewFactory.createCharacterConceptView();
    this.resources = resources;
  }

  public void initPresentation() {
    initRulesPresentation();
    final boolean casteRow = initCastePresentation();
    statistics.getCharacterConcept().getWillpowerRegainingConcept().accept(new IWillpowerRegainingConceptVisitor() {
      public void accept(INature nature) {
        initNaturePresentation(nature);
      }

      public void accept(IMotivation motivation) {
        initMotivationPresentation(motivation, casteRow);
      }
    });
    initConceptPresentation();
    initGui();
  }

  public IViewContent getTabContent() {
    String conceptHeader = resources.getString("CardView.CharacterConcept.Title"); //$NON-NLS-1$
    return new SimpleViewContent(new ContentProperties(conceptHeader), view);
  }

  private void initMotivationPresentation(final IMotivation motivation, boolean casteRow) {
    final ITextView textView = initTextualDescriptionPresentation(
        motivation.getEditableDescription(),
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
    cancelEditAction.setToolTipText(resources.getString("CharacterConcept.Motivation.Cancel.Tooltip")); //$NON-NLS-1$
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
      public void editBegun() {
        textView.setEnabled(true);
        textView.setText(motivation.getEditableDescription().getText());
        beginButton.setAction(cancelEditAction);
        endEditAction.setEnabled(true);
        endEditXPAction.setEnabled(true);
      }

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

  private void initConceptPresentation() {
    initTextualDescriptionPresentation(statistics.getCharacterConcept().getConcept(), "Label.Concept"); //$NON-NLS-1$
  }

  private ITextView initTextualDescriptionPresentation(final ITextualDescription description, String resourceKey) {
    final ITextView textView = view.addLabelTextView(resources.getString(resourceKey));
    textView.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        description.setText(newValue);
      }
    });
    description.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        textView.setText(newValue);
      }
    });
    textView.setText(description.getText());
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
      public String getConceptTitle() {
        return resources.getString("CardView.CharacterConcept.Concept"); //$NON-NLS-1$
      }

      public String getRulesTitle() {
        return resources.getString("CardView.CharacterConcept.Rules");} //$NON-NLS-1$
    });
  }

  private void initNaturePresentation(INature nature) {
    INatureType[] unsortedNatures = NatureProvider.getInstance().getNatures();
    INatureType[] natures = new INatureType[unsortedNatures.length];
    new I18nedIdentificateSorter<INatureType>() {
      @Override
      protected String getString(final IResources sorterResources, INatureType type) {
        return sorterResources.getString("Nature." + type.getId() + ".Name"); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }.sortAscending(unsortedNatures, natures, resources);
    final IObjectSelectionView<INatureType> natureView = view.addObjectSelectionView(
        resources.getString("Label.Nature"), //$NON-NLS-1$
        natures,
        new IdentificateSelectCellRenderer("Nature.", ".Name", resources),//$NON-NLS-1$ //$NON-NLS-2$
        false);
    final ITypedDescription<INatureType> natureType = nature.getDescription();
    natureView.setSelectedObject(natureType.getType());
    final IWillpowerConditionView willpowerConditionLabel = view.addWillpowerConditionView(resources.getString("CharacterConcept.GainWillpower")); //$NON-NLS-1$
    natureView.addObjectSelectionChangedListener(new IObjectValueChangedListener<INatureType>() {
      public void valueChanged(INatureType newValue) {
        natureType.setType(newValue);
      }
    });
    natureType.addChangeListener(new IChangeListener() {
      public void changeOccured() {
        updateNature(natureView, willpowerConditionLabel, natureType.getType());
      }
    });
    updateNature(natureView, willpowerConditionLabel, natureType.getType());
    statistics.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        natureView.setEnabled(!experienced);
        willpowerConditionLabel.setEnabled(!experienced);
      }
    });
    natureView.setEnabled(!statistics.isExperienced());
    willpowerConditionLabel.setEnabled(!statistics.isExperienced());
  }

  private void initRulesPresentation() {
    view.addRulesLabel(resources.getString(
        "Rules.CharacterType.Message", resources.getString(statistics.getCharacterTemplate().getPresentationProperties().getNewActionResource()))); //$NON-NLS-1$
    view.addRulesLabel(resources.getString(
        "Rules.Ruleset.Message", resources.getString("Ruleset." + statistics.getRules().getId()))); //$NON-NLS-1$//$NON-NLS-2$
  }

  private void updateNature(
      final IObjectSelectionView<INatureType> natureView,
      final IWillpowerConditionView willpowerConditionLabel,
      INatureType natureType) {
    natureView.setSelectedObject(natureType);
    if (natureType == null) {
      willpowerConditionLabel.setText(null);
      return;
    }
    String willpowerCondition = resources.getString("Nature." + natureType + ".Text"); //$NON-NLS-1$ //$NON-NLS-2$
    if (willpowerCondition == null) {
      willpowerCondition = resources.getString("CharacterConcept.WillpowerCondition.NotSpecified"); //$NON-NLS-1$
    }
    willpowerConditionLabel.setText(willpowerCondition);
  }

  private boolean initCastePresentation() {
    final ICharacterTemplate template = statistics.getCharacterTemplate();
    if (template.getCasteCollection().getAllCasteTypes().length <= 0) {
      return false;
    }
    String casteLabelResourceKey = template.getPresentationProperties().getCasteLabelResource();
    IObjectUi casteUi = new CasteSelectObjectUi(resources, template.getPresentationProperties(), template.getEdition());
    ICasteType[] allCasteTypes = template.getCasteCollection().getAllCasteTypes();
    final IObjectSelectionView<ICasteType> casteView = view.addObjectSelectionView(
        resources.getString(casteLabelResourceKey),
        allCasteTypes,
        new ObjectUiListCellRenderer(casteUi),
        false);
    final ITypedDescription<ICasteType> caste = statistics.getCharacterConcept().getCaste();
    casteView.setSelectedObject(caste.getType());
    casteView.addObjectSelectionChangedListener(new IObjectValueChangedListener<ICasteType>() {
      public void valueChanged(ICasteType newValue) {
        caste.setType(newValue);
      }
    });
    caste.addChangeListener(new IChangeListener() {
      public void changeOccured() {
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