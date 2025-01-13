package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Tests;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.FTCCenterStage.Globals;
import org.firstinspires.ftc.teamcode.FTCCenterStage.drivebase.CenterStageDriveBase;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.TrackingWheelIntegrator;
import com.qualcomm.robotcore.hardware.DigitalChannel;

//@Disabled
@TeleOp
public class Maget2 extends LinearOpMode {

    // Drive Motors
    // Other Motors
//    private DcMotorEx UppyHangs;

    // Servos
    public static Servo Spinnie;
    public static Servo Wheelie;
    // probably important
    private float Rservopos;
    private float Lservopos;
    private RevTouchSensor Maget;
//    TouchSensor test_magnetic;
//    private DigitalChannel limitSwitch;
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

//        limitSwitch = hardwareMap.get(DigitalChannel.class, "limitSwitchName");
        Maget = hardwareMap.get(RevTouchSensor.class, "Maget");

//        limitSwitch.setMode(DigitalChannel.Mode.INPUT);
        // Telemetry initialisation
        telemetry.setMsTransmissionInterval(20);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();



        // StateM stuff
        while (opModeIsActive()) {

            if (Maget.isPressed()) {
                Spinnie.setPosition(1);
//                test_motor.setPower(0);
            } else { // Otherwise, run the motor
                Spinnie.setPosition(.5);
//                test_motor.setPower(0.3);
            }
//            if (limitSwitch.getState()) {
//                // Limit switch is not triggered
//                Spinnie.setPosition(1);
//            } else {
//                // Limit switch is triggered
//                Spinnie.setPosition(.5);
//            }
            // Telemetry
            telemetry.addData("ServoRpos", Rservopos);
            telemetry.addData("ServoLpos", Lservopos);
            telemetry.addData(":)",Maget.isPressed());
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


