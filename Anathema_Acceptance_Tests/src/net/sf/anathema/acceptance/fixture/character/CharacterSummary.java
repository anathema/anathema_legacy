package net.sf.anathema.acceptance.fixture.character;

import java.io.FileNotFoundException;
import java.util.Map;

import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.generic.impl.magic.persistence.CharmCache;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.impl.model.ExaltedCharacter;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.resources.IResources;

public class CharacterSummary extends CharacterGenericsSummary {

  private static final String KEY_RESOURCES = "Resources"; //$NON-NLS-1$
  private static final String KEY_CHARACTER = "character"; //$NON-NLS-1$

  @SuppressWarnings("unchecked")
  public CharacterSummary(Map<Object, Object> summary) {
    super(summary);
  }

  public void setCharacter(ICharacterTemplate template) throws SpellException {
    ExaltedCharacter character = new ExaltedCharacter();
    character.createCharacterStatistics(template, getCharacterGenerics(), template.getEdition().getDefaultRuleset());
    setCharacter(character);
  }

  public void setCharacter(ICharacter character) {
    summary.put(KEY_CHARACTER, character);
  }

  public ICharacter getCharacter() {
    return (ICharacter) summary.get(KEY_CHARACTER);
  }

  public ICharacterTemplate getTemplate() {
    return getCharacter().getStatistics().getCharacterTemplate();
  }

  public void setResources(IResources resources) {
    summary.put(KEY_RESOURCES, resources);
  }

  public void registerCharmFiles() throws FileNotFoundException, AnathemaException {
    if (!CharmCache.getInstance().isEmpty()) {
      return;
    }
    AcceptanceCharmCompiling charmCompiling = new AcceptanceCharmCompiling();
    charmCompiling.registerCharmFiles();
  }
}