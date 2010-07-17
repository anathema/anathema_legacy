package net.sf.anathema.character.impl.model;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.model.ISpellMapper;

public class SpellMapper implements ISpellMapper {

  private final Map<String, String> spellMap2e = new HashMap<String, String>();

  public SpellMapper() {
    spellMap2e.put("Terrestrial.DragonSmokeSerpentFlame", "Terrestrial.DragonSmokeFlame"); //$NON-NLS-1$ //$NON-NLS-2$
    spellMap2e.put("Celestial.GeyserCorruption", "Celestial.GeyserCorrosion"); //$NON-NLS-1$ //$NON-NLS-2$
    spellMap2e.put("Shadowlands.InvisibleDoorway", "Shadowlands.DoorDead"); //$NON-NLS-1$ //$NON-NLS-2$
    spellMap2e.put("Shadowlands.RingingHunRebuke", "Shadowlands.RingingRebuke"); //$NON-NLS-1$ //$NON-NLS-2$
    spellMap2e.put("Labyrinth.BanefulSun", "Labyrinth.BanefulSunShadow"); //$NON-NLS-1$ //$NON-NLS-2$
    spellMap2e.put("Labyrinth.BlackVial", "Labyrinth.BlackVialMedicine"); //$NON-NLS-1$ //$NON-NLS-2$
    spellMap2e.put("Labyrinth.WhiteShard", "Labyrinth.WhiteShardFuneral"); //$NON-NLS-1$ //$NON-NLS-2$
    spellMap2e.put("Void.BlackstormWagon", "Void.BlackstormCoffle"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public String getId(String id, IExaltedEdition edition) {
    if (edition == ExaltedEdition.SecondEdition) {
      String mappedId = spellMap2e.get(id);
      if (mappedId != null) {
        return mappedId;
      }
    }
    return id;
  }
}