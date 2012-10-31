package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IExtendedCharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupArbitrator;

import java.util.ArrayList;
import java.util.List;

public class LearningCharmGroupArbitrator implements ICharmGroupArbitrator {

  private final ICharmTemplate template;
  private final ICharacterModelContext context;

  public LearningCharmGroupArbitrator(ICharmTemplate template, ICharacterModelContext context) {
    this.template = template;
    this.context = context;
  }

  @Override
  public ICharm[] getCharms(ICharmGroup charmGroup) {
    IBasicCharacterData data = context.getBasicCharacterContext();
    ICharm[] allCharms = charmGroup.getAllCharms();
    if (template.isAllowedAlienCharms(data.getCasteType())) {
      return allCharms;
    }
    List<ICharm> charms = new ArrayList<>();
    for (ICharm charm : allCharms) {
      if (!charm.hasAttribute(IExtendedCharmData.EXCLUSIVE_ATTRIBUTE)
          || data.getCharacterType() == charm.getCharacterType()) {
        charms.add(charm);
      }
    }
    return charms.toArray(new ICharm[charms.size()]);
  }
}