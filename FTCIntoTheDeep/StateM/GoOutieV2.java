//package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM;
//
//import org.firstinspires.ftc.teamcode.FTCCenterStage.StateM.StateMMovmentPerformer;
//import org.firstinspires.ftc.teamcode.FTCCenterStage.StateM.StateMachine;
//import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.BackupPlan;
//
//public class GoOutieV2 extends StateMachine<GoOutieV2.State> implements StateMMovmentPerformer {
//
//    public double angle;
//
//
//    public enum State {
//        START,
//        GETOUTOFHERE,
//        THETOUNGUESLURPS,
//        NOTTHEWATERFALL,
//        IDLE,
//
//
//    }
//// GETOUTOFHERE = UppyMini go either strait or partially down, Double check claw open
//// THETOUUNGESLURPS TheToungue extends
//// NOTTHEWATERFALL UppyMini falls
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
//    public GoOutieV2() {
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
//                    switchState(State.GETOUTOFHERE);
//                }
//                break;
//            }
//            case GETOUTOFHERE: {
//                BackupPlan.UppyMini.setPosition(.75);
//                BackupPlan.Wrist.setPosition(.2);
//
//
//                if(getElapsedStateTime() >1000) {
//                    switchState(State.THETOUNGUESLURPS);
//                }
//                break;
//            }
//            case THETOUNGUESLURPS: {
//                BackupPlan.TheToungue.setPosition(.65);
//                if(getElapsedStateTime() > 500) {
//
//                    switchState(State.NOTTHEWATERFALL);
//                }
//                break;
//            }
//            case NOTTHEWATERFALL: {
//                BackupPlan.Claw.setPosition(.3);
//                BackupPlan.UppyMini.setPosition(.9);
//
//                if(getElapsedStateTime() > 200) {
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
