package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base.StateMMovmentPerformer;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base.StateMachine;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.MeckyDrives;

import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TrashCan;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TrashCanDump;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TrashCanReady;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TrashCanStore;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Uppy;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyCounts;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyD;

public class GoDowny extends StateMachine<GoDowny.State> implements StateMMovmentPerformer {

    public double angle;

    public enum State {
        START,
        DUMPY,
        FIRSTTHINGSFIRST,
        NOWYOUMAY,
        IDLE,


    }
// RETRACTTHEWATERFALL = UppyMini Rises
// SLURPIEUPPY TheToungue Retracts
// WATERFALLSTHEOTHERWAY UppyMini falls backwards



    public State getStAte()
    {
        return state;
    }

    @Override
    public boolean run() {
        return runIteration() == ReturnState.PROCEED;
    }

    @Override
    public void reset() {
        state = State.START;

    }

    @Override
    public String getName() {
        return "AutoTransfer";
    }
    public GoDowny() {
        state = State.START;

    }
    @Override
    public ReturnState runIteration() {
        switch (state) {

            case START: {

                if (getElapsedStateTime() > 1000) {
                    switchState(State.DUMPY);
                }
                break;
            }
            case DUMPY: {
                TrashCanDump();

                if(getElapsedStateTime() >750) {
                    switchState(State.FIRSTTHINGSFIRST);
                }
                break;
            }
            case FIRSTTHINGSFIRST: {
                TrashCanStore();

                if(getElapsedStateTime() > 750) {
                    switchState(State.IDLE);
                }
                break;
            }
//            case  NOWYOUMAY: {
//                UppyD();
//
//                if(UppyCounts <50) {
//                    TrashCanReady();
//
//                    if(getElapsedStateTime() >2000) {
//                        switchState(State.IDLE);
//
//                    }
//                }
//                break;
//            }
            case IDLE: {
                if(getElapsedStateTime() > 100) {
                    DeepGlobals.RESETME = true;
                    return ReturnState.PROCEED;
                }
                break;
            }

        }
        return ReturnState.KEEP_RUNNING_ME;
    }
}
