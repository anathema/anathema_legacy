package net.sf.anathema.character.main.xml.magic;

import net.sf.anathema.character.main.magic.spells.CircleType;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.magic.CustomizableFreePicksPredicate;
import net.sf.anathema.character.main.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.main.template.magic.SpellMagicTemplate;
import net.sf.anathema.character.main.xml.core.AbstractXmlTemplateParser;
import net.sf.anathema.character.main.xml.registry.IXmlTemplateRegistry;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class GenericMagicTemplateParser extends AbstractXmlTemplateParser<GenericMagicTemplate> {

  private static final Object VALUE_NONE = "None";
  private static final String TAG_SPELL_TEMPLATE = "spellTemplate";
  private static final String ATTRIB_MAXIMUM_SORCERY_CIRCLE = "maximumSorceryCircle";
  private static final String ATTRIB_MAXIMUM_NECROMANCY_CIRCLE = "maximumNecromancyCircle";
  private static final String ATTRIB_SUB_TEMPLATE = "subTemplate";
  private final HeroTemplate hostTemplate;

  public GenericMagicTemplateParser(IXmlTemplateRegistry<GenericMagicTemplate> templateRegistry, HeroTemplate template) {
    super(templateRegistry);
    this.hostTemplate = template;
  }

  @Override
  protected GenericMagicTemplate createNewBasicTemplate() {
    return new GenericMagicTemplate();
  }

  @Override
  public GenericMagicTemplate parseTemplate(Element element) throws PersistenceException {
    GenericMagicTemplate basicTemplate = getBasicTemplate(element);
    setSpellTemplate(basicTemplate, element);
    return basicTemplate;
  }

  @SuppressWarnings("unchecked")
  private void setSpellTemplate(GenericMagicTemplate basicTemplate, Element element) throws PersistenceException {
    Element spellTemplateElement = element.element(TAG_SPELL_TEMPLATE);
    if (spellTemplateElement == null) {
      return;
    }
    String maximumSorceryCircleId = ElementUtilities.getRequiredAttrib(spellTemplateElement, ATTRIB_MAXIMUM_SORCERY_CIRCLE);
    CircleType maximumSorceryCircle = null;
    if (!maximumSorceryCircleId.equals(VALUE_NONE)) {
      maximumSorceryCircle = CircleType.valueOf(maximumSorceryCircleId);
    }
    String maximumNecromancyCircleId = ElementUtilities.getRequiredAttrib(spellTemplateElement, ATTRIB_MAXIMUM_NECROMANCY_CIRCLE);
    CircleType maximumNecromancyCircle = null;
    if (!maximumNecromancyCircleId.equals(VALUE_NONE)) {
      maximumNecromancyCircle = CircleType.valueOf(maximumNecromancyCircleId);
    }
    Class<SpellMagicTemplate> magicTemplateClass = SpellMagicTemplate.class;
    ISpellMagicTemplate template = null;
    String spellMagicSubTemplate = spellTemplateElement.attributeValue(ATTRIB_SUB_TEMPLATE);
    if (spellMagicSubTemplate != null) {
      try {
        magicTemplateClass = (Class<SpellMagicTemplate>) Class.forName(spellMagicSubTemplate);
      } catch (ClassNotFoundException e1) {
        e1.printStackTrace();
      }
    }

    try {
      Constructor<SpellMagicTemplate>[] helper = (Constructor<SpellMagicTemplate>[]) magicTemplateClass.getConstructors();
      template = helper[0]
              .newInstance(CircleType.getSorceryCirclesUpTo(maximumSorceryCircle), CircleType.getNecromancyCirclesUpTo(maximumNecromancyCircle),
                      hostTemplate);
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }
    basicTemplate.setSpellTemplate(template);
  }
}