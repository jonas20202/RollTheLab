package general;

import game.Ball;

public class BallPhysic extends Vektor{
    private boolean bounce = false;
    final double freeFallSpeed = 0.01;
    final double frictionValue = 0.00005;
    //constructor
    public BallPhysic(){
        super(0, 0.001);
    }

    //Recalks the force vector with the current collision Objects
    //and the current force vector
    public void RecalkPhysic(DrawingObjektGroup collidateObjects, Vektor ballMidPoint){
        Vektor dir = new Vektor(0,0);
        int nSize = collidateObjects.drawingObjekts.size();
        DrawingObjekt curCollObj = null;
        //get the current dir vector
        if(nSize == 0) {
            dir = SetSpeedToDir(new Vektor(0.2, 1));
            bounce = false;
        }
        else{
            for(int i = 0; i < nSize; i++)
            {
                //gets the current object from the collision objects
                curCollObj = collidateObjects.drawingObjekts.get(i);

                //sets the dir vector with the direction of the collision object
                dir = curCollObj.getMoveVek();
                //if the direction of the vector goes up turn the vector
                if(!dir.goesDown())
                    dir = new Vektor(-dir.x, -dir.y);

                dir = SetSpeedToDir(new Vektor(dir.getNormVek().x, dir.getNormVek().y));
            }

        }
        //the difference angle between the current force direction and the new dir vector

        double angleDiffDown = getCrossingAngle(dir);
        Vektor dirUp = new Vektor(-dir.x, -dir.y);
        double angleDiffUp = getCrossingAngle(dirUp);
        double angleDiff = angleDiffDown;
        if(angleDiffDown > angleDiffUp) {
            angleDiff = angleDiffUp;
        }
        double len = getLen();
        if(nSize > 0 && Math.abs(angleDiff) > 0.0000001 && !bounce && len > 0.2) {
            len /= 1;
//            Vektor ballWithForceDir = new Vektor(ballMidPoint);
//            ballWithForceDir.Add(this);
//
//            Vektor ballWithForceDir = new Vektor(ballMidPoint);
//            ballWithForceDir.Add(this);
            double mForce = y / x;
            double tForce = y - mForce * x;

            double mDir = dir.y / dir.x;
            double tDir = dir.y - mDir * dir.x;

            double xCrossingPoint = (tDir - tForce) / (mForce - mDir);
            double yCrossingPoint = mForce * xCrossingPoint + tForce;

            Vektor ortho = dir.getOrtho();
            double mOrtho = ortho.y / ortho.x;
            double tOrtho = yCrossingPoint - mOrtho * xCrossingPoint;

            double tDirCross = y - mDir * x;

            double xCrossingPoint2 = (tDirCross - tOrtho) / (mOrtho - mDir);
            double yCrossingPoint2 = mOrtho * xCrossingPoint2 + tOrtho;

            Vektor move = new Vektor(xCrossingPoint2 - x, yCrossingPoint2 -y);
            Vektor CrossingPoint2 = new Vektor(xCrossingPoint2, yCrossingPoint2);
            CrossingPoint2.Add(move);

            x =  xCrossingPoint - CrossingPoint2.x;
            y =  yCrossingPoint - CrossingPoint2.y;
            //rotate(-(angleDiff*2));
            setLen(len);
            bounce = true;
        }else if(nSize > 0 && goesDown()){
            if(dir.y != 0)
                x = dir.x;
            y = dir.y;
            setLen(len);
        }
        if(nSize > 0)
        {
            if(getLen() >= frictionValue)
                setLen((getLen()-frictionValue));
            else
                setLen(0);
        }
        //Adds direction vector
        if(dir.y != 0)
            Add(dir);
    }

    private double getSpeed(double angle){
        if(angle > Math.PI / 2) {
            if (angle > Math.PI) {
                if (angle > Math.PI * 1.5) {
                    return (angle - Math.PI * 2) * (freeFallSpeed / Math.PI / 2);
                } else
                    return (angle - Math.PI) * (freeFallSpeed / Math.PI / 2);
            } else
                return (angle - Math.PI) * (freeFallSpeed / Math.PI / 2);
        } else
            return angle * (freeFallSpeed / Math.PI / 2);
    }

    private Vektor SetSpeedToDir(Vektor dir){
        double len = getSpeed(dir.getCrossingAngle(null));
        dir.setLen(len);
        return dir;
    }
}
