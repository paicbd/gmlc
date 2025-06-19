package org.mobicents.gmlc.management.console.impl;

import org.restcomm.ss7.management.console.CommandContext;
import org.restcomm.ss7.management.console.CommandHandlerWithHelp;
import org.restcomm.ss7.management.console.Tree;
import org.restcomm.ss7.management.console.Tree.Node;

/**
 *
 * @author <a href="mailto:abhayani@gmail.com"> Amit Bhayani </a>
 *
 */
public class GmlcCommandHandler extends CommandHandlerWithHelp {

  static final Tree commandTree = new Tree("gmlc");

  static {
    Node parent = commandTree.getTopNode();

    Node set = parent.addChild("set");
    set.addChild("gmlcgt");
    set.addChild("gmlcssn");
    set.addChild("hlrssn");
    set.addChild("mscssn");
    set.addChild("vlrssn");

    Node get = parent.addChild("get");
    get.addChild("gmlcgt");
    get.addChild("gmlcssn");
    get.addChild("hlrssn");
    get.addChild("mscssn");
    get.addChild("vlrssn");

  }

  public GmlcCommandHandler() {
    super(commandTree, CONNECT_MANDATORY_FLAG);
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * org.mobicents.ss7.management.console.CommandHandler#isValid(java.lang.String)
   */
  @Override
  public void handle(CommandContext ctx, String commandLine) {
    // TODO Validate command
    if (commandLine.contains("--help")) {
      this.printHelp(commandLine, ctx);
      return;
    }

    ctx.sendMessage(commandLine);
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * org.mobicents.ss7.management.console.CommandHandler#isAvailable(org.mobicents.ss7.management.console.CommandContext)
   */
  @Override
  public boolean isAvailable(CommandContext ctx) {
    if (!ctx.isControllerConnected()) {
      ctx.printLine("The command is not available in the current context. Please connect first");
      return false;
    }
    return true;
  }

}
