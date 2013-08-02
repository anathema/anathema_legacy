package net.sf.anathema.platform.tree.fx;

import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;

public class FxTransformer {
  public static Affine convert(AgnosticTransform transform) {
    return Transform.affine(transform.a1, transform.a2, transform.b1, transform.b2, transform.c1, transform.c2);
  }
}