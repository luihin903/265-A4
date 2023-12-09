package factory;

import main.Panel;

public abstract class AbstractFactory {
    
    Panel panel;

    public AbstractFactory(Panel panel) {
        this.panel = panel;
    }

}
