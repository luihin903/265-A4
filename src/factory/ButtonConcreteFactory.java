/*
 * The factory for buttons
 */

package factory;

import main.Panel;
import simulation.button.*;

public class ButtonConcreteFactory extends AbstractFactory {
    
    public ButtonConcreteFactory(Panel panel) {
        super(panel);
    }

    public Button create(String type) {

        Button b;

        switch(type) {
            case "booster":
                b = new Booster();
                break;
            case "launch":
                b = new Launch();
                b.setPanel(panel);
                break;
            case "restart":
                b = new Restart();
                break;
            case "satellite":
                b = new Satellite();
                break;
            case "spacecraft":
                b = new Spacecraft();
                break;
            case "start":
                b = new Start();
                break;
            default:
                b = null;
                System.out.println(type + " not found");
        }

        return b;
    }

}
