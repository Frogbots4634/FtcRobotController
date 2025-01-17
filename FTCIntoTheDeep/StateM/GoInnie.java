package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM;

import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base.StateMMovmentPerformer;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base.StateMachine;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.MeckyDrives;

import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Claw;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ClawClosed;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ClawOPEN;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ClawOpen;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.None;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Out;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TheToungue;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ToungueIn;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMini;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMiniIn;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMinistraight;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Wrist;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Wriststraight;

public class GoInnie extends StateMachine<GoInnie.State> implements StateMMovmentPerformer {

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
    public GoInnie() {
        state = State.START;

    }
    @Override
    public ReturnState runIteration() {
        switch (state) {

            case START: {

                if (getElapsedStateTime() > 1000) {
                    switchState(State.RETRACTTHEWATERFALL);
                }
                break;
            }
            case RETRACTTHEWATERFALL: {
                UppyMinistraight();
                ClawClosed();
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

                if(getElapsedStateTime() > 2000) {
                    DeepGlobals.RESETME = true;
                    switchState(State.IDLE);
                }
                break;
            }

            case LEAVEIT:{
                ClawOPEN();
                if(getElapsedStateTime() > 2000) {
                    DeepGlobals.RESETME = true;
                    switchState(GoInnie.State.IDLE);
                }
            }

            case IDLE: {
                ClawOpen();
                if(getElapsedStateTime() > 100) {

                    return ReturnState.PROCEED;
                }
                break;
            }

        }
        return ReturnState.KEEP_RUNNING_ME;
    }
}
