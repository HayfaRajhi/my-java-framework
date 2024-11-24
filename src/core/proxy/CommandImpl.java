package core.proxy;

public class CommandImpl implements Command {
	
	private final String label ="test";
	
	@Override
	public void execute() {
		System.out.println(this.label);
	}

	@Override
	public void other() {
		System.out.println("Other !!");
	}

}
