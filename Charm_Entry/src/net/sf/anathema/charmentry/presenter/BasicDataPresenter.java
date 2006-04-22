package net.sf.anathema.charmentry.presenter;

import java.awt.Component;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JList;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.framework.magic.compare.MagicComparator;
import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmType;
import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.magic.charms.DurationType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.ValuedTraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.library.intvalue.IntValueDisplayFactory;
import net.sf.anathema.charmentry.model.CharmEntryModel;
import net.sf.anathema.charmentry.model.IConfigurableCharmData;
import net.sf.anathema.charmentry.view.BasicDataView;
import net.sf.anathema.charmentry.view.ICostEntryView;
import net.sf.anathema.charmentry.view.ISourceSelectionView;
import net.sf.anathema.charmentry.view.ReflexiveCharmSpecialsView;
import net.sf.anathema.charmentry.view.SimpleCharmSpecialsView;
import net.sf.anathema.framework.presenter.view.IObjectSelectionView;
import net.sf.anathema.framework.presenter.view.IdentificateListCellRenderer;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.lib.workflow.container.ISelectionContainerView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public class BasicDataPresenter implements ICharmEntrySubPresenter {
  // TODO: Various exceptions long for handling

  private final BasicDataView view;
  private final IResources resources;
  private final CharmEntryModel model;
  private IntValueDisplayFactory intValueDisplayFactory;
  private final Map<String, String> nameMap = new HashMap<String, String>();
  private ISelectionContainerView prerequisiteCharmView;

  public BasicDataPresenter(CharmEntryModel model, BasicDataView view, IResources resources) {
    this.model = model;
    this.view = view;
    this.resources = resources;
    this.intValueDisplayFactory = new IntValueDisplayFactory(
        resources,
        resources.getImageIcon(IIconConstants.SOLAR_BALL));
  }

  public void initPresentation() {
    initCharmNamePresentation();
    ITextView groupView = initCharmGroupPresentation();
    IObjectSelectionView characterTypeView = initCharacterTypeView();
    initEditionPresentation();
    initCharmTypePresentation();
    initSecondEditionTypeDependentSpecialsPresentation();
    initDurationPresentation();
    initCostPresentation();
    ISelectableTraitView primaryPrerequisiteView = initPrimaryPrerequisitePresentation();
    this.prerequisiteCharmView = initPrerequisiteCharmPresentation();
    initCharacterTypeListening(characterTypeView, primaryPrerequisiteView, groupView);
    initEssencePrerequisitePresentation();
    initSourcePresentation();
  }

  private void initSecondEditionTypeDependentSpecialsPresentation() {
    final SimpleCharmSpecialsView subView = view.addSimpleCharmSpecialsView();
    final ReflexiveCharmSpecialsView subView2 = view.addReflexiveCharmSpecialsView();
    model.addAvailableSpecialsListener(new IChangeListener() {
      public void changeOccured() {
        subView.setEnabled(model.isSimpleSpecialsAvailable());
        subView2.setEnabled(model.isReflexiveSpecialsAvailable());
      }
    });
    subView.addSpeedValueChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        model.setSimpleCharmSpeedValue(newValue);
      }
    });
    subView.addDefenseValueChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        model.setSimpleCharmDefenseValue(newValue);
      }
    });
  }

  private void initEditionPresentation() {
    IObjectSelectionView typeBox = view.addObjectSelectionView(resources.getString("CharmEntry.ExaltedEdition"), //$NON-NLS-1$
        false,
        new IdentificateSelectCellRenderer("", resources)); //$NON-NLS-1$
    typeBox.setObjects(ExaltedEdition.values());
    typeBox.addObjectSelectionChangedListener(new IObjectValueChangedListener<ExaltedEdition>() {
      public void valueChanged(ExaltedEdition newValue) {
        model.setEdition(newValue);
      }
    });
  }

  private ITextView initCharmGroupPresentation() {
    final ITextView nameView = view.addSingleLineTextView(resources.getString("CharmEntry.CharmGroupId")); //$NON-NLS-1$
    nameView.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        model.setCharmGroup(newValue);
      }
    });
    model.addCharmGroupIdListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        nameView.setText(newValue);
      }
    });
    return nameView;
  }

  private ISelectionContainerView initPrerequisiteCharmPresentation() {
    final ISelectionContainerView prerequisiteView = view.addPrerequisiteCharmView(new IdentificateListCellRenderer(
        resources) {
      @Override
      public Component getListCellRendererComponent(
          JList list,
          Object value,
          int index,
          boolean isSelected,
          boolean cellHasFocus) {
        IIdentificate identificate = (IIdentificate) value;
        String name = nameMap.get(identificate.getId());
        if (name == null) {
          return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
        return super.getListCellRendererComponent(list, name, index, isSelected, cellHasFocus);
      }
    }

    );
    prerequisiteView.addSelectionChangeListener(new IChangeListener() {
      public void changeOccured() {
        Object[] selectedValues = prerequisiteView.getSelectedValues();
        ICharm[] charms = new ICharm[selectedValues.length];
        net.sf.anathema.lib.lang.ArrayUtilities.copyAll(selectedValues, charms);
        model.setPrerequisiteCharms(charms);
      }
    });
    return prerequisiteView;
  }

  private void initCostPresentation() {
    ICostEntryView essenceCostView = view.addCostView(resources.getString("CharmEntry.EssenceCost"), //$NON-NLS-1$
        resources.getString("CharmEntry.CostValue"), //$NON-NLS-1$
        resources.getString("CharmEntry.CostText")); //$NON-NLS-1$
    essenceCostView.getValueView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        model.setEssenceCostValue(newValue);
      }
    });
    essenceCostView.getTextView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        model.setEssenceCostText(newValue);
      }
    });
    ICostEntryView willpowerCostView = view.addCostView(resources.getString("CharmEntry.WillpowerCost"), //$NON-NLS-1$
        resources.getString("CharmEntry.CostValue"), //$NON-NLS-1$
        resources.getString("CharmEntry.CostText")); //$NON-NLS-1$
    willpowerCostView.getValueView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        model.setWillpowerCostValue(newValue);
      }
    });
    willpowerCostView.getTextView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        model.setWillpowerCostText(newValue);
      }
    });
    ICostEntryView healthCostView = view.addCostView(resources.getString("CharmEntry.HealthCost"), //$NON-NLS-1$
        resources.getString("CharmEntry.CostValue"), //$NON-NLS-1$
        resources.getString("CharmEntry.CostText")); //$NON-NLS-1$
    healthCostView.getValueView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        model.setHealthCostValue(newValue);
      }
    });
    healthCostView.getTextView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        model.setHealthCostText(newValue);
      }
    });
    ICostEntryView xpCostView = view.addCostView(resources.getString("CharmEntry.XPCost"), //$NON-NLS-1$
        resources.getString("CharmEntry.CostValue"), //$NON-NLS-1$
        resources.getString("CharmEntry.CostText")); //$NON-NLS-1$
    xpCostView.getValueView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        model.setXpCostValue(newValue);
      }
    });
    xpCostView.getTextView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        model.setXpCostText(newValue);
      }
    });
  }

  private void initSourcePresentation() {
    final ISourceSelectionView sourceView = view.addSourceView(resources.getString("CharmEntry.Source"), //$NON-NLS-1$
        resources.getString("CharmEntry.Book"), //$NON-NLS-1$
        resources.getString("CharmEntry.Page"), //$NON-NLS-1$
        new IIdentificate[] { new Identificate("Custom") }); //$NON-NLS-1$
    sourceView.addSourceChangeListener(new IObjectValueChangedListener() {
      public void valueChanged(Object newValue) {
        String value;
        if (newValue instanceof IIdentificate) {
          value = ((IIdentificate) newValue).getId();
        }
        else {
          value = newValue.toString();
        }
        sourceView.setPageSelectionEnabled(!(StringUtilities.isNullOrEmpty(value) || value.equalsIgnoreCase("Custom"))); //$NON-NLS-1$
        model.setSourceBook(value);
      }
    });
    sourceView.addPageChangeListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        model.setSourcePage(newValue);
      }
    });
    sourceView.setPageSelectionEnabled(false);
  }

  private void initCharacterTypeListening(
      IObjectSelectionView characterTypeView,
      final ISelectableTraitView primaryPrerequisiteView,
      final ITextView groupView) {
    characterTypeView.addObjectSelectionChangedListener(new IObjectValueChangedListener<CharacterType>() {
      public void valueChanged(CharacterType newValue) {
        model.setCharacterType(newValue);
        primaryPrerequisiteView.setSelectableTraits(model.getLegalPrimaryPrerequisiteTypes());
      }
    });
    characterTypeView.addObjectSelectionChangedListener(new IObjectValueChangedListener<CharacterType>() {
      public void valueChanged(CharacterType newValue) {
        try {
          setCharmsInView(prerequisiteCharmView, newValue);
        }
        catch (PersistenceException e) {
          // Todo Error Handling
        }
      }
    });
    characterTypeView.addObjectSelectionChangedListener(new IObjectValueChangedListener<CharacterType>() {
      public void valueChanged(CharacterType newValue) {
        groupView.setEnabled(newValue == CharacterType.LUNAR);
      }
    });
  }

  private void setCharmsInView(final ISelectionContainerView prerequisiteCharmView, CharacterType newValue)
      throws PersistenceException {
    ICharm[] charms = CharmCache.getInstance().getCharms(newValue, false);
    Arrays.sort(charms, new MagicComparator(newValue));
    prerequisiteCharmView.populate(charms);
  }

  private ISelectableTraitView initPrimaryPrerequisitePresentation() {
    final ISelectableTraitView traitView = view.addPrimaryPrerequisiteSelectionView(
        resources.getString("CharmEntry.PrimaryPrerequisite"), //$NON-NLS-1$
        intValueDisplayFactory);
    traitView.addTraitSelectionListener(new ITraitSelectionChangedListener() {
      public void selectionChanged(Object type, int value) {
        model.setPrimaryPrerequisite(new ValuedTraitType((ITraitType) type, value));
      }
    });
    model.addPrimaryPrerequisiteListener(new ITraitSelectionChangedListener() {
      public void selectionChanged(Object object, int value) {
        traitView.setSelectedTrait(object);
        traitView.setValue(value);
      }
    });
    traitView.setSelectableTraits(model.getLegalPrimaryPrerequisiteTypes());
    traitView.setValue(1);
    return traitView;
  }

  private void initEssencePrerequisitePresentation() {
    final IIntValueView traitView = view.addEssencePrerequisiteView(
        resources.getString("CharmEntry.EssencePrerequisite"), //$NON-NLS-1$
        resources.getString(OtherTraitType.Essence.getId()),
        intValueDisplayFactory,
        1,
        EssenceTemplate.SYSTEM_ESSENCE_MAX);
    traitView.addIntValueChangedListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        model.setPrerequisite(new ValuedTraitType(OtherTraitType.Essence, newValue));
      }
    });
    model.addEssencePrerequisiteListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        traitView.setValue(newValue);
      }
    });
    traitView.setValue(1);
  }

  private void initDurationPresentation() {
    IObjectSelectionView box = view.addObjectSelectionView(resources.getString("CharmEntry.Duration"), //$NON-NLS-1$
        true,
        new IdentificateSelectCellRenderer("", resources)); //$NON-NLS-1$
    box.addObjectSelectionChangedListener(new IObjectValueChangedListener() {
      public void valueChanged(Object newValue) {
        String value;
        if (newValue instanceof IIdentificate) {
          value = ((IIdentificate) newValue).getId();
        }
        else {
          value = newValue.toString();
        }
        model.setDuration(Duration.getDuration(value));
      }
    });
    box.setObjects(new IIdentificate[] { DurationType.Instant });
  }

  private void initCharmTypePresentation() {
    IObjectSelectionView typeBox = view.addObjectSelectionView(resources.getString("CharmEntry.CharmType"), //$NON-NLS-1$
        false,
        new IdentificateSelectCellRenderer("", resources)); //$NON-NLS-1$
    typeBox.addObjectSelectionChangedListener(new IObjectValueChangedListener<CharmType>() {
      public void valueChanged(CharmType newValue) {
        model.setCharmType(newValue);
      }
    });
    typeBox.setObjects(CharmType.values());
  }

  private void initCharmNamePresentation() {
    ITextView nameView = view.addSingleLineTextView(resources.getString("CharmEntry.CharmName")); //$NON-NLS-1$
    nameView.addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        model.setCharmName(newValue);
      }
    });
  }

  private IObjectSelectionView initCharacterTypeView() {
    IObjectSelectionView typeBox = view.addObjectSelectionView(resources.getString("CharmEntry.CharacterType"), //$NON-NLS-1$
        false,
        new IdentificateSelectCellRenderer("", resources)); //$NON-NLS-1$
    typeBox.setObjects(model.getLegalCharacterTypes());
    return typeBox;
  }

  public void charmAdded(IConfigurableCharmData charmData) throws PersistenceException {
    nameMap.put(charmData.getId(), charmData.getName());
    setCharmsInView(prerequisiteCharmView, charmData.getCharacterType());
  }
}