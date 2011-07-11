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
import net.sf.anathema.character.library.trait.FriendlyValueChangeChecker;
import net.sf.anathema.character.library.trait.rules.TraitRules;
import net.sf.anathema.character.model.background.Background;
import net.sf.anathema.character.model.background.IBackground;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;
import net.sf.anathema.character.model.background.IBackgroundListener;
import net.sf.anathema.lib.collection.Predicate;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.registry.IIdentificateRegistry;

public class BackgroundConfiguration implements IBackgroundConfiguration {

  private final List<IBackground> backgrounds = new ArrayList<IBackground>();
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
	  List<IBackground> otherBackgroundsToInit = new ArrayList<IBackground>(backgrounds);
	  for (IBackgroundTemplate background : getAllAvailableBackgroundTemplates())
	  {
		  ITraitTemplate traitTemplate = traitTemplates.getTraitTemplate(background);
		  if (traitTemplate.getStartValue() > 0)
			  otherBackgroundsToInit.remove(addBackground(background, traitTemplate.getTag(), true));
	  }
	  //anything else should also be notified as going to the screen
	  for (IBackground background : otherBackgroundsToInit)
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
  
  public IBackground addBackground(String customBackgroundName, String description)
  {
	  return addBackground(customBackgroundName, description, false);
  }
  
  public IBackground addBackground(final IBackgroundTemplate backgroundType, String description)
  {
	  return addBackground(backgroundType, description, false);
  }

  public IBackground addBackground(String customBackgroundName, String description, boolean loadIfExists) {
    Ensure.ensureNotNull(customBackgroundName);
    return addBackground(new CustomizedBackgroundTemplate(customBackgroundName), description, loadIfExists);
  }

  public IBackground addBackground(final IBackgroundTemplate backgroundType, final String description, boolean loadIfExists) {
    Ensure.ensureNotNull(backgroundType);
    IBackground foundBackground = new Predicate<IBackground>() {
      public boolean evaluate(IBackground listBackground) {
        return ObjectUtilities.equals(backgroundType, listBackground.getType()) &&
        	((description == null && listBackground.getDescription() == null) ||
          	 (description != null && description.equals(listBackground.getDescription())));
      }
    }.find(backgrounds);
    if (foundBackground != null)
    {
    	if (loadIfExists && foundBackground != null)
    		fireBackgroundAddedEvent(foundBackground);
      return loadIfExists ? foundBackground : null;
    }
    ITraitTemplate traitTemplate = traitTemplates.getTraitTemplate(backgroundType);
    if (traitTemplate.getTag() != null && !traitTemplate.getTag().equals(description))
    	traitTemplate = traitTemplates.getDefaultTraitTemplate(backgroundType);
    TraitRules rules = new TraitRules(backgroundType, traitTemplate, context.getLimitationContext());
    IBackground background = new Background(description, rules, context, new FriendlyValueChangeChecker());
    backgrounds.add(background);
    fireBackgroundAddedEvent(background);
    return background;
  }

  public IBackground[] getBackgrounds() {
    return backgrounds.toArray(new IBackground[backgrounds.size()]);
  }

  public void addBackgroundListener(IBackgroundListener listener) {
    listeners.addListener(listener);
  }

  private void fireBackgroundAddedEvent(final IBackground background) {
    listeners.forAllDo(new IClosure<IBackgroundListener>() {
      public void execute(IBackgroundListener input) {
        input.backgroundAdded(background);
      }
    });
  }

  public void removeBackground(IBackground background) {
    backgrounds.remove(background);
    fireBackgroundRemovedEvent(background);
  }

  private void fireBackgroundRemovedEvent(final IBackground background) {
    listeners.forAllDo(new IClosure<IBackgroundListener>() {
      public void execute(IBackgroundListener input) {
        input.backgroundRemoved(background);
      }
    });
  }

  public IBackground getBackgroundByTemplate(IBackgroundTemplate type) {
    Ensure.ensureNotNull("Background type must not be null.", type); //$NON-NLS-1$
    for (IBackground background : getBackgrounds()) {
      if (type.equals(background.getType()) || type.getId().equals(background.getType().getId())) {
        return background;
      }
    }
    return null;
  }
}