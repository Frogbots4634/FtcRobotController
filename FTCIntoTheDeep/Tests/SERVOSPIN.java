package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FTCCenterStage.Globals;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.TrackingWheelIntegrator;
import org.firstinspires.ftc.teamcode.FTCCenterStage.drivebase.CenterStageDriveBase;

//@Disabled
@TeleOp
public class SERVOSPIN extends LinearOpMode {

    // Drive Motors
    // Other Motors
//    private DcMotorEx UppyHangs;

    // Servos
    public static Servo Spinnie;
    public static Servo Wheelie;
    // probably important
    private float Rservopos;
    private float Lservopos;


    // Local Variables

    // Magic state machine thing
    public static boolean RESETME;

    // Sysinternals, dont touch
    CenterStageDriveBase centerStageDriveBase;
    TrackingWheelIntegrator trackingWheelIntegrator;

    // State Machine

    public static double UppyHangCounts;
    //    private float  speedFactor;
    public void runOpMode() {
        // Other Motors
//        Uppy = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "Uppy");

        // Sysinit, don't touch
        centerStageDriveBase = new CenterStageDriveBase();
        centerStageDriveBase.init(hardwareMap);
        centerStageDriveBase.enablePID();
        Globals.robot=centerStageDriveBase;
        Globals.driveBase=centerStageDriveBase;
        Globals.trackingWheelIntegrator = trackingWheelIntegrator;
        Globals.opMode = this;
        Globals.robot.enableBrake(true);
        trackingWheelIntegrator = new TrackingWheelIntegrator();

        // Initial Servo positions
        Rservopos = 1;
        Lservopos = -1;

        // Run to position
        Spinnie = (Servo) hardwareMap.get(Servo.class, "Spinie" );
        Wheelie = (Servo) hardwareMap.get(Servo.class, "Wheelie" );

        // Telemetry initialisation
        telemetry.setMsTransmissionInterval(20);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();



        // StateM stuff
        while (opModeIsActive()) {

            // Telemetry
            telemetry.addData("ServoRpos", Rservopos);
            telemetry.addData("ServoLpos", Lservopos);
            telemetry.update();

            runGamepad();
        }

    }

    void runGamepad() {

        // Default speed, change with care

        if (gamepad1.a){
            Spinnie.setPosition(1);
            Wheelie.setPosition(0);
        }
        if (gamepad1.b){
            Spinnie.setPosition(0);
            Wheelie.setPosition(1);
        }
        if (gamepad1.x){
            Spinnie.setPosition(.5);
            Wheelie.setPosition(.5);

        }
    }
}


