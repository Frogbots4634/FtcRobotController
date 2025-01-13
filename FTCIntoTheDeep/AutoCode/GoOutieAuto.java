package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.AutoCode;

import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base.StateMMovmentPerformer;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base.StateMachine;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals;

import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Claw;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.TheToungue;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.UppyMini;
import static org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals.Wrist;

public class GoOutieAuto extends StateMachine<GoOutieAuto.State> implements StateMMovmentPerformer {

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
    public GoOutieAuto() {
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
                UppyMini.setPosition(.75);
                Wrist.setPosition(.2);


                if(getElapsedStateTime() >1000) {
                    switchState(State.THETOUNGUESLURPS);
                }
                break;
            }
            case THETOUNGUESLURPS: {
                TheToungue.setPosition(.65);
                if(getElapsedStateTime() > 500) {

                    switchState(State.NOTTHEWATERFALL);
                }
                break;
            }
            case NOTTHEWATERFALL: {
                Claw.setPosition(.3);
                UppyMini.setPosition(.9);

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
