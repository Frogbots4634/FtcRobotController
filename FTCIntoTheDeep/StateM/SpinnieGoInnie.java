package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM;

import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base.StateMMovmentPerformer;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base.StateMachine;

import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ClawClosed;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.None;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Out;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ToungueIn;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TrashCanReady;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMiniIn;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMiniSTRAIGHT;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMinistraight;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Wriststraight;

public class SpinnieGoInnie extends StateMachine<SpinnieGoInnie.State> implements StateMMovmentPerformer {

    public double angle;


    public enum State {
        START,
        RETRACTTHEWATERFALL,
        SLURPIEUPPY,
        WATERFALLSTHEOTHERWAY,
        LEAVEIT,
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
    public SpinnieGoInnie() {
        state = State.START;

    }
    @Override
    public ReturnState runIteration() {
        switch (state) {

            case START: {

                if (getElapsedStateTime() > 500) {
                    switchState(State.RETRACTTHEWATERFALL);
                }
                break;
            }
            case RETRACTTHEWATERFALL: {
                UppyMinistraight();
                None();
                Wriststraight();

                if(getElapsedStateTime() >500) {
                    switchState(State.SLURPIEUPPY);
                }
                break;
            }
            case SLURPIEUPPY: {
                ToungueIn();

                if(getElapsedStateTime() > 1000) {

                    switchState(State.WATERFALLSTHEOTHERWAY);
                }
                break;
            }
            case WATERFALLSTHEOTHERWAY: {
                UppyMiniIn();
                TrashCanReady();

                if(getElapsedStateTime() > 5000) {
                    switchState(State.LEAVEIT);
                }
                break;
            }
            case LEAVEIT:{
                Out();
                if(getElapsedStateTime() > 5000) {
                    DeepGlobals.RESETME = true;
                    switchState(State.IDLE);
                }
            }

            case IDLE: {
                None();
                if(getElapsedStateTime() > 100) {

                    return ReturnState.PROCEED;
                }
                break;
            }

        }
        return ReturnState.KEEP_RUNNING_ME;
    }
}
