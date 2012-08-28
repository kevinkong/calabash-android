package sh.calaba.instrumentationbackend.actions.netease.drag;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.view.MotionEvent;
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
        System.out.println("fromX===" + fromX);
        System.out.println("toX===" + toX);
        System.out.println("fromY===" + fromY);
        System.out.println("toY===" + toY);
        System.out.println("stepCount===" + stepCount);
        
//       InstrumentationBackend.solo.drag(fromX, toX, fromY, toY, stepCount);
        
        Instrumentation inst = InstrumentationBackend.instrumentation;
        System.out.println("inst===" + inst);
        long downTime = SystemClock.uptimeMillis();
		long eventTime = SystemClock.uptimeMillis();
		float y = fromY;
		float x = fromX;
		float yStep = (toY - fromY) / stepCount;
		float xStep = (toX - fromX) / stepCount;
		MotionEvent event = MotionEvent.obtain(downTime, eventTime,MotionEvent.ACTION_DOWN, fromX, fromY, 0);
		try {
			inst.sendPointerSync(event);
		} catch (SecurityException ignored) {}
		for (int i = 0; i < stepCount; ++i) {
			y += yStep;
			x += xStep;
			eventTime = SystemClock.uptimeMillis();
			 System.out.println("ACTION_MOVE x===" + x);
			 System.out.println("ACTION_MOVE y===" + y);
			event = MotionEvent.obtain(downTime, eventTime,MotionEvent.ACTION_MOVE, x, y, 0);
			try {
				inst.sendPointerSync(event);
			} catch (SecurityException ignored) {
				 System.out.println("SecurityException===" + ignored);
			}
		}
		eventTime = SystemClock.uptimeMillis();
		event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP,toX, toY, 0);
		try {
			inst.sendPointerSync(event);
		} catch (SecurityException ignored) {
			 System.out.println("SecurityException===" + ignored);
		}
		
		
		
        
        
        System.out.println("ResultgetMessage===" + Result.successResult().getMessage());
        return Result.successResult();
    }

    @Override
    public String key() {
        return "drag";
    }

}

