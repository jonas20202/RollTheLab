package general;

public class BallPhysic extends Vektor{
    private boolean bounce = false;

    //constructor
    public BallPhysic(){
        super(0, 0.001);
    }

    //Recalks the force vector with the current collision Objects
    //and the current force vector
    public void RecalkPhysic(DrawingObjektGroup collidateObjects){
        Vektor dir = new Vektor(0,0);
        int nSize = collidateObjects.drawingObjekts.size();

        //get the current dir vector
        if(nSize == 0) {
            dir = new Vektor(0, 0.001);
            bounce = false;
        }
        else{
            for(int i = 0; i < nSize; i++)
            {
                //gets the current object from the collision objects
                DrawingObjekt curCollObj = collidateObjects.drawingObjekts.get(i);

                //sets the dir vector with the direction of the collision object
                dir = curCollObj.getMoveVek();

                //if the direction of the vector goes up turn the vector
                if(!dir.goesDown())
                    dir = new Vektor(-dir.x, -dir.y);

                //
                dir = new Vektor(dir.getNormVek().x * 0.0001, dir.getNormVek().y * 0.0001);
            }

        }
        //the difference angle between the current force direction and the new dir vector
        double angleDiff = getCrossingAngle(dir);
        double len = getLen();
        if(nSize > 0 && Math.abs(angleDiff) > 0.0000001 && !bounce && len > 0.2) {
            double testPrevAngle = getCrossingAngle(null);
            double gradPrev = (360 / (2*Math.PI)) * testPrevAngle;
            len /= 2;
            angleDiff *= 2;
            rotate(-angleDiff);
            double testAfterAngle = getCrossingAngle(null);
            double gradnext = (360 / (2*Math.PI)) * testAfterAngle;
            setLen(len);
            bounce = true;
        }else if(nSize > 0 && goesDown()){
            x = dir.x;
            y = dir.y;
            setLen(len);
            //force.Add(dir);
        }

        //Adds direction vector
        if(dir.y != 0)
            Add(dir);
    }

}
