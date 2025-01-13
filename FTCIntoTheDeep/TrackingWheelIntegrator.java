package org.firstinspires.ftc.teamcode.FTCIntoTheDeep;

import com.qualcomm.robotcore.util.MovingStatistics;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.AutoCode.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.odo;
//import org.firstinspires.ftc.teamcode.Vector;


public class TrackingWheelIntegrator
{
    private double x, y;

    private double oldX, oldY;

//    private int dPosLeft, dPosRight, dPosAux;

//    double dH;


    //GLOBALS
//    private double dX, dY//;

    private double heading;
//    private double TICKS_PER_DEGREE = -285.3;
//    private double TICKS_PER_INCH_NORMAL = -1710;
//    private double TICKS_PER_INCH_STRAFE = 1710;
//    double AUX_SPIN_SLIP = 0.101919839;

    boolean init = false;

    boolean useImu = false;

    double wheelHead;

    double lastImu;

    MovingStatistics speedometer = new MovingStatistics(50);

    long lastUpdateTime;

    double headingOffsetDegrees;

//    public void setFirstTrackingVal(double x, double y) {
////        trackingVector.setCartesian(x,y);
//            }
//    public void setHeading(double newHeading) {
//           heading = newHeading;
//
//    }
//    public void SetX(double NewX) {
//        x = NewX;
////        /trackingVector.setCartesian(x,y);
//    }
//    public void SetY(double NewY) {
//        y = NewY;
////        trackingVector.setCartesian(x,y);
//    }

//    public void ZeroHeading() {
//        lastPosLeft = cPosLeft;
//        lastPosRight = cPosRight;
//    }


//    public void setHeadingOffsetDegrees(double imuHeading)
//    {
////        this.headingOffsetDegrees = heading - imuHeading ;
//
//        System.out.println(String.format("Sync heading w/ IMU... IMU says %f wheels say %f offset is %f", imuHeading, heading, headingOffsetDegrees));
//    }

    public void update(int cPosLeft, int cPosRight, int cPosAux)
    {
        if(!init)
        {

            init = true;
            return;
        }

        Pose2D pos = odo.getPosition();

        heading = pos.getHeading(AngleUnit.DEGREES);
        //dH = pos.getHeading(AngleUnit.DEGREES);
        y = pos.getY(DistanceUnit.INCH);
        x = pos.getX(DistanceUnit.INCH);
//
//        dY /= TICKS_PER_INCH_NORMAL;
//        dX /= TICKS_PER_INCH_STRAFE;

//
//        instantVector.setCartesian(dX, dY);
//
//        trackingVector.addPolar(instantVector.getMag(), heading+instantVector.getDir());


        oldX = x;
        oldY = y;

//        x = trackingVector.getX();
//        y = -trackingVector.getY();

        if(lastUpdateTime != 0)
        {
            double deltaDisplacement = Math.sqrt(Math.pow(x-oldX, 2) + Math.pow(y-oldY, 2));
            long deltaTime = System.currentTimeMillis() - lastUpdateTime;
            double speed = deltaDisplacement / (deltaTime/1000.0);

            speedometer.add(speed);
        }

        lastUpdateTime = System.currentTimeMillis();
    }

    public void clear()
    {
//        trackingVector.clear();
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getHeading()
    {
        return heading;
    }
    public double speed()
    {
        return speedometer.getMean();
    }

    public double getWheelHead()
    {
        return wheelHead;
    }
}