package net.sf.anathema.character.presenter;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.presenter.AbstractTraitPresenter;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;
import net.sf.anathema.character.model.background.IBackgroundListener;
import net.sf.anathema.character.presenter.util.I18nComparator;
import net.sf.anathema.character.presenter.util.ProxyComboBoxEditor;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IObjectSelectionView;
import net.sf.anathema.lib.collection.IdentityMapping;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.resources.IResources;

public class BackgroundPresenter extends AbstractTraitPresenter implements IAdvantageSubPresenter {

  private static final String BACKGROUND_TYPE_RESOURCE_KEY_PREFIX = "BackgroundType.Name."; //$NON-NLS-1$
  private final IBackgroundConfiguration configuration;
  private final IBasicAdvantageView configurationView;
  private final IResources resources;
  private final IdentityMapping<ITrait, IRemovableTraitView> viewsByBackground = new IdentityMapping<ITrait, IRemovableTraitView>();
  private final IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry;
  private final Map<String, IBackgroundTemplate> templatesByDisplayName = new HashMap<String, IBackgroundTemplate>();

  public BackgroundPresenter(
      IResources resources,
      IBackgroundConfiguration configuration,
      ICharacterModelContext context,
      IBasicAdvantageView backgroundView,
      IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry) {
    this.resources = resources;
    this.configuration = configuration;
    this.configurationView = backgroundView;
    this.backgroundRegistry = backgroundRegistry;
    this.configuration.addBackgroundListener(new IBackgroundListener() {
      public void backgroundAdded(ITrait background) {
        addBackgroundView(background);
      }

      public void backgroundRemoved(ITrait background) {
        removeBackgroundView(background);
      }
    });
    context.getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        allowRemoveCreationBackground(!experienced);
      }
    });
  }

  private Object getDisplayObject(Object anObject) {
    if (anObject instanceof ITrait) {
      anObject = ((ITrait) anObject).getType();
    }
    if (anObject instanceof IBackgroundTemplate) {
      return getDisplayName((IBackgroundTemplate) anObject);
    }
    return anObject;
  }

  private String getDisplayName(IBackgroundTemplate template) {
    if (template instanceof CustomizedBackgroundTemplate) {
      return template.getId();
    }
    return resources.getString(BACKGROUND_TYPE_RESOURCE_KEY_PREFIX + template.getId());
  }

  public void init() {
    Icon addIcon = new BasicUi(resources).getMediumAddIcon();
    IObjectSelectionView view = configurationView.addBackgroundSelectionView(
        resources.getString("BackgroundConfigurationView.SelectionCombo.Label"), //$NON-NLS-1$
        getSortedBackgrounds(),
        new ProxyComboBoxEditor() {
          @Override
          public void setItem(Object anObject) {
            super.setItem(getDisplayObject(anObject));
          }
        },
        new DefaultListCellRenderer() {
          @Override
          public Component getListCellRendererComponent(
              JList list,
              Object value,
              int index,
              boolean isSelected,
              boolean cellHasFocus) {
            return super.getListCellRendererComponent(list, getDisplayObject(value), index, isSelected, cellHasFocus);
          }
        },
        addIcon);
    view.addObjectSelectionChangedListener(new IObjectValueChangedListener() {
      public void valueChanged(Object oldValue, Object newValue) {
        IBackgroundTemplate backgroundType = getBackgroundType(newValue);
        if (backgroundType == null) {
          configuration.addBackground(newValue.toString());
        }
        else {
          configuration.addBackground(backgroundType);
        }
      }
    });
    for (IBackgroundTemplate template : configuration.getAllAvailableBackgroundTemplates()) {
      templatesByDisplayName.put(getDisplayName(template), template);
    }
    for (ITrait background : configuration.getBackgrounds()) {
      addBackgroundView(background);
    }
  }

  private IBackgroundTemplate[] getSortedBackgrounds() {
    IBackgroundTemplate[] backgroundTemplates = configuration.getAllAvailableBackgroundTemplates();
    Arrays.sort(backgroundTemplates, new I18nComparator(resources, BACKGROUND_TYPE_RESOURCE_KEY_PREFIX));
    return backgroundTemplates;
  }

  private synchronized void addBackgroundView(final ITrait background) {
    Icon deleteIcon = new BasicUi(resources).getMediumRemoveIcon();
    IRemovableTraitView backgroundView = configurationView.addBackgroundView(
        deleteIcon,
        getDisplayObject(background).toString(),
        background.getCurrentValue(),
        background.getMaximalValue());
    addModelValueListener(background, backgroundView);
    addViewValueListener(backgroundView, background);
    backgroundView.addButtonListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        configuration.removeBackground(background);
      }
    });
    viewsByBackground.put(background, backgroundView);
  }

  private synchronized void removeBackgroundView(ITrait background) {
    IRemovableTraitView view = viewsByBackground.get(background);
    viewsByBackground.remove(background);
    view.delete();
  }

  private IBackgroundTemplate getBackgroundType(Object anObject) {
    if (anObject instanceof IBackgroundTemplate) {
      return (IBackgroundTemplate) anObject;
    }
    String displayName = anObject.toString();
    IBackgroundTemplate template = templatesByDisplayName.get(displayName);
    if (template != null) {
      return template;
    }
    return backgroundRegistry.getById(anObject.toString());
  }

  public void allowRemoveCreationBackground(boolean allowed) {
    for (ITrait background : viewsByBackground.getAllKeys()) {
      if (background.isCreationLearned()) {
        IRemovableTraitView view = viewsByBackground.get(background);
        view.setButtonEnabled(allowed);
      }
    }
  }
}