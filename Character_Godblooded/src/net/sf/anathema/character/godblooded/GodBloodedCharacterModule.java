package net.sf.anathema.character.godblooded;

import net.sf.anathema.character.abyssal.AbyssalCharacterType;
import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.CharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.backgrounds.TemplateTypeBackgroundTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.ghost.GhostCharacterType;
import net.sf.anathema.character.godblooded.inheritance.GodBloodedInheritanceModelFactory;
import net.sf.anathema.character.godblooded.inheritance.GodBloodedInheritanceTemplate;
import net.sf.anathema.character.lunar.LunarCharacterType;
import net.sf.anathema.character.sidereal.SiderealCharacterType;
import net.sf.anathema.character.solar.SolarCharacterType;
import net.sf.anathema.character.spirit.SpiritCharacterType;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.util.Identifier;

@CharacterModule
public class GodBloodedCharacterModule extends CharacterModuleAdapter {

  public static final String BACKGROUND_ID_INHERITANCE = "Inheritance";

  private static final TemplateType godBloodedType = new TemplateType(new SpiritCharacterType(), new Identifier(
          "GodBlooded"));
  private static final TemplateType demonBloodedType = new TemplateType(new SpiritCharacterType(), new Identifier(
          "DemonBlooded"));
  private static final TemplateType ghostBloodedType = new TemplateType(new GhostCharacterType(), new Identifier(
          "GhostBlooded"));
  private static final TemplateType halfCasteAbyssalType = new TemplateType(new AbyssalCharacterType(), new Identifier(
          "HalfCasteAbyssal"));
  private static final TemplateType halfCasteLunarType = new TemplateType(new LunarCharacterType(), new Identifier(
          "HalfCasteLunar"));
  private static final TemplateType halfCasteSiderealType = new TemplateType(new SiderealCharacterType(), new Identifier(
          "HalfCasteSidereal"));
  private static final TemplateType halfCasteSolarType = new TemplateType(new SolarCharacterType(), new Identifier(
          "HalfCasteSolar"));

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/GodBlooded2nd.template", "godblooded/");
    registerParsedTemplate(characterGenerics, "template/DemonBlooded2nd.template", "godblooded/");
    registerParsedTemplate(characterGenerics, "template/GhostBlooded2nd.template", "godblooded/");
    registerParsedTemplate(characterGenerics, "template/HalfCasteAbyssal2nd.template", "godblooded/");
    registerParsedTemplate(characterGenerics, "template/HalfCasteLunar2nd.template", "godblooded/");
    registerParsedTemplate(characterGenerics, "template/HalfCasteSidereal2nd.template", "godblooded/");
    registerParsedTemplate(characterGenerics, "template/HalfCasteSolar2nd.template", "godblooded/");
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
