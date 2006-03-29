package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.reporting.template.CharacterDescriptionReportTemplate;
import net.sf.anathema.character.generic.framework.reporting.template.MortalBasicsCharacterTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.SimpleBackgroundTemplate;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.resources.IResources;

public class MortalCharacterModule extends CharacterGenericsModuleAdapter {

  public static final String BACKGROUND_ID_RESOURCES = "Resources"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_MENTOR = "Mentor"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_INFLUENCE = "Influence"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_FOLLOWERS = "Followers"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_FAMILIAR = "Familiar"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_CONTACTS = "Contacts"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_BACKING = "Backing"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_ALLIES = "Allies"; //$NON-NLS-1$

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "HeroicMortal.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "Mortal.template"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "Mortal2nd.template"); //$NON-NLS-1$
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    generics.getReportTemplateRegistry().add(new CharacterDescriptionReportTemplate(resources));
    generics.getReportTemplateRegistry().add(new MortalBasicsCharacterTemplate(resources));
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> registry = generics.getBackgroundRegistry();
    registry.add(new SimpleBackgroundTemplate(BACKGROUND_ID_ALLIES));
    registry.add(new SimpleBackgroundTemplate(BACKGROUND_ID_BACKING));
    registry.add(new SimpleBackgroundTemplate(BACKGROUND_ID_CONTACTS));
    registry.add(new SimpleBackgroundTemplate(BACKGROUND_ID_FAMILIAR));
    registry.add(new SimpleBackgroundTemplate(BACKGROUND_ID_FOLLOWERS));
    registry.add(new SimpleBackgroundTemplate(BACKGROUND_ID_INFLUENCE));
    registry.add(new SimpleBackgroundTemplate(BACKGROUND_ID_MENTOR));
    registry.add(new SimpleBackgroundTemplate(BACKGROUND_ID_RESOURCES));
  }
}