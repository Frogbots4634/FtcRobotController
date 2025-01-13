//package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.AutoCode.StateM;
//
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
//import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.IntoTheDeepDriveBase;
//import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepDrive.MotorPowers;
//import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals;
//import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base.StateMMovmentPerformer;
//import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base.StateMachine;
//import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.odo;
//
//public class Specimen extends StateMachine<Specimen.State> implements StateMMovmentPerformer {
//
//    IntoTheDeepDriveBase intoTheDeepDriveBase;
//
////    Pose2D pos = odo.getPosition();
////    double GetX = pos.getX(DistanceUnit.INCH);
////    double GetY = pos.getY(DistanceUnit.INCH);
////    double GetH = pos.getHeading(AngleUnit.DEGREES);
//
////    private void Turn(double H){
////        Pose2D pos = odo.getPosition();
////        double Error = H - pos.getHeading(AngleUnit.DEGREES);
////        double Kp = (double) 1/180;
////        double LCorrection = Error * -Kp;
////        double RCorrection = Error * Kp;
////        MotorPowers motorPowers = new MotorPowers();
////
////        intoTheDeepDriveBase.setMotorPowers(LCorrection, RCorrection);
////
////    }
////    private void Straight(double X){
////        Pose2D pos = odo.getPosition();
////        double Error = X - pos.getX(DistanceUnit.INCH);
////        double Kp = (double) 1/80;
////        double Correction = Error * Kp;
////        MotorPowers motorPowers = new MotorPowers();
////
////        intoTheDeepDriveBase.setMotorPowers(Correction);
////    }
////    private void Strafe(double Y){
////        Pose2D pos = odo.getPosition();
////        double Error = Y - pos.getY(DistanceUnit.MM);
////        double Kp = (double) 1/80;
////        double FLCorrection = Error * -Kp;
////        double FRCorrection = Error * Kp;
////        double RLCorrection = Error * Kp;
////        double RRCorrection = Error * -Kp;
////        MotorPowers motorPowers = new MotorPowers();
////
////        intoTheDeepDriveBase.setMotorPowers(FLCorrection,FRCorrection,RLCorrection,RRCorrection);
////    }
////
//
//    public double angle;
//
//    public enum State {
//        START,
//        READY_1_PLACE,
//        PLACE_1,
//        BACK_1,
//        IDLE,
//
//
//    }
//
//    public State getStAte()
//    {
//        return state;
//    }
//
//    @Override
//    public boolean run() {
//        return runIteration() == ReturnState.PROCEED;
//    }
//
//    @Override
//    public void reset() {
//        state = State.START;
//
//    }
//
//    @Override
//    public String getName() {
//        return "AutoTransfer";
//    }
//    public Specimen() {
//        state = State.START;
//
//    }
//    @Override
//    public ReturnState runIteration() {
//        switch (state) {
//
//            case START: {
//
//                if (getElapsedStateTime() > 1000) {
//                    switchState(State.READY_1_PLACE);
//                }
//                break;
//            }
//            case READY_1_PLACE: {
////                MoveInOrder.Straight(-24);
////                UppyHC();
//
//                if(getElapsedStateTime() > 5000){
//                    switchState(State.IDLE);
//                }
//                break;
//            }
////            case PLACE_1: {
////                TrashCanStore();
////
////                if(getElapsedStateTime() > 750) {
////                    switchState(State.BACK_1);
////                }
////                break;
////            }
////            case BACK_1: {
////                UppyD();
////
////                if(UppyCounts <50) {
////                    TrashCanReady();
////
////                    if(getElapsedStateTime() >2000) {
////                        switchState(State.IDLE);
////
////                    }
////                }
////                break;
////            }
//            case IDLE: {
//                if(getElapsedStateTime() > 100) {
//                    DeepGlobals.RESETME = true;
//                    return ReturnState.PROCEED;
//                }
//                break;
//            }
//
//        }
//        return ReturnState.KEEP_RUNNING_ME;
//    }
//}
