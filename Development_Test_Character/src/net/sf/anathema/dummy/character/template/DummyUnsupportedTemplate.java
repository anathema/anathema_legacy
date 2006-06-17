package net.sf.anathema.dummy.character.template;

import net.sf.anathema.character.generic.impl.exalt.AbstractUnsupportedExaltTemplate;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.NotYetImplementedException;
import net.sf.anathema.lib.util.Identificate;

public class DummyUnsupportedTemplate extends AbstractUnsupportedExaltTemplate {

  private final String subtype;
  private final CharacterType type;
  private final IExaltedEdition edition;

  public DummyUnsupportedTemplate(CharacterType type, String subtype, IExaltedEdition edition) {
    this.type = type;
    this.subtype = subtype;
    this.edition = edition;
  }

  public IGroupedTraitType[] getAbilityGroups() {
    throw new NotYetImplementedException();
  }

  public IPresentationProperties getPresentationProperties() {
    throw new NotYetImplementedException();
  }

  public ITemplateType getTemplateType() {
    if (subtype == null) {
      return new TemplateType(type);
    }
    return new TemplateType(type, new Identificate(subtype));
  }

  @Override
  public IExaltedEdition getEdition() {
    return edition;
  }

  public IMagicTemplate getMagicTemplate() {
    throw new NotYetImplementedException();
  }
}