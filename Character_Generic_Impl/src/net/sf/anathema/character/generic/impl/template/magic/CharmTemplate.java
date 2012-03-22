package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.magic.ICharmSet;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMartialArtsRules;
import net.sf.anathema.character.generic.template.magic.IUniqueCharmType;

import java.util.ArrayList;
import java.util.List;

public class CharmTemplate implements ICharmTemplate {

  private final ICharmSet charmSet;
  private final List<String> alienAllowedCastes = new ArrayList<String>();
  private final IUniqueCharmType uniqueCharmType;
  private final IMartialArtsRules martialArtsRules;

  public CharmTemplate(IMartialArtsRules rules, ICharmSet charmSet) {
    this(rules, charmSet, null);
  }

  public CharmTemplate(IMartialArtsRules rules, ICharmSet charmSet, IUniqueCharmType uniqueCharms) {
    this.martialArtsRules = rules;
    this.charmSet = charmSet;
    this.uniqueCharmType = uniqueCharms;
  }

  @Override
  public final ICharm[] getCharms() {
    return charmSet.getCharms();
  }
  
  @Override
  public final ICharm[] getUniqueCharms() {
	return charmSet.getUniqueCharms();
  }

  @Override
  public final IUniqueCharmType getUniqueCharmType() {
    return uniqueCharmType;
  }

  @Override
  public final ICharm[] getMartialArtsCharms() {
    return charmSet.getMartialArtsCharms();
  }

  @Override
  public IMartialArtsRules getMartialArtsRules() {
    return martialArtsRules;
  }

  @Override
  public boolean hasUniqueCharms() {
    return uniqueCharmType != null;
  }

  @Override
  public boolean canLearnCharms() {
    return charmSet.getCharms().length > 0 || charmSet.getMartialArtsCharms().length > 0;
  }

  @Override
  public boolean isAllowedAlienCharms(ICasteType caste) {
    return alienAllowedCastes.contains(caste.getId());
  }

  public void setCasteAlienAllowed(String casteId) {
    alienAllowedCastes.add(casteId);
  }
}