package net.sf.anathema.character.main.persistence;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.Element;

public class HeroTemplatePersister {

  public static final String ATTRIB_SUB_TYPE = "subtype";
  public static final String TAG_CHARACTER_TYPE = "CharacterType";

  private final HeroEnvironment generics;

  public HeroTemplatePersister(HeroEnvironment generics) {
    this.generics = generics;
  }

  public void saveTemplate(Element parent, Hero hero) {
    Preconditions.checkNotNull(hero);
    HeroTemplate template = hero.getTemplate();
    Element characterTypeElement = parent.addElement(TAG_CHARACTER_TYPE);
    characterTypeElement.addAttribute(ATTRIB_SUB_TYPE, template.getTemplateType().getSubType().getId());
    characterTypeElement.addText(template.getTemplateType().getCharacterType().getId());
  }

  public HeroTemplate loadTemplate(Element parent) {
    ITemplateType templateType = loadTemplateType(parent);
    return generics.getTemplateRegistry().getTemplate(templateType);
  }

  private ITemplateType loadTemplateType(Element parent) throws PersistenceException {
    String typeId = ElementUtilities.getRequiredText(parent, TAG_CHARACTER_TYPE);
    CharacterType characterType = generics.getCharacterTypes().findById(typeId);
    String subTypeValue = parent.element(TAG_CHARACTER_TYPE).attributeValue(ATTRIB_SUB_TYPE);
    Identifier subtype = subTypeValue == null ? TemplateType.DEFAULT_SUB_TYPE : new SimpleIdentifier(subTypeValue);
    return new TemplateType(characterType, subtype);
  }
}