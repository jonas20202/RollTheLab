package general.physics;

import drawingObjects.Ball;
import drawingObjects.general.DrawingObject;
import drawingObjects.general.DrawingObjektGroup;
import game.gui.GameFrame;
import game.gui.DrawingPanel;
import general.math.calculation.LineFunction;
import general.math.calculation.Vektor;

public class BallPhysic extends Vektor {
    //private boolean bounce = false;
    //private double bounceSpeedDiv = 3;
    //private boolean inBounceChange = false;

    private static double freeFallSpeed = 0.05;
    private static Vektor gravityStrength = new Vektor(0, freeFallSpeed);

    //constructor
    public BallPhysic(){
        super(0, 0);
    }

    //Berechnet die Physik anhand des übergebenen Balles neu
    public void RecalkPhysicWithBall(Ball ball){

        canMove(new Vektor(0,0), ball, true);
        DrawingObjektGroup collidateObjects = ball.lab.getCollidateObjekts(ball.ball, false, true);
        int nSize = collidateObjects.drawingObjekts.size();

        boolean bounceChange = false;
        for(int i = 0; i < nSize; i++){
            DrawingObject curColl = collidateObjects.drawingObjekts.get(i);
            if(curColl.isBounceActivator()) {
                collidateObjects.drawingObjekts.remove(curColl);
                nSize--;
                bounceChange = true;
            }


        }
        if(bounceChange == false)
        {
            if(inBounceChange == true)
                if(bounceSpeedDiv == 3)
                    bounceSpeedDiv = 1;
        }
        inBounceChange = bounceChange;

        Vektor dir = null;
        boolean canMove = false;
        int curCollObj = 0;

        //if(nSize > 0)
            ball.rotate(DrawingPanel.drawingAngle, GameFrame.midPoint);

        while(!canMove) {
            Vektor forceBk = new Vektor(this);

            //Get the correct direction vektor for the current collision object
            if (nSize == 0) {
                dir = SetSpeedToDir(new Vektor(0, 1));
                bounce = false;
            }else {
                if (curCollObj < nSize) {
                    dir = getDirVec(curCollObj, collidateObjects, ball);
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
            if (nSize > 0 && Math.abs(angleDiff) > 0.01 && !bounce && len > 0.01) {
                boolean goesDown = goesDown();
                len /= bounceSpeedDiv;

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
                //len = orthoLine.getCrossingPoint(dirLine).getLen();

                if (dir.y != 0) {
                    x = dir.x;
                } else
                    len = 0;
                y = dir.y;

                Vektor newVecUp = new Vektor(-x, -y);
                if (forceBk.isRightOr() != isRightOr() && forceBk.isLeftOr() != isLeftOr()) {
                    x = newVecUp.x;
                    y = newVecUp.y;
                    len -= Math.abs(dir.getLen());
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
                if(nSize > 0 && nSize == curCollObj) {
                    x = 0;
                    y = 0;
                }else {
                    x = forceBk.x;
                    y = forceBk.y;
                }
                //System.out.println("kann nicht");
            }
        }
    }


    private Vektor getDirVec(int i, DrawingObjektGroup collidateObjects, Ball ball){
        Vektor dir = null;
        //gets the current object from the collision objects
        DrawingObject curCollObj = curCollObj = collidateObjects.drawingObjekts.get(i);

        //sets the dir vector with the direction of the collision object
        dir = curCollObj.getMoveVek(ball.ball.getMidPoint());
        //if the direction of the vector goes up turn the vector
        if(!dir.goesDown())
            dir = new Vektor(-dir.x, -dir.y);

        dir = SetSpeedToDir(new Vektor(dir.getNormVek().x, dir.getNormVek().y));
        return dir;
    }

    private boolean canMove(Vektor move, Ball ball, boolean correctDiff){

        Vektor moveCopy = new Vektor(move);
        moveCopy.Add(ball.ball.getMidPoint());

        DrawingArc movedBall = new DrawingArc(moveCopy, ball.ball.getRadius(), 0,360);
        DrawingObjektGroup collidateObjects = ball.lab.getCollidateObjekts(movedBall, true, false);

        if(collidateObjects.drawingObjekts.size() == 0)
            return true;
        else {

            if(correctDiff)
            {
                int size = collidateObjects.drawingObjekts.size();

                while(collidateObjects.drawingObjekts.size() > 0) {

                    Vektor moveVec = collidateObjects.drawingObjekts.get(0).getMoveOnLineVec();
                    ball.ball.move(moveVec);
                    collidateObjects = ball.lab.getCollidateObjekts(ball.ball, true, false);

                }
            }
            return false;

        }
    }

    private double getSpeed(double angle){
        angle = Math.abs(angle);
        if (angle == 0 || angle == Math.PI)
            return 0.00000001;
        if(angle > Math.PI / 2) {

            if (angle > Math.PI) {

                if (angle > Math.PI * 1.5) {
                    return (angle - Math.PI * 2) * (freeFallSpeed / Math.PI / 2);
                } else return (angle - Math.PI) * (freeFallSpeed / Math.PI / 2);

            } else return (angle - Math.PI) * (freeFallSpeed / Math.PI / 2);

        } else return angle * (freeFallSpeed / Math.PI / 2);

    }

    private Vektor SetSpeedToDir(Vektor dir){

        double len = getSpeed(dir.getCrossingAngle(null));
        dir.setLen(len);
        return dir;

    }
}
