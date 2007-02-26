package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.unsupported.UnsupportedDragonBloodedSecondTemplate;
import net.sf.anathema.character.generic.framework.unsupported.UnsupportedDragonKingTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.EssenceUserBackgroundTemplate;
import net.sf.anathema.character.generic.impl.backgrounds.SimpleBackgroundTemplate;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.lib.registry.IIdentificateRegistry;

public class BasicExaltCharacterModule extends NullObjectCharacterModuleAdapter {

  public static final String BACKGROUND_ID_FACE = "Face"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_MANSE = "Manse"; //$NON-NLS-1$
  public static final String BACKGROUND_ID_ARTIFACT = "Artifact"; //$NON-NLS-1$

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> registry = generics.getBackgroundRegistry();
    ITemplateRegistry templateRegistry = generics.getTemplateRegistry();
    registry.add(new EssenceUserBackgroundTemplate(BACKGROUND_ID_ARTIFACT, templateRegistry));
    registry.add(new EssenceUserBackgroundTemplate(BACKGROUND_ID_FACE, templateRegistry));
    registry.add(new EssenceUserBackgroundTemplate(BACKGROUND_ID_MANSE, templateRegistry));
    registry.add(new EssenceUserBackgroundTemplate(IBackgroundIds.BACKGROUND_ID_CULT, templateRegistry));
    registry.add(new SimpleBackgroundTemplate(IBackgroundIds.BACKGROUND_ID_ALLIES));
    registry.add(new SimpleBackgroundTemplate(IBackgroundIds.BACKGROUND_ID_BACKING));
    registry.add(new SimpleBackgroundTemplate(IBackgroundIds.BACKGROUND_ID_CONTACTS));
    registry.add(new SimpleBackgroundTemplate(IBackgroundIds.BACKGROUND_ID_FAMILIAR));
    registry.add(new SimpleBackgroundTemplate(IBackgroundIds.BACKGROUND_ID_FOLLOWERS));
    registry.add(new SimpleBackgroundTemplate(IBackgroundIds.BACKGROUND_ID_INFLUENCE));
    registry.add(new SimpleBackgroundTemplate(IBackgroundIds.BACKGROUND_ID_MENTOR));
    registry.add(new SimpleBackgroundTemplate(IBackgroundIds.BACKGROUND_ID_RESOURCES));
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    characterGenerics.getTemplateRegistry().register(new UnsupportedDragonKingTemplate(CharmCache.getInstance()));
    characterGenerics.getTemplateRegistry().register(new UnsupportedDragonBloodedSecondTemplate(CharmCache.getInstance()));
  }
}