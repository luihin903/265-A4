package factory;

import main.Panel;
import simulation.starship.BasicStarship;
import simulation.starship.BoosterDecorator;
import simulation.starship.FlameDecorator;
import simulation.starship.SatelliteDecorator;
import simulation.starship.SpacecraftDecorator;
import simulation.starship.StarshipInterface;

public class StarshipConcreteFactory extends AbstractFactory {
    
    StarshipInterface s;

    public StarshipConcreteFactory(Panel panel) {
        super(panel);
        s = null;
    }

    public StarshipInterface create(String type) {

        switch(type) {
            case "basic":
                s = new BasicStarship();
                break;
            case "booster":
                s = new BoosterDecorator(s);
                break;
            case "spacecraft":
                s = new SpacecraftDecorator(s);
                break;
            case "satellite":
                s = new SatelliteDecorator(s);
                break;
            case "flame":
                s = new FlameDecorator(s);
                break;
            default:
                s = null;
                System.out.println(type + " not found");
        }

        return s;
    }
}
