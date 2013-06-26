package net.sf.anathema.character.model;

import com.google.common.base.Functions;

import java.util.HashMap;
import java.util.Map;

public class SpellMapper implements ISpellMapper {

  private final Map<String, String> spellMap2e = new HashMap<>();

  public SpellMapper() {
    spellMap2e.put("Terrestrial.DragonSmokeSerpentFlame", "Terrestrial.DragonSmokeFlame");
    spellMap2e.put("Celestial.GeyserCorruption", "Celestial.GeyserCorrosion");
    spellMap2e.put("Shadowlands.InvisibleDoorway", "Shadowlands.DoorDead");
    spellMap2e.put("Shadowlands.RingingHunRebuke", "Shadowlands.RingingRebuke");
    spellMap2e.put("Labyrinth.BanefulSun", "Labyrinth.BanefulSunShadow");
    spellMap2e.put("Labyrinth.BlackVial", "Labyrinth.BlackVialMedicine");
    spellMap2e.put("Labyrinth.WhiteShard", "Labyrinth.WhiteShardFuneral");
    spellMap2e.put("Void.BlackstormWagon", "Void.BlackstormCoffle");
  }

  @Override
  public String getId(String id) {
    return Functions.forMap(spellMap2e, id).apply(id);
  }
}
