package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

import java.util.Arrays;
import java.util.List;

import static net.sf.anathema.character.equipment.MagicalMaterial.Adamant;
import static net.sf.anathema.character.equipment.MagicalMaterial.Orichalcum;
import static net.sf.anathema.character.equipment.impl.creation.model.WeaponTag.Piercing;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProxyWeaponStats_AdamantTagsTest {
  IWeaponStats original = mock(IWeaponStats.class);

  @Test
  public void addsPiercingToAAdamantWeapon() throws Exception {
    setOriginalTags();
    List<IIdentificate> tags = getModifiedTags(Adamant);
    assertThat(tags, JUnitMatchers.<IIdentificate>hasItem(Piercing));
  }

  @Test
  public void doesNotAddPiercingToAnotherMaterial() throws Exception {
    setOriginalTags();
    List<IIdentificate> tags = getModifiedTags(Orichalcum);
    assertThat(tags.size(), is(0));
  }

  @Test
  public void addsSpecialPiercingTagIfItIsAlreadyPiercing() throws Exception {
    setOriginalTags(Piercing);
    List<IIdentificate> tags = getModifiedTags(Adamant);
    assertThat(tags, JUnitMatchers.<IIdentificate>hasItem(new Identificate("AdamantPiercing")));
  }

  private List<IIdentificate> getModifiedTags(MagicalMaterial material) {
    ProxyWeaponStats stats = new ProxyWeaponStats(original, material);
    return Arrays.asList(stats.getTags());
  }

  private void setOriginalTags(IIdentificate... tags) {
    when(original.getTags()).thenReturn(tags);
  }
}