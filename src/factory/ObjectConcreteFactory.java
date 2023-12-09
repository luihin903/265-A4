package factory;

import main.Panel;
import simulation.Instruction;
import simulation.Object;
import simulation.environment.*;

public class ObjectConcreteFactory extends AbstractFactory {

    public ObjectConcreteFactory(Panel panel) {
        super(panel);
    }

    public Object create(String type) {

        Object o;

        switch(type) {
            case "block":
                o = new Block();
                break;
            case "blurredStarship":
                o = new BlurredStarship();
                break;
            case "boosterEngines":
                o = new BoosterEngines();
                break;
            case "cloud":
                o = new Cloud();
                break;
            case "cover":
                o = new Cover();
                break;
            case "flame":
                o = new Flame();
                break;
            case "ground":
                o = new Ground();
                break;
            case "sky":
                o = new Sky();
                break;
            case "steam":
                o = new Steam();
                o.setPanel(panel);
                break;
            case "tower":
                o = new Tower();
                break;
            case "instruction":
                o = new Instruction();
                o.setPanel(panel);
                break;
            default:
                o = null;
                System.out.println(type + " not found");
        }

        return o;
    }

}
