package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.AutoCode.Control.AcceleratedGain;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.AutoCode.Control.MecanumDrive;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.IntoTheDeepDriveBase;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.TrackingWheelIntegrator;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Vector;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.MotorPowers;

public class StateMPointApproach implements StateMMovmentPerformer
{
    double targetX;
    double targetY;
    double maxPower;
    double targetHeading;
    AcceleratedGain acclHeadingGain;
    double maxTurnPower;
    double thresh;
    double angleThresh;
    double headingGain;
    double xyGain;
    boolean stopMotorsOnDone = false;
    TrackingWheelIntegrator trackingWheelIntegrator;
    IntoTheDeepDriveBase intoTheDeepDriveBase;
    double error = 0;
    boolean angleGood = false;


    public StateMPointApproach()
    {

    }

    @Override
    public boolean run()
    {
        if (moveToWaypoint()) {
            if(stopMotorsOnDone)
            {
                intoTheDeepDriveBase.stopMotors();
            }
            return true;
        }
        return false;
    }

    @Override
    public void reset() {

    }

    boolean moveToWaypoint()
    {

//        it no likey
        double turnError = trackingWheelIntegrator.getHeading() - targetHeading;

        double xErr = targetX - trackingWheelIntegrator.getX();
        double yErr = targetY - trackingWheelIntegrator.getY();

        if(angleThresh == 0)
        {
            angleGood = true;
        }
        else
        {
            angleGood = Math.abs(turnError) < angleThresh;
        }

        Vector driveVector = new Vector();
        driveVector.addCartesian(xErr, yErr);
        driveVector.rotateDegrees(-trackingWheelIntegrator.getHeading());

        driveVector.setCartesian(driveVector.getX()*1.5, driveVector.getY());

        double mag = Range.clip(driveVector.getMag()*xyGain, -maxPower, maxPower);

        double turnCorrection = Range.clip(turnError*getHeadingGain(), -maxTurnPower, maxTurnPower);

        MotorPowers pows = MecanumDrive.calcPolar(mag, driveVector.getDir(), turnCorrection);

        error = Vector.calcMag(xErr, yErr);

        intoTheDeepDriveBase.setMotorPowers(pows);

        return Math.abs(error) < thresh && angleGood;
    }

    double getHeadingGain()
    {
        if(acclHeadingGain == null)
        {
            return headingGain;
        }
        else
        {
            return acclHeadingGain.getControlledGain();
        }
    }

    boolean opModeIsActive()
    {
        return DeepGlobals.opMode.opModeIsActive();
    }

    public static class Builder
    {
        double targetX;
        double targetY;
        double maxPower = .5;
        double targetHeading;
        AcceleratedGain acclHeadingGain;
        double maxTurnPower;
        double thresh;
        double angleThresh;
        double xyGain = 0.06;
        double headingGain = .012;
        boolean stopMotorsOnDone = false;

        public Builder setTargetPosition(double x, double y)
        {
            this.targetX = x;
            this.targetY = y;

            return this;
        }

        public Builder setMaxPower(double maxPower)
        {
            this.maxPower = maxPower;
            return this;
        }

        public Builder setXyGain(double xyGain)
        {
            this.xyGain = xyGain;
            return this;
        }

        public Builder setHeadingDynamicGain(AcceleratedGain headingGain)
        {
            this.acclHeadingGain = headingGain;
            return this;
        }

        public Builder setMaxTurnPower(double maxTurnPower)
        {
            this.maxTurnPower = maxTurnPower;
            return this;
        }

        public Builder setMovementThresh(double thresh)
        {
            this.thresh = thresh;
            return this;
        }

        public Builder setTargetHeading(double heading)
        {
            this.targetHeading = heading;
            return this;
        }

        public Builder stopMotorsOnDone(boolean stopMotorsOnDone)
        {
            this.stopMotorsOnDone = stopMotorsOnDone;
            return this;
        }

        public Builder setHeadingThreshold(double headingThreshold)
        {
            this.angleThresh = headingThreshold;
            return this;
        }

        public StateMPointApproach build()
        {
            StateMPointApproach pointApproach = new StateMPointApproach();
            pointApproach.targetX          = targetX;
            pointApproach.targetY          = targetY;
            pointApproach.targetHeading    = targetHeading;
            pointApproach.maxPower         = maxPower;
            pointApproach.acclHeadingGain  = acclHeadingGain;
            pointApproach.maxTurnPower     = maxTurnPower;
            pointApproach.angleThresh      = angleThresh;
            pointApproach.headingGain      = headingGain;
            pointApproach.stopMotorsOnDone = stopMotorsOnDone;
            pointApproach.thresh           = thresh;
            pointApproach.xyGain           = xyGain;

            return pointApproach;
        }
    }
}
