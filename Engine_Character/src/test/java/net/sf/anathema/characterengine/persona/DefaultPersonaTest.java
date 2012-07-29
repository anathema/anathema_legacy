package net.sf.anathema.characterengine.persona;

import net.sf.anathema.characterengine.command.Command;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.Type;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DefaultPersonaTest {

  private Qualities qualities = mock(Qualities.class);
  private Persona persona = new DefaultPersona(qualities);

  @Test
  public void callsCommandsWithQualities() throws Exception {
    Command command = mock(Command.class);
    persona.execute(command);
    verify(command).execute(qualities);
  }

  @Test
  public void executesQueriesOnQualities() throws Exception {
    QualityClosure closure = mock(QualityClosure.class);
    Type type = new Type("type");
    Name name = new Name("name");
    persona.doFor(new QualityKey(type, name), closure);
    verify(qualities).doFor(new QualityKey(type, name), closure);
  }

  @Test
  public void executesQueryOnEachQuality() throws Exception {
    QualityClosure closure = mock(QualityClosure.class);
    Type type = new Type("type");
    persona.doForEach(type, closure);
    verify(qualities).doForEach(type, closure);
  }
}