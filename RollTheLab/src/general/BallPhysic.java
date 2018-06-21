package general;

import game.Ball;

public class BallPhysic extends Vektor{
    private boolean bounce = false;
    final double freeFallSpeed = 0.02;
    final double frictionValue = 0.00001;
    //constructor
    public BallPhysic(){
        super(0, 0.001);
    }

    //Recalks the force vector with the current collision Objects
    //and the current force vector
    public void RecalkPhysic(Ball ball){
        DrawingObjektGroup collidateObjects = ball.lab.getCollidateObjekts(ball.ball, false);
        int nSize = collidateObjects.drawingObjekts.size();

        Vektor dir = null;
        boolean canMove = false;
        int curCollObj = 0;
        while(!canMove) {
            //At the first time
            Vektor forceBk = new Vektor(this);
            if (dir == null) {
                dir = SetSpeedToDir(new Vektor(0, 1));
                if(nSize == 0)
                    bounce = false;
            }else {
                if (curCollObj < nSize) {
                    dir = getDirVec(curCollObj, collidateObjects);
                    curCollObj++;
                } else{
                    break;
                }
            }
            //the difference angle between the current force direction and the new dir vector
            double angleDiffDown = getCrossingAngle(dir);
            Vektor dirUp = new Vektor(-dir.x, -dir.y);
            double angleDiffUp = getCrossingAngle(dirUp);
            double angleDiff = angleDiffDown;
            if (angleDiffDown > angleDiffUp) {
                angleDiff = angleDiffUp;
            }

            double len = getLen();
            if (nSize > 0 && Math.abs(angleDiff) > 0.0000001 && !bounce && len > 0.2) {
                boolean goesDown = goesDown();
                len /= 1;

                LineFunction dirLine = new LineFunction(dir, this);
                LineFunction orthoLine = new LineFunction(dir.getOrtho(), new Vektor(0, 0));

                Vektor CrossingPoint = orthoLine.getCrossingPoint(dirLine);
                Vektor move = CrossingPoint.minusVec(this);
                Add(move);
                Add(move);

                Vektor moveTest = new Vektor(this);
                moveTest.setLen(10);
                if (!canMove(new Vektor(new Vektor(moveTest)), ball, false)) {
                    x = -x;
                    y = -y;
                }

                setLen(len);
                bounce = true;
            } else if (nSize > 0 && goesDown()) {
                LineFunction orthoLine = new LineFunction(dir.getOrtho(), new Vektor(x, y));
                LineFunction dirLine = new LineFunction(dir, new Vektor(0, 0));
                len = orthoLine.getCrossingPoint(dirLine).getLen();

                if (dir.y != 0)
                    x = dir.x;
                y = dir.y;

                Vektor newVecUp = new Vektor(-x, -y);
                double testADown = getCrossingAngle(forceBk);
                double testAUp = newVecUp.getCrossingAngle(forceBk);
                if(forceBk.isRightOr() != isRightOr() && forceBk.isLeftOr() != isLeftOr())
                {
                    x = newVecUp.x;
                    y = newVecUp.y;
                    len -= dir.getLen();
                }
                setLen(len);
            }

            //Add friction of the line
            if (nSize > 0) {
                if (getLen() >= frictionValue)
                    setLen((getLen() - frictionValue));
                else
                    setLen(0);
            }


            if (dir.y != 0)
                Add(dir);

            if(canMove(this, ball, true))
                canMove = true;
            else
            {

                x = forceBk.x;
                y = forceBk.y;
            }
        }
    }

    private Vektor getDirVec(int i, DrawingObjektGroup collidateObjects){
        Vektor dir = null;
        //gets the current object from the collision objects
        DrawingObjekt curCollObj = curCollObj = collidateObjects.drawingObjekts.get(i);

        //sets the dir vector with the direction of the collision object
        dir = curCollObj.getMoveVek();
        //if the direction of the vector goes up turn the vector
        if(!dir.goesDown())
            dir = new Vektor(-dir.x, -dir.y);

        dir = SetSpeedToDir(new Vektor(dir.getNormVek().x, dir.getNormVek().y));
        return dir;
    }

    private boolean canMove(Vektor move, Ball ball, boolean CorrectDiff){
        Vektor moveCopy = new Vektor(move);
        moveCopy.Add(ball.ball.getMidPoint());
        DrawingArc movedBall = new DrawingArc(moveCopy, ball.ball.getRadius(), 0,360);
        DrawingObjektGroup collidateObjects = ball.lab.getCollidateObjekts(movedBall, true);
        if(collidateObjects.drawingObjekts.size() == 0)
            return true;
        else {
            if(CorrectDiff)
            {
                while(collidateObjects.drawingObjekts.size() > 0) {
                    Vektor moveVec = collidateObjects.drawingObjekts.get(0).getMoveOnLineVec();
                    ball.ball.move(moveVec);
                    collidateObjects = ball.lab.getCollidateObjekts(ball.ball, true);
                }
            }
            return false;
        }
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
