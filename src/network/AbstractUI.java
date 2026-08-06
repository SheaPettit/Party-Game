package network;

public abstract class AbstractUI implements UserInterface {
    protected Client client;
    public AbstractUI(Client client) {
        this.client = client;
    }
}