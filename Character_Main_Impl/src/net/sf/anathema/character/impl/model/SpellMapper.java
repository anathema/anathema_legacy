package net.sf.anathema.character.impl.model;

import com.google.common.base.Functions;
import net.sf.anathema.character.model.ISpellMapper;

import java.util.HashMap;
import java.util.Map;

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

  @Override
  public String getId(String id) {
    return Functions.forMap(spellMap2e, id).apply(id);
  }
}
