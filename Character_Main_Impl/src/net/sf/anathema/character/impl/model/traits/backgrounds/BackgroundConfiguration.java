package net.sf.anathema.character.impl.model.traits.backgrounds;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.backgrounds.CustomizedBackgroundTemplate;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;
import net.sf.anathema.character.model.background.IBackgroundListener;
import net.sf.anathema.lib.collection.Predicate;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.registry.IIdentificateRegistry;

public class BackgroundConfiguration implements IBackgroundConfiguration {

  private final List<IDefaultTrait> backgrounds = new ArrayList<IDefaultTrait>();
  private final GenericControl<IBackgroundListener> listeners = new GenericControl<IBackgroundListener>();
  private final IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry;
  private final ITraitTemplateCollection traitTemplates;
  private final ITraitContext context;
  private final IBackgroundArbitrator backgroundArbitrator;

  public BackgroundConfiguration(IBackgroundArbitrator arbitrator,
      ITraitTemplateCollection traitTemplates,
      ITraitContext context,
      IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry) {
    backgroundArbitrator = arbitrator;
    this.context = context;
    this.backgroundRegistry = backgroundRegistry;
    this.traitTemplates = traitTemplates;
  }

  public void initStartingBackgrounds()
  {
	  //load starting backgrounds from template
	  List<IDefaultTrait> otherBackgroundsToInit = new ArrayList<IDefaultTrait>(backgrounds);
	  for (IBackgroundTemplate background : getAllAvailableBackgroundTemplates())
	  {
		  ITraitTemplate traitTemplate = traitTemplates.getTraitTemplate(background);
		  if (traitTemplate.getStartValue() > 0)
			  otherBackgroundsToInit.remove(addBackground(background, true));
	  }
	  //anything else should also be notified as going to the screen
	  for (IDefaultTrait background : otherBackgroundsToInit)
	  	  fireBackgroundAddedEvent(background);
  }
  
  public IBackgroundTemplate[] getAllAvailableBackgroundTemplates() {
    List<IBackgroundTemplate> backgroundList = new ArrayList<IBackgroundTemplate>();
    for (IBackgroundTemplate backgroundTemplate : backgroundRegistry.getAll()) {
      if (backgroundArbitrator.accepts(backgroundTemplate)) {
        backgroundList.add(backgroundTemplate);
      }
    }
    return backgroundList.toArray(new IBackgroundTemplate[backgroundList.size()]);
  }
  
  public IDefaultTrait addBackground(String customBackgroundName)
  {
	  return addBackground(customBackgroundName, false);
  }
  
  public IDefaultTrait addBackground(final IBackgroundTemplate backgroundType)
  {
	  return addBackground(backgroundType, false);
  }

  public IDefaultTrait addBackground(String customBackgroundName, boolean loadIfExists) {
    Ensure.ensureNotNull(customBackgroundName);
    return addBackground(new CustomizedBackgroundTemplate(customBackgroundName), loadIfExists);
  }

  public IDefaultTrait addBackground(final IBackgroundTemplate backgroundType, boolean loadIfExists) {
    Ensure.ensureNotNull(backgroundType);
    IDefaultTrait foundBackground = new Predicate<IDefaultTrait>() {
      public boolean evaluate(IDefaultTrait listBackground) {
        return ObjectUtilities.equals(backgroundType, listBackground.getType());
      }
    }.find(backgrounds);
    if (foundBackground != null)
    {
    	if (loadIfExists && foundBackground != null)
    		fireBackgroundAddedEvent(foundBackground);
      return loadIfExists ? foundBackground : null;
    }
    ITraitTemplate traitTemplate = traitTemplates.getTraitTemplate(backgroundType);
    TraitRules rules = new TraitRules(backgroundType, traitTemplate, context.getLimitationContext());
    IDefaultTrait background = new DefaultTrait(rules, context, new FriendlyValueChangeChecker());
    backgrounds.add(background);
    fireBackgroundAddedEvent(background);
    return background;
  }

  public IDefaultTrait[] getBackgrounds() {
    return backgrounds.toArray(new IDefaultTrait[backgrounds.size()]);
  }

  public void addBackgroundListener(IBackgroundListener listener) {
    listeners.addListener(listener);
  }

  private void fireBackgroundAddedEvent(final IDefaultTrait background) {
    listeners.forAllDo(new IClosure<IBackgroundListener>() {
      public void execute(IBackgroundListener input) {
        input.backgroundAdded(background);
      }
    });
  }

  public void removeBackground(IDefaultTrait background) {
    backgrounds.remove(background);
    fireBackgroundRemovedEvent(background);
  }

  private void fireBackgroundRemovedEvent(final IDefaultTrait background) {
    listeners.forAllDo(new IClosure<IBackgroundListener>() {
      public void execute(IBackgroundListener input) {
        input.backgroundRemoved(background);
      }
    });
  }

  public IDefaultTrait getBackgroundByTemplate(IBackgroundTemplate type) {
    Ensure.ensureNotNull("Background type must not be null.", type); //$NON-NLS-1$
    for (IDefaultTrait background : getBackgrounds()) {
      if (type.equals(background.getType())) {
        return background;
      }
    }
    return null;
  }
}