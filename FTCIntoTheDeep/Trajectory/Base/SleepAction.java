package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.Trajectory.Base;

import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.DeepGlobals;

public class SleepAction implements MovementPerformer
{
    long start;
    int ms;

    @Override
    public void run()
    {
        start = System.currentTimeMillis();

        while ((System.currentTimeMillis()-start) < ms && DeepGlobals.opMode.opModeIsActive())
        {
            DeepGlobals.updateTracking();
        }
    }

    public SleepAction(int ms)
    {
        this.ms = ms;
    }
}
