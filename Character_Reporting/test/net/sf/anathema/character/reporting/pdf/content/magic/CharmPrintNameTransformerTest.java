package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.dummy.DummyGenericTrait;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.lib.resources.IResources;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CharmPrintNameTransformerTest {
  IResources resources = mock(IResources.class);
  DummyCharm charm = new DummyCharm("Abyssal.SecondExcellency.Archery", new ICharm[0],
          new IGenericTrait[]{new DummyGenericTrait(AbilityType.Archery, 5)});

  @Before
  public void setUp() throws Exception {
    charm.setGeneric(true);
  }

  @Test
  public void internationalizesGenericCharms() throws Exception {
    String expected = "Hit";
    when(resources.getString("Archery")).thenReturn("Archery");
    when(resources.getString("Abyssal.SecondExcellency", "Archery")).thenReturn(expected);
    String result = new CharmPrintNameTransformer(resources).transform(charm);
    assertThat(result, is(expected));
  }


}
