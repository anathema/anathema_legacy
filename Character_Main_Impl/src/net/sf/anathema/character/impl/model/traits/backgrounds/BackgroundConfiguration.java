package net.sf.anathema.character.impl.model.traits.backgrounds;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
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
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.util.ObjectUtilities;
import org.jmock.example.announcer.Announcer;

import java.util.ArrayList;
import java.util.List;

public class BackgroundConfiguration implements IBackgroundConfiguration {

  private final List<IBackground> backgrounds = new ArrayList<IBackground>();
  private final Announcer<IBackgroundListener> listeners = Announcer.to(IBackgroundListener.class);
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

  @Override
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
  
  @Override
  public IBackgroundTemplate[] getAllAvailableBackgroundTemplates() {
    List<IBackgroundTemplate> backgroundList = new ArrayList<IBackgroundTemplate>();
    for (IBackgroundTemplate backgroundTemplate : backgroundRegistry.getAll()) {
      if (backgroundArbitrator.accepts(backgroundTemplate)) {
        backgroundList.add(backgroundTemplate);
      }
    }
    return backgroundList.toArray(new IBackgroundTemplate[backgroundList.size()]);
  }
  
  @Override
  public IBackground addBackground(String customBackgroundName, String description)
  {
	  return addBackground(customBackgroundName, description, false);
  }
  
  @Override
  public IBackground addBackground(IBackgroundTemplate backgroundType, String description)
  {
	  return addBackground(backgroundType, description, false);
  }

  @Override
  public IBackground addBackground(String customBackgroundName, String description, boolean loadIfExists) {
    Preconditions.checkNotNull(customBackgroundName);
    return addBackground(new CustomizedBackgroundTemplate(customBackgroundName), description, loadIfExists);
  }

  @Override
  public IBackground addBackground(final IBackgroundTemplate backgroundType, final String description, boolean loadIfExists) {
    Preconditions.checkNotNull(backgroundType);
    IBackground foundBackground = Iterables.find(backgrounds,new Predicate<IBackground>() {
      @Override
      public boolean apply(IBackground listBackground) {
        return ObjectUtilities.equals(backgroundType, listBackground.getType()) &&
                ((description == null && listBackground.getDescription() == null) ||
                        (description != null && description.equals(listBackground.getDescription())));
      }
    },null);
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

  @Override
  public IBackground[] getBackgrounds() {
    return backgrounds.toArray(new IBackground[backgrounds.size()]);
  }

  @Override
  public void addBackgroundListener(IBackgroundListener listener) {
    listeners.addListener(listener);
  }

  private void fireBackgroundAddedEvent(IBackground background) {
    listeners.announce().backgroundAdded(background);
  }

  @Override
  public void removeBackground(IBackground background) {
    backgrounds.remove(background);
    fireBackgroundRemovedEvent(background);
  }

  private void fireBackgroundRemovedEvent(IBackground background) {
    listeners.announce().backgroundRemoved(background);
  }

  @Override
  public IBackground getBackgroundByTemplate(IBackgroundTemplate type) {
    Preconditions.checkNotNull(type);
    for (IBackground background : getBackgrounds()) {
      if (type.equals(background.getType()) || type.getId().equals(background.getType().getId())) {
        return background;
      }
    }
    return null;
  }
}