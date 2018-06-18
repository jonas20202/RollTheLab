package general;

public class BallPhysic {
    private Vektor force = new Vektor(0,1);
    public Vektor getDir(){ return force;}
    private boolean bounce = false;
    public DrawingLine testLine = new DrawingLine(new Vektor(0,0), new Vektor(0,0));

    public void RecalkPhysic(DrawingObjektGroup collidateObjects){
        Vektor dir = new Vektor(0,0);
        int nSize = collidateObjects.drawingObjekts.size();
        if(nSize == 0) {
            dir = new Vektor(0, 1);
            bounce = false;
        }
        else{
            for(int i = 0; i < nSize; i++)
            {
                DrawingObjekt curCollObj = collidateObjects.drawingObjekts.get(i);
                dir = curCollObj.getMoveVek();
                if(!dir.goesDown())
                    dir = new Vektor(-dir.x, -dir.y);
                Vektor down = new Vektor(0, 1);
                dir = new Vektor(dir.getNormVek().x * 0.0001, dir.getNormVek().y * 0.0001);
            }

        }
        double testAngek = force.getCrossingAngle(null);
        double angleDiff = force.getCrossingAngle(null) - dir.getCrossingAngle(null);
        if(nSize > 0 && Math.abs(angleDiff) > 0 && !bounce) {
            double len = force.getLen();

            force.rotate(-angleDiff);
            force = new Vektor(force.getNormVek().x * len, force.getNormVek().y * len);
            bounce = true;
            /*if(force.getAngle() > dir.getAngle()) {
                len = force.getLen();
                force = new Vektor(dir.getNormVek().x * len, dir.getNormVek().y * len);
            }*/
            //force.Add(dir);
        }else if(nSize > 0 && force.goesDown()){
            double len = force.getLen();
            force = new Vektor(dir.getNormVek().x*len, dir.getNormVek().y*len);
            //force.Add(dir);
        }
            force.Add(dir);
    }

}
