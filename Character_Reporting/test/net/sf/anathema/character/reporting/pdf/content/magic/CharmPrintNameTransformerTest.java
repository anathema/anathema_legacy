package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.dummy.DummyGenericTrait;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.magic.dummy.DummyCharm;
import net.sf.anathema.lib.resources.Resources;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CharmPrintNameTransformerTest {
  Resources resources = mock(Resources.class);
  DummyCharm charm = new DummyCharm("Abyssal.SecondExcellency.Archery", new ICharm[0],
          new GenericTrait[]{new DummyGenericTrait(AbilityType.Archery, 5)});

  @Before
  public void setUp() throws Exception {
    charm.setGeneric(true);
  }

  @Test
  public void internationalizesGenericCharms() throws Exception {
    String expected = "Hit";
    when(resources.getString("Archery")).thenReturn("Archery");
    when(resources.getString("Abyssal.SecondExcellency", "Archery")).thenReturn(expected);
    String result = new CharmPrintNameTransformer(resources).apply(charm);
    assertThat(result, is(expected));
  }


}
