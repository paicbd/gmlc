package org.mobicents.gmlc.extension;

import org.jboss.as.controller.*;
import org.jboss.dmr.ModelNode;
import org.jboss.msc.service.ServiceController;

import java.util.List;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.OP_ADDR;
import static org.mobicents.gmlc.extension.GmlcMbeanDefinition.MBEAN_ATTRIBUTES;

class GmlcMbeanAdd extends AbstractAddStepHandler {

  static final GmlcMbeanAdd INSTANCE = new GmlcMbeanAdd();

  private GmlcMbeanAdd() {
  }

  @Override
  protected void populateModel(final ModelNode operation, final ModelNode model) throws OperationFailedException {
    PathAddress address = PathAddress.pathAddress(operation.require(OP_ADDR));
    String name = GmlcMbeanDefinition.NAME_ATTR.getName();
    model.get(name).set(address.getLastElement().getValue());

    //SS7MbeanDefinition.NAME_ATTR.validateAndSet(operation, model);
    for (SimpleAttributeDefinition def : MBEAN_ATTRIBUTES) {
      def.validateAndSet(operation, model);
    }
  }

  @Override
  protected void performRuntime(OperationContext context, ModelNode operation, ModelNode model,
                                ServiceVerificationHandler verificationHandler, List<ServiceController<?>> newControllers)
      throws OperationFailedException {

    final PathAddress address = PathAddress.pathAddress(operation.get(OP_ADDR));
    final String mbeanName = address.getLastElement().getValue();

    // here we can add mbeans with reflection usage
  }
}
