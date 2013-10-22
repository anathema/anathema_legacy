package net.sf.anathema.charmdatabase.presenter;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import net.sf.anathema.character.main.magic.charm.duration.Duration;
import net.sf.anathema.character.main.magic.charm.duration.QualifiedAmountDuration;
import net.sf.anathema.character.main.magic.charm.duration.SimpleDuration;
import net.sf.anathema.character.main.magic.charm.type.CharmType;
import net.sf.anathema.charmdatabase.management.ICharmDatabaseManagement;
import net.sf.anathema.charmdatabase.management.filters.CharmNameFilter;
import net.sf.anathema.charmdatabase.management.model.ICharmEditModel;
import net.sf.anathema.charmdatabase.tools.NewCharmAction;
import net.sf.anathema.charmdatabase.view.CharmBasicsPanel;
import net.sf.anathema.charmdatabase.view.CharmDatabaseView;
import net.sf.anathema.charmdatabase.view.info.CharmInformationPanel;
import net.sf.anathema.charmdatabase.view.info.CharmSourcePanel;
import net.sf.anathema.charmdatabase.view.rules.CharmCostsPanel;
import net.sf.anathema.charmdatabase.view.rules.CharmKeywordsPanel;
import net.sf.anathema.charmdatabase.view.rules.CharmPrerequisitesPanel;
import net.sf.anathema.charmdatabase.view.rules.CharmRulesPanel;
import net.sf.anathema.charmdatabase.view.rules.CharmTraitMinimumsPanel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class CharmDatabasePresenter implements Presenter {
  private final Resources resources;
  private final CharmDatabaseView view;
  private final ICharmDatabaseManagement model;

  public CharmDatabasePresenter(Resources resources, ICharmDatabaseManagement model,
                                    CharmDatabaseView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
  }

  @Override
  public void initPresentation() {
    new CharmListPresenter(model, view).initPresentation();
    addEditTemplateActions();
    initCharmListFilterView();
    initBasicDetailsView();
    initRulesDetailsView();
    initInformationDetailsView();
    Platform.runLater(new Runnable() {
		@Override
		public void run() {
			model.getCharmEditModel().setEditCharm(model.getFilteredCharms()[0]);	
		}
    });
  }
  
  private void addEditTemplateActions() {
	if (ICharmEditModel.EDIT_ENABLED) {
		new NewCharmAction(resources, model).addToolTo(view);
	    /*new SaveEquipmentTemplateAction(resources, model).addToolTo(view);
	    new CopyEquipmentTemplateAction(resources, model).addToolTo(view);
	    new RemoveEquipmentTemplateAction(resources, model).addToolTo(view);*/	
	}
  }
  
  private void initCharmListFilterView() {
	  final ITextView filterView = view.addTextualFilter(resources.getString("Charms.Filter"));
	  final CharmNameFilter filter = new CharmNameFilter(new MagicDisplayLabeler(resources));
	  model.addFilter(filter);
	  filterView.addTextChangedListener(new ObjectValueListener<String>() {

		@Override
		public void valueChanged(String newValue) {
			filter.setCurrentText(newValue);
			model.notifyFiltersUpdated();
		}
		  
	  });
  }

  private void initBasicDetailsView() {
	  
    CharmBasicsPanel basicsPanel = view.addBasicsPanel(resources.getString("Charms.Creation.Basics"));
    final ITextView nameView = basicsPanel.addNameView(resources.getString("Charms.Creation.Basics.Name"));
    new TextualPresentation().initView(nameView, model.getCharmEditModel().getName());
    
    final ObjectSelectionView<Identifier> typeView = basicsPanel.addTypeView(resources.getString("Charms.Creation.Basics.CharmType"),
    		new IdentifierUi(resources));
    typeView.setObjects(model.getCharmTypes());
    final ObjectSelectionView<Identifier> groupView = basicsPanel.addGroupView(resources.getString("Charms.Creation.Basics.GroupType"),
    		new IdentifierUi(resources));
    final ObjectSelectionView<Identifier> traitView = basicsPanel.addTraitView(resources.getString("Charms.Creation.Basics.TraitType"),
    		new IdentifierUi(resources));
    model.getCharmEditModel().addCharmTypeChangedListening(new ChangeListener() {

		@Override
		public void changeOccurred() {
			typeView.setSelectedObject(new SimpleIdentifier(model.getCharmEditModel().getCharmType().getId()));
			groupView.setObjects(model.getGroupsForCharmType(model.getCharmEditModel().getCharmType()));
			groupView.setSelectedObject(model.getCharmEditModel().getCharmGroup());
			Identifier[] traitsForType = model.getTraitsForCharmType(model.getCharmEditModel().getCharmType()); 
			traitView.setObjects(traitsForType);
			if (traitsForType.length > 0) {
				traitView.setSelectedObject(model.getCharmEditModel().getCharmPrimaryTraitType());
				traitView.setEnabled(true);
			} else {
				traitView.setEnabled(false);
			}
		}
    });
    typeView.addObjectSelectionChangedListener(new ObjectValueListener<Identifier>() {
        @Override
        public void valueChanged(Identifier newValue) {
          model.getCharmEditModel().setCharmType(newValue);
        }
      });
    model.getCharmEditModel().addCharmGroupChangedListening(new ChangeListener() {
		@Override
		public void changeOccurred() {
			groupView.setSelectedObject(model.getCharmEditModel().getCharmGroup());
		}
    });
    model.getCharmEditModel().addCharmPrimaryTraitTypeChangedListening(new ChangeListener() {

		@Override
		public void changeOccurred() {
			traitView.setSelectedObject(model.getCharmEditModel().getCharmPrimaryTraitType());
		}
    	
    });
    groupView.addObjectSelectionChangedListener(new ObjectValueListener<Identifier>() {

		@Override
		public void valueChanged(Identifier newValue) {
			model.getCharmEditModel().setCharmGroup(newValue);
		}
    });
    traitView.addObjectSelectionChangedListener(new ObjectValueListener<Identifier>() {

		@Override
		public void valueChanged(Identifier newValue) {
			model.getCharmEditModel().setCharmPrimaryTraitType(newValue);
		}
    });
        
    model.getCharmEditModel().addCanonCharmSelectionListening(new ChangeListener() {

		@Override
		public void changeOccurred() {
			nameView.setEnabled(false);
			typeView.setEnabled(false);
			groupView.setEnabled(false);
			traitView.setEnabled(false);
		}
    	
    });
    model.getCharmEditModel().addCustomCharmSelectionListening(new ChangeListener() {

		@Override
		public void changeOccurred() {
			nameView.setEnabled(true);
			typeView.setEnabled(true);
			groupView.setEnabled(true);
			traitView.setEnabled(true);
		}
    	
    });
  }
  
  private void initRulesDetailsView() {
	  CharmRulesPanel rulesPanel = view.addRulesPanel(resources.getString("Charms.Creation.Rules"));
	    final CharmPrerequisitesPanel prerequisitesPanel = rulesPanel.addPrerequisitesPanel(resources.getString("Charms.Creation.Rules.Prerequisites"));
	    final CharmTraitMinimumsPanel traitsPanel = rulesPanel.addTraitsPanel(resources.getString("Charms.Creation.Rules.Traits"));
	    final CharmCostsPanel costsPanel = rulesPanel.addCostsPanel(resources.getString("Charms.Creation.Rules.Costs"));
	    final CharmKeywordsPanel keywordsPanel = rulesPanel.addKeywordsPanel(resources.getString("Charms.Creation.Rules.Keywords"));
	    final ObjectSelectionView<CharmType> actionTypeView = rulesPanel.addTypeView(resources.getString("Charms.Creation.Rules.Type"), new CharmTypeUi(resources));
	    final ObjectSelectionView<Duration> durationView = rulesPanel.addDurationView(resources.getString("Charms.Creation.Rules.Duration"), new DurationUi(resources));
	    actionTypeView.setObjects(CharmType.values());
	    durationView.setObjects(getDurations());
	    
	    model.getCharmEditModel().addCharmPrerequisitesChangedListening(new ChangeListener() {
			@Override
			public void changeOccurred() {
				prerequisitesPanel.setPrerequisites(model.getCharmEditModel().getCharmPrerequisites());
			}
	    });
	    
	    model.getCharmEditModel().addCharmTraitMinimumsChangedListening(new ChangeListener() {

			@Override
			public void changeOccurred() {
				traitsPanel.setTraits(model.getCharmEditModel().getCharmTraitMinimums());
			}
	    	
	    });
	    
	    model.getCharmEditModel().addCharmTemporaryCostsChangedListening(new ChangeListener() {

			@Override
			public void changeOccurred() {
				costsPanel.setCosts(model.getCharmEditModel().getCharmTemporaryCosts());
			}
	    });
	    
	    model.getCharmEditModel().addCharmKeywordsChangedListening(new ChangeListener() {
			@Override
			public void changeOccurred() {
				keywordsPanel.setKeywords(model.getCharmEditModel().getCharmKeywords());
			}
	    });
	    
	    model.getCharmEditModel().addCharmActionTypeChangedListening(new ChangeListener() {

			@Override
			public void changeOccurred() {
				actionTypeView.setSelectedObject(model.getCharmEditModel().getCharmActionType().getCharmType());
			}
	    	
	    });
	    
	    model.getCharmEditModel().addCharmDurationTypeChangedListening(new ChangeListener() {

			@Override
			public void changeOccurred() {
				// TODO: We will require some simple means of handling non-standard durations
				durationView.setObjects(new Duration[] { model.getCharmEditModel().getCharmDuration() });
				durationView.setSelectedObject(model.getCharmEditModel().getCharmDuration());
			}
	    	
	    });
  
	    model.getCharmEditModel().addCanonCharmSelectionListening(new ChangeListener() {

			@Override
			public void changeOccurred() {
				actionTypeView.setEnabled(false);
				durationView.setEnabled(false);
			}
	    	
	    });
	    model.getCharmEditModel().addCustomCharmSelectionListening(new ChangeListener() {

			@Override
			public void changeOccurred() {
				actionTypeView.setEnabled(true);
				durationView.setEnabled(true);
			}
	    	
	    });
  }
  
  private Duration[] getDurations() {
	  // TODO: There should be a list for these common values somewhere, as there is for types
	  List<Duration> durations = new ArrayList<>();
	  durations.add(SimpleDuration.INSTANT_DURATION);
	  durations.add(SimpleDuration.PERMANENT_DURATION);
	  durations.add(new QualifiedAmountDuration("1", "scene"));
	  return durations.toArray(new Duration[0]);
  }
  
  private void initInformationDetailsView() {
	  CharmInformationPanel infoPanel = view.addInformationPanel(resources.getString("Charms.Creation.Information"));
	  final ITextView descriptionView = infoPanel.addDescriptionView(resources.getString("Charms.Creation.Information.Description"));
	  new TextualPresentation().initView(descriptionView, model.getCharmEditModel().getDescription());
	  final CharmSourcePanel sourcesPanel = infoPanel.addSourcePanel(resources.getString("Charms.Creation.Information.Sources"));

	  model.getCharmEditModel().addCharmSourcesChangedListening(new ChangeListener() {

		@Override
		public void changeOccurred() {
			sourcesPanel.setSources(model.getCharmEditModel().getCharmSources(), 
					model.getCharmEditModel().getCharmType(),
					model.getCharmEditModel().getName().getText());
		}
		  
	  });
	  
	  model.getCharmEditModel().addCanonCharmSelectionListening(new ChangeListener() {

		@Override
		public void changeOccurred() {
			descriptionView.setEnabled(false);
		}
	  });
	  
	  model.getCharmEditModel().addCustomCharmSelectionListening(new ChangeListener() {

		@Override
		public void changeOccurred() {
			descriptionView.setEnabled(true);
		}
	  });
  }
}