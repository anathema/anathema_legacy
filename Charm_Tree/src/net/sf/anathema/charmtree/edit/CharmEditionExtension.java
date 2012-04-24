package net.sf.anathema.charmtree.edit;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.initialization.Extension;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.reflections.AnathemaReflections;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.resources.IResources;

@Extension(id="net.sf.anathema.charmtree.CharmTreeEditor")
public class CharmEditionExtension implements CharmEditor, IAnathemaExtension {
  
  public static final String ID = "net.sf.anathema.charmtree.CharmTreeEditor";

  private CharmEditor editor = new NullCharmEditor();

  @Override
  public void initialize(IResources resources,
		  IDataFileProvider dataFileProvider,
		  AnathemaReflections reflections) throws InitializationException {
    // nothing to do
  }

  @Override
  public void editCharm(String charmId) {
    editor.editCharm(charmId);
  }

  public void setEditor(CharmEditor editor) {
    this.editor = editor;
  }
}
