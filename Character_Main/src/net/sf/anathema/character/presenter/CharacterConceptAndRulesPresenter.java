package net.sf.anathema.character.presenter;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.text.JTextComponent;

import net.disy.commons.swing.ui.IObjectUi;
import net.disy.commons.swing.ui.ObjectUiListCellRenderer;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ITypedDescriptionType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.character.model.nature.INatureProvider;
import net.sf.anathema.character.model.nature.INatureType;
import net.sf.anathema.character.view.ICharacterConceptAndRulesViewFactory;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesViewProperties;
import net.sf.anathema.framework.presenter.view.IObjectSelectionView;
import net.sf.anathema.framework.view.renderer.IdentificateObjectUi;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.view.ILabelTextView;

public class CharacterConceptAndRulesPresenter {

  private final ICharacterConceptAndRulesView view;
  private final ICharacterStatistics statistics;
  private final INatureProvider natureProvider;
  private final IResources resources;

  public CharacterConceptAndRulesPresenter(
      ICharacterStatistics statstics,
      ICharacterConceptAndRulesViewFactory viewFactory,
      IResources resources,
      INatureProvider natureProvider) {
    this.statistics = statstics;
    this.view = viewFactory.createCharacterConceptView();
    this.resources = resources;
    this.natureProvider = natureProvider;
  }

  public TabContent[] init() {
    initRulesPresentation();
    initCastePresentation();
    initNaturePresentation();
    initConceptPresentation();
    initExperienceListening();
    initGui();
    return new TabContent[] { new TabContent(resources.getString("CardView.CharacterConcept.Title"), view) }; //$NON-NLS-1$
  }

  private void initExperienceListening() {
    statistics.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        view.setEnabled(!experienced);
      }
    });
    view.setEnabled(!statistics.isExperienced());
  }

  private void initConceptPresentation() {
    final ILabelTextView conceptView = view.addLabelTextView(resources.getString("Label.Concept")); //$NON-NLS-1$
    final ISimpleTextualDescription concept = statistics.getCharacterConcept().getConcept();
    conceptView.addTextChangedListener(new IStringValueChangedListener() {
      public void valueChanged(String newValue) {
        concept.setText(newValue);
      }
    });
    concept.addTextChangedListener(new IStringValueChangedListener() {
      public void valueChanged(String newValue) {
        conceptView.setText(newValue);
      }
    });
    conceptView.setText(concept.getText());
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

  private void initNaturePresentation() {
    INatureType[] natures = natureProvider.getAllSorted();
    final IObjectSelectionView natureView = view.addConceptObjectSelectionView(resources.getString("Label.Nature"), //$NON-NLS-1$
        natures,
        new DefaultListCellRenderer() {

          @Override
          public java.awt.Component getListCellRendererComponent(
              javax.swing.JList list,
              Object value,
              int index,
              boolean isSelected,
              boolean cellHasFocus) {
            INatureType valueIdentificate = (INatureType) value;
            Component component;
            if (value == null) {
              component = super.getListCellRendererComponent(
                  list,
                  resources.getString("ComboBox.SelectLabel"), index, isSelected, cellHasFocus); //$NON-NLS-1$
            }
            else {
              component = super.getListCellRendererComponent(
                  list,
                  valueIdentificate.getName(),
                  index,
                  isSelected,
                  cellHasFocus);
            }
            return component;
          }
        },
        false);
    final ITypedDescription<INatureType> nature = statistics.getCharacterConcept().getNature();
    natureView.setSelectedObject(nature.getType());
    final JTextComponent willpowerConditionLabel = view.addWillpowerConditionView(resources.getString("CharacterConcept.GainWillpower")); //$NON-NLS-1$
    natureView.addObjectSelectionChangedListener(new IObjectValueChangedListener() {
      public void valueChanged(Object oldValue, Object newValue) {
        if (newValue instanceof INatureType) {
          nature.setType((INatureType) newValue);
        }
      }
    });
    nature.addTypeListener(new IObjectValueChangedListener<ITypedDescriptionType>() {
      public void valueChanged(ITypedDescriptionType oldValue, ITypedDescriptionType newValue) {
        updateNature(natureView, willpowerConditionLabel, (INatureType) newValue);
      }
    });
    updateNature(natureView, willpowerConditionLabel, statistics.getCharacterConcept().getNature().getType());
  }

  private void initRulesPresentation() {
    view.addRulesLabel(resources.getString("CharacterType.TextLabel.Intro") + " " + resources.getString(statistics.getCharacterTemplate().getPresentationProperties().getNewActionResource()) + "."); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
    view.addRulesLabel(resources.getString("Ruleset.TextLabel.UseIntro") + " " + resources.getString("Ruleset." + statistics.getRules().getId()) + "."); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$    
  }

  private void updateNature(
      final IObjectSelectionView natureView,
      final JTextComponent willpowerConditionLabel,
      INatureType natureType) {
    natureView.setSelectedObject(natureType);
    if (natureType == null) {
      willpowerConditionLabel.setText(null);
      return;
    }
    String willpowerCondition = natureType.getWillpowerCondition();
    if (willpowerCondition == null) {
      willpowerCondition = resources.getString("CharacterConcept.WillpowerCondition.NotSpecified"); //$NON-NLS-1$
    }
    willpowerConditionLabel.setText(willpowerCondition);
  }

  private void initCastePresentation() {
    final ICharacterTemplate template = statistics.getCharacterTemplate();
    if (template.getCasteCollection().getAllCasteTypes().length <= 0) {
      return;
    }
    String casteResourceBase = template.getPresentationProperties().getCasteResourceBase();
    String casteLabelResourceKey = template.getPresentationProperties().getCasteLabelResource();
    IObjectUi casteUi = new IdentificateObjectUi(resources, casteResourceBase) {
      @Override
      protected Icon getNonNullIcon(IIdentificate value) {
        CharacterType characterType = template.getTemplateType().getCharacterType();
        return getResources().getImageIcon(characterType.getId() + "Button" + value.getId() + "16.png"); //$NON-NLS-1$ //$NON-NLS-2$
      }
    };
    final IObjectSelectionView casteView = view.addConceptObjectSelectionView(
        resources.getString(casteLabelResourceKey),
        template.getCasteCollection().getAllCasteTypes(),
        new ObjectUiListCellRenderer(casteUi),
        false);
    final ITypedDescription<ICasteType> caste = statistics.getCharacterConcept().getCaste();
    casteView.setSelectedObject(caste.getType());
    casteView.addObjectSelectionChangedListener(new IObjectValueChangedListener() {
      public void valueChanged(Object oldValue, Object newValue) {
        caste.setType((ICasteType) newValue);
      }
    });
    caste.addTypeListener(new IObjectValueChangedListener<ITypedDescriptionType>() {
      public void valueChanged(ITypedDescriptionType oldValue, ITypedDescriptionType newValue) {
        casteView.setSelectedObject(newValue);
      }
    });
  }
}