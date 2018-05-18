package com.apigateway.demo.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 接口注册中心
 */
public class ApiStore {

    private final static Logger logger = LoggerFactory.getLogger(ApiStore.class);

    private ApplicationContext applicationContext;

    private HashMap<String,ApiRunnable> apiMap = new HashMap<>();

    public ApiStore(ApplicationContext applicationContext) {
        Assert.notNull(applicationContext);
        this.applicationContext = applicationContext;
    }

    public void loadApiFromSpringBeans(){
        String[] names = applicationContext.getBeanDefinitionNames();
        Class<?> type;
        for (String name : names) {
            type = applicationContext.getType(name);
            for (Method m : type.getDeclaredMethods()) {
                APIMapping apiMapping = m.getAnnotation(APIMapping.class);
                if(apiMapping != null){
                    addApiItem(apiMapping,name,m);
                }
            }
        }
    }

    public ApiRunnable findApiRunnable(String apiName){
        return apiMap.get(apiName);
    }

    public ApiRunnable findApiRunnable(String apiName,String version){
        return apiMap.get(apiName+"_"+version);
    }

    public List<ApiRunnable> findApiRunnables(String apiName){
        if(apiName == null){
            throw  new IllegalArgumentException("api name must not null");
        }
        List<ApiRunnable> list = new ArrayList<>(20);
        for (ApiRunnable api : apiMap.values()) {
            if(api.apiName.equals(apiName)){
                list.add(api);
            }
        }
        return list;
    }

    public List<ApiRunnable> getAll(){
        List<ApiRunnable> list = new ArrayList<>(20);
        list.addAll(apiMap.values());
        Collections.sort(list, (o1, o2) -> {
            return o1.getApiName().compareTo(o2.getApiName());
        });
        return list;
    }

    private void addApiItem(APIMapping apiMapping,String beanName,Method method){
        for (Class<?> aClass : method.getParameterTypes()) {
            aClass.getDeclaredFields()
            if(Object.class.equals(aClass)){
                throw new RuntimeException("接口不符合规范");
            }
        }
        ApiRunnable apiRunnable = new ApiRunnable();
//        apiRunnable.apiName = apiMapping
    }

    public boolean containsApi(String apiName,String version){
        return apiMap.containsKey(apiName) && apiMap.containsKey(version);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //用于执行对应的API方法
    public class ApiRunnable{
        //api名称
        String apiName;
        //bean名称
        String targetName;
        //实例
        Object target;
        //目标方法
        Method targetMethod;

        public Object run(Object... args) throws InvocationTargetException, IllegalAccessException {
            if(target == null){
                target = applicationContext.getBean(targetName);
            }
            return targetMethod.invoke(target,args);
        }

        public Class<?>[] getParamTypes(){
            return targetMethod.getParameterTypes();
        }

        public String getApiName() {
            return apiName;
        }

        public void setApiName(String apiName) {
            this.apiName = apiName;
        }

        public String getTargetName() {
            return targetName;
        }

        public void setTargetName(String targetName) {
            this.targetName = targetName;
        }

        public Object getTarget() {
            return target;
        }

        public void setTarget(Object target) {
            this.target = target;
        }

        public Method getTargetMethod() {
            return targetMethod;
        }

        public void setTargetMethod(Method targetMethod) {
            this.targetMethod = targetMethod;
        }
    }
}


