package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.magic.ICharmSet;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.MartialArtsRules;

import java.util.ArrayList;
import java.util.List;

public class CharmTemplate implements ICharmTemplate {

  private final ICharmSet charmSet;
  private final List<String> alienAllowedCastes = new ArrayList<>();
  private final MartialArtsRules martialArtsRules;

  public CharmTemplate(MartialArtsRules rules, ICharmSet charmSet) {
    this.martialArtsRules = rules;
    this.charmSet = charmSet;
  }

  @Override
  public final ICharm[] getCharms() {
    return charmSet.getCharms();
  }

  @Override
  public final ICharm[] getMartialArtsCharms() {
    return charmSet.getMartialArtsCharms();
  }

  @Override
  public MartialArtsRules getMartialArtsRules() {
    return martialArtsRules;
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