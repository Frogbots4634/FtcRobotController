//package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM;
//
//import org.firstinspires.ftc.teamcode.FTCCenterStage.StateM.StateMMovmentPerformer;
//import org.firstinspires.ftc.teamcode.FTCCenterStage.StateM.StateMachine;
//import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.BackupPlan;
//
//public class GoInnieV2 extends StateMachine<GoInnieV2.State> implements StateMMovmentPerformer {
//
//    public double angle;
//
//
//    public enum State {
//        START,
//        RETRACTTHEWATERFALL,
//        SLURPIEUPPY,
//        WATERFALLSTHEOTHERWAY,
//        IDLE,
//
//
//    }
//// RETRACTTHEWATERFALL = UppyMini Rises
//// SLURPIEUPPY TheToungue Retracts
//// WATERFALLSTHEOTHERWAY UppyMini falls backwards
//
//
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
//    public GoInnieV2() {
//        state = State.START;
//
//    }
//    @Override
//    public ReturnState runIteration() {
//        switch (state) {
//
//            case START: {
//
//                if (getElapsedStateTime() > 500) {
//                    switchState(State.RETRACTTHEWATERFALL);
//                }
//                break;
//            }
//            case RETRACTTHEWATERFALL: {
//                BackupPlan.UppyMini.setPosition(.65);
//                BackupPlan.Claw.setPosition(0);
//                BackupPlan.Wrist.setPosition(.2);
//
//                if(getElapsedStateTime() >500) {
//                    switchState(State.SLURPIEUPPY);
//                }
//                break;
//            }
//            case SLURPIEUPPY: {
//                BackupPlan.TheToungue.setPosition(1);
//
//                if(getElapsedStateTime() > 1000) {
//
//                    switchState(State.WATERFALLSTHEOTHERWAY);
//                }
//                break;
//            }
//            case WATERFALLSTHEOTHERWAY: {
//                BackupPlan.UppyMini.setPosition(0);
//
//                if(getElapsedStateTime() > 2000) {
//                    BackupPlan.RESETME = true;
//                    switchState(State.IDLE);
//                }
//                break;
//            }
//
//            case IDLE: {
//                if(getElapsedStateTime() > 100) {
//
//                    return ReturnState.PROCEED;
//                }
//                break;
//            }
//
//        }
//        return ReturnState.KEEP_RUNNING_ME;
//    }
//}
