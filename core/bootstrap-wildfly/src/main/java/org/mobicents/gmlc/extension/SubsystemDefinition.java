package org.mobicents.gmlc.extension;

import org.jboss.as.controller.SimpleResourceDefinition;
import org.jboss.as.controller.registry.ManagementResourceRegistration;

/**
 * @author <a href="mailto:tcerar@redhat.com">Tomaz Cerar</a>
 */
public class SubsystemDefinition extends SimpleResourceDefinition {
  public static final SubsystemDefinition INSTANCE = new SubsystemDefinition();

  private SubsystemDefinition() {
    super(GmlcExtension.SUBSYSTEM_PATH,
        GmlcExtension.getResourceDescriptionResolver(null),
        //We always need to add an 'add' operation
        SubsystemAdd.INSTANCE,
        //Every resource that is added, normally needs a remove operation
        SubsystemRemove.INSTANCE);
  }

  @Override
  public void registerOperations(ManagementResourceRegistration resourceRegistration) {
    super.registerOperations(resourceRegistration);
    //you can register additional operations here
  }

  @Override
  public void registerAttributes(ManagementResourceRegistration resourceRegistration) {
    //you can register attributes here
  }
}
