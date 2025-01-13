package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM;

import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base.StateMMovmentPerformer;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base.StateMachine;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.MeckyDrives;

import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Claw;
//import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Door;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ClawOPEN;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TheToungue;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.ToungueClose;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMini;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMiniDown;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMinistraight;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Wrist;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Wriststraight;

public class GoOutie extends StateMachine<GoOutie.State> implements StateMMovmentPerformer {

    public double angle;


    public enum State {
        START,
        GETOUTOFHERE,
        THETOUNGUESLURPS,
        NOTTHEWATERFALL,
        IDLE,


    }
// GETOUTOFHERE = UppyMini go either strait or partially down, Double check claw open
// THETOUUNGESLURPS TheToungue extends
// NOTTHEWATERFALL UppyMini falls



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
    public GoOutie() {
        state = State.START;

    }
    @Override
    public ReturnState runIteration() {
        switch (state) {

            case START: {

                if (getElapsedStateTime() > 500) {
                    switchState(State.GETOUTOFHERE);
                }
                break;
            }
            case GETOUTOFHERE: {
                UppyMinistraight();
                Wriststraight();


                if(getElapsedStateTime() >1000) {
                    switchState(State.THETOUNGUESLURPS);
                }
                break;
            }
            case THETOUNGUESLURPS: {
                ToungueClose();
                if(getElapsedStateTime() > 500) {

                    switchState(State.NOTTHEWATERFALL);
                }
                break;
            }
            case NOTTHEWATERFALL: {
                ClawOPEN();
                UppyMiniDown();

                if(getElapsedStateTime() > 200) {
                    DeepGlobals.RESETME = true;
                    switchState(State.IDLE);
                }
                break;
            }

            case IDLE: {
                if(getElapsedStateTime() > 100) {

                    return ReturnState.PROCEED;
                }
                break;
            }

        }
        return ReturnState.KEEP_RUNNING_ME;
    }
}
