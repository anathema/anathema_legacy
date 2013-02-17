package net.sf.anathema.character.godblooded;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.*;
import net.sf.anathema.character.godblooded.inheritance.GodBloodedInheritanceModelFactory;
import net.sf.anathema.character.godblooded.inheritance.GodBloodedInheritanceParser;
import net.sf.anathema.character.godblooded.inheritance.GodBloodedInheritanceTemplate;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.util.Identificate;

@CharacterModule
public class GodBloodedCharacterModule extends NullObjectCharacterModuleAdapter {

  public static final String BACKGROUND_ID_INHERITANCE = "Inheritance"; //$NON-NLS-1$

  private static final TemplateType godBloodedType = new TemplateType(new SpiritCharacterType(), new Identificate(
          "GodBlooded")); //$NON-NLS-1$
  private static final TemplateType demonBloodedType = new TemplateType(new SpiritCharacterType(), new Identificate(
          "DemonBlooded")); //$NON-NLS-1$
  private static final TemplateType ghostBloodedType = new TemplateType(new GhostCharacterType(), new Identificate(
          "GhostBlooded")); //$NON-NLS-1$
  private static final TemplateType halfCasteAbyssalType = new TemplateType(new AbyssalCharacterType(), new Identificate(
          "HalfCasteAbyssal")); //$NON-NLS-1$
  private static final TemplateType halfCasteLunarType = new TemplateType(new LunarCharacterType(), new Identificate(
          "HalfCasteLunar")); //$NON-NLS-1$
  private static final TemplateType halfCasteSiderealType = new TemplateType(new SiderealCharacterType(), new Identificate(
          "HalfCasteSidereal")); //$NON-NLS-1$
  private static final TemplateType halfCasteSolarType = new TemplateType(new SolarCharacterType(), new Identificate(
          "HalfCasteSolar")); //$NON-NLS-1$

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getAdditionalTemplateParserRegistry().register(
            GodBloodedInheritanceTemplate.ID,
            new GodBloodedInheritanceParser());
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/GodBlooded2nd.template", "godblooded/"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/DemonBlooded2nd.template", "godblooded/"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/GhostBlooded2nd.template", "godblooded/"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/HalfCasteAbyssal2nd.template", "godblooded/"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/HalfCasteLunar2nd.template", "godblooded/"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/HalfCasteSidereal2nd.template", "godblooded/"); //$NON-NLS-1$
    registerParsedTemplate(characterGenerics, "template/HalfCasteSolar2nd.template", "godblooded/"); //$NON-NLS-1$
  }

  @Override
  public void addBackgroundTemplates(ICharacterGenerics generics) {
    IIdentificateRegistry<IBackgroundTemplate> backgroundRegistry = generics.getBackgroundRegistry();
    ITemplateType[] templates = new TemplateType[]{
            godBloodedType,
            demonBloodedType,
            ghostBloodedType,
            halfCasteAbyssalType,
            halfCasteLunarType,
            halfCasteSiderealType,
            halfCasteSolarType
    };
    backgroundRegistry.add(new TemplateTypeBackgroundTemplate(BACKGROUND_ID_INHERITANCE, templates));
  }

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = characterGenerics.getAdditionalModelFactoryRegistry();
    registerGodBloodedInheritance(additionalModelFactoryRegistry);
  }

  private void registerGodBloodedInheritance(IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry) {
    String templateId = GodBloodedInheritanceTemplate.ID;
    additionalModelFactoryRegistry.register(templateId, new GodBloodedInheritanceModelFactory());
  }
}
