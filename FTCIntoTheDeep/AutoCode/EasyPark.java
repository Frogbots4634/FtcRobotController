package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.AutoCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
//import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM.GoOutieAuto;

import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.TrackingWheelIntegrator;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.IntoTheDeepDriveBase;
import org.openftc.easyopencv.OpenCvCamera;

import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.trackingWheelIntegrator;


@Autonomous(preselectTeleOp = "MeckyDrives")
public class EasyPark extends LinearOpMode {
    public static Servo TheToungue;
    public static Servo UppyMini;
    public static Servo Claw;
    public static Servo Wrist;
    private Servo Lifty;        //odemetry
    private Servo Holdy;        //odemetry
    public static Servo SmushMush;
    public static Servo TrashCan;     //bucket
    private double ClawMove;
//    TrackingWheelIntegrator trackingWheelIntegrator = new TrackingWheelIntegrator();

    IntoTheDeepDriveBase intoTheDeepDriveBase;
    TrackingWheelIntegrator trackingWheelIntegrator;


    OpenCvCamera openCvCamera;
//    GoOutieAuto GO = new GoOutieAuto();
//    private Boolean Wait;



    public static void clearEnc()        {


    }

        @Override
        public void runOpMode() throws InterruptedException
        {

            TheToungue = (Servo) hardwareMap.get(Servo.class,  "TheToungue");
            UppyMini = (Servo) hardwareMap.get(Servo.class, "UppyMini" );
            Claw = (Servo) hardwareMap.get(Servo.class, "Claw" );
            Wrist = (Servo) hardwareMap.get(Servo.class, "Wrist" );
            Lifty = (Servo) hardwareMap.get(Servo.class, "Lifty" );
            Holdy = (Servo) hardwareMap.get(Servo.class, "Holdy" );
            SmushMush = (Servo) hardwareMap.get(Servo.class, "SmushMush" );
            TrashCan = (Servo) hardwareMap.get(Servo.class, "TrashCan" );

            intoTheDeepDriveBase = new IntoTheDeepDriveBase();
            intoTheDeepDriveBase.init(hardwareMap);
            intoTheDeepDriveBase.enablePID();
            DeepGlobals.robot= intoTheDeepDriveBase;
            DeepGlobals.driveBase= intoTheDeepDriveBase;
            DeepGlobals.trackingWheelIntegrator = trackingWheelIntegrator;
            DeepGlobals.opMode = this;
            DeepGlobals.robot.enableBrake(true);
            trackingWheelIntegrator = new TrackingWheelIntegrator();

            // trackingWheelIntegrator = trackingWheelIntegrator;

            telemetry.setMsTransmissionInterval(20);

            telemetry.setMsTransmissionInterval(20);

            //Setting Start Location in Auto.
//            trackingWheelIntegrator.setFirstTrackingVal(48,0);
            ClawMove = 0;
            Claw.setPosition(ClawMove);

            clearEnc();

            while (!isStarted() && !isStopRequested())
            {
                //Vision During INT

                telemetry.update();
            }
            while (opModeIsActive()) {
                UppyMini.setPosition(.575);
                TheToungue.setPosition(.55);
                Claw.setPosition(ClawMove);
                if (getRuntime() > 5) {
                    ClawMove = .1;
                }


                telemetry.update();
                }
            }
}
