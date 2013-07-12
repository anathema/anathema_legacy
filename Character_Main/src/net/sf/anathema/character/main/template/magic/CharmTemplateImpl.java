package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.hero.concept.CasteType;

import java.util.ArrayList;
import java.util.List;

public class CharmTemplateImpl implements CharmTemplate {

  private boolean canLearnCharms;
  private final List<String> alienAllowedCastes = new ArrayList<>();
  private final MartialArtsRules martialArtsRules;

  public CharmTemplateImpl(MartialArtsRules rules, boolean canLearnCharms) {
    this.martialArtsRules = rules;
    this.canLearnCharms = canLearnCharms;
  }

  @Override
  public MartialArtsRules getMartialArtsRules() {
    return martialArtsRules;
  }

  @Override
  public boolean canLearnCharms() {
    return canLearnCharms;
  }

  @Override
  public boolean isAllowedAlienCharms(CasteType caste) {
    return alienAllowedCastes.contains(caste.getId());
  }

  public void setCasteAlienAllowed(String casteId) {
    alienAllowedCastes.add(casteId);
  }
}