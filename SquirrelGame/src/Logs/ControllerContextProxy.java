package Logs;

import BotAPI.ControllerContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Logger;

public class ControllerContextProxy {

    ControllerContext context;
    boolean isMiniSquirrel;

    public ControllerContextProxy(ControllerContext context, boolean isMiniSquirrel){
        this.context = context;
        this.isMiniSquirrel = isMiniSquirrel;
    }

    public ControllerContext proxy(){
        DebugHandler handler = new DebugHandler(context);
         return (ControllerContext) Proxy.newProxyInstance(ControllerContext.class.getClassLoader(), new Class[]{ControllerContext.class}, handler);
    }

    public class DebugHandler implements InvocationHandler {

        private ControllerContext context;
        Logger log = Logger.getLogger("Logger");

        public DebugHandler(ControllerContext context){
            this.context = context;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
            if(!isMiniSquirrel && method.getName().equals("directionOfMaster")){
                log.fine("IllegalAccessException");
                throw new IllegalAccessException();
            }
            if(!isMiniSquirrel && method.getName().equals("implode")){
                log.fine("IllegalAccessException");
                throw new IllegalAccessException();
            }

            String s = "";
            for (int i = 0; i < args.length; i++){
                //System.out.print("* calling method " + method + " with params ");
                s = s + args[i];
            }
            log.fine(s);

            Object result = null;

            try  {
                result = method.invoke(context, args);
            } catch(IllegalAccessException ex)  {
                ex.printStackTrace();
            } catch(InvocationTargetException ex)  {
                ex.printStackTrace();
            }
            return result;
        }

    }
}
