package sh.calaba.instrumentationbackend.actions.netease.drag;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.actions.Action;

/**
 * 
     * @ClassName: Drag 
     * @Description: 扩展robotium的drag功能
     * @author kqy kevinkong@corp.netease.com
     * @date 2012-8-23 下午3:03:31 
     *
 */
public class Drag implements Action {

    @Override
    public Result execute(String... args) {
    	float fromX = Float.parseFloat(args[0]);
        float toX = Float.parseFloat(args[1]);
        float fromY = Float.parseFloat(args[2]);
        float toY = Float.parseFloat(args[3]);
        int stepCount = Integer.parseInt(args[4]);
        InstrumentationBackend.solo.drag(fromX, toX, fromY, toY, stepCount);
        return Result.successResult();
    }

    @Override
    public String key() {
        return "drag";
    }

}

