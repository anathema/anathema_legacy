package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.resources.BackgroundInternationalizer;
import net.sf.anathema.character.library.intvalue.IRemovableTraitView;
import net.sf.anathema.character.library.trait.presenter.TraitPresenter;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.background.IBackground;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;
import net.sf.anathema.character.model.background.IBackgroundListener;
import net.sf.anathema.character.presenter.util.I18nComparator;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.lib.collection.IdentityMapping;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.text.MessageFormat.format;

public class BackgroundPresenter implements IPresenter {

  private final IBackgroundConfiguration configuration;
  private final IBasicAdvantageView configurationView;
  private final IResources resources;
  private final IdentityMapping<IDefaultTrait, IRemovableTraitView<?>> viewsByBackground = new IdentityMapping<IDefaultTrait, IRemovableTraitView<?>>();
  private final IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry;
  private final Map<String, IBackgroundTemplate> templatesByDisplayName = new HashMap<String, IBackgroundTemplate>();
  private final BackgroundInternationalizer internationalizer;
  private String backgroundDescription = "";
  private Displayer displayer;

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
    this.internationalizer = new BackgroundInternationalizer(resources);
    this.configuration.addBackgroundListener(new IBackgroundListener() {
      public void backgroundAdded(IBackground background) {
        addBackgroundView(background);
      }

      public void backgroundRemoved(IBackground background) {
        removeBackgroundView(background);
      }
    });
    context.getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        allowRemoveCreationBackground(!experienced);
      }
    });
    this.displayer = new Displayer(internationalizer);
  }

  public void initPresentation() {
    Icon addIcon = new BasicUi(resources).getAddIcon();
    final IButtonControlledComboEditView<Object> view = configurationView.addBackgroundSelectionView(
            resources.getString("BackgroundConfigurationView.SelectionCombo.Label"), //$NON-NLS-1$
            new BackgroundListRenderer(displayer),
            new BackgroundBoxEditor(displayer),
            addIcon);
    view.addEditChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newBackgroundDescription) {
        backgroundDescription = newBackgroundDescription;
      }
    });
    view.setObjects(getSortedBackgrounds());
    view.addButtonListener(new IObjectValueChangedListener<Object>() {
      public void valueChanged(Object newValue) {
        IBackgroundTemplate backgroundType = getBackgroundType(newValue);
        String description = backgroundDescription.equals("") ? null : backgroundDescription;
        if (backgroundType == null) {
          configuration.addBackground(newValue.toString(), description);
        } else {
          configuration.addBackground(backgroundType, description);
        }
        view.clear();
      }
    });
    for (IBackgroundTemplate template : configuration.getAllAvailableBackgroundTemplates()) {
      templatesByDisplayName.put(internationalizer.getDisplayName(template), template);
    }
    configuration.initStartingBackgrounds();
  }

  private IBackgroundTemplate[] getSortedBackgrounds() {
    IBackgroundTemplate[] backgroundTemplates = configuration.getAllAvailableBackgroundTemplates();
    Arrays.sort(backgroundTemplates, new I18nComparator(resources, internationalizer.getPrefix()));
    return backgroundTemplates;
  }

  private synchronized void addBackgroundView(final IBackground background) {
    Icon deleteIcon = new BasicUi(resources).getRemoveIcon();
    String description = background.getDescription() != null ? format(" ({0})", background.getDescription()) : "";
    String backgroundString = background.getType().toString() + description;
    IRemovableTraitView<?> backgroundView = configurationView.addBackgroundView(deleteIcon, backgroundString,
            background.getCurrentValue(), background.getMaximalValue());
    new TraitPresenter(background, backgroundView).initPresentation();
    backgroundView.addButtonListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        configuration.removeBackground(background);
      }
    });
    if (background.getMinimalValue() > 0)
      backgroundView.setButtonEnabled(false);
    viewsByBackground.put(background, backgroundView);
  }

  private synchronized void removeBackgroundView(IDefaultTrait background) {
    IRemovableTraitView<?> view = viewsByBackground.get(background);
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
    return backgroundRegistry.getById(displayName);
  }

  public void allowRemoveCreationBackground(boolean allowed) {
    for (IDefaultTrait background : viewsByBackground.getAllKeys()) {
      if (background.getCalculationValue() > 0) {
        IRemovableTraitView<?> view = viewsByBackground.get(background);
        view.setButtonEnabled(allowed && background.getMinimalValue() == 0);
      }
    }
  }
}